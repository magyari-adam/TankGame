package View;

import Engine.Engine;
import Engine.Tank;
import Engine.Bullet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Render extends JPanel implements KeyListener {

    private BufferedImage background;
    private BufferedImage tank;
    private BufferedImage bullet;
    private BufferedImage cannon;
    private BufferedImage upcannon;
    private Engine engine;

    public Render(Engine engine) {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(800, 600));
        this.setMinimumSize(new Dimension(800, 600));
        this.setMaximumSize(new Dimension(800, 600));

        try {
            background = ImageIO.read(getClass().getResource("/assets/bg.png"));
            tank = ImageIO.read(getClass().getResource("/assets/tank_no_cannon.png"));
            bullet = ImageIO.read(getClass().getResource("/assets/bullet.png"));
            cannon=ImageIO.read(getClass().getResource("/assets/cannon.png"));
            upcannon=new BufferedImage(cannon.getWidth()+40,cannon.getHeight()+30,cannon.getType());
            Graphics2D g=upcannon.createGraphics();
            g.drawImage(cannon,0,cannon.getHeight()+20,null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.engine = engine;
    }

    public void changeKeyListener(KeyListener k){
        this.removeKeyListener(this.getKeyListeners()[0]);
        this.addKeyListener(k);
    }

    private void paintBattleground() {
        boolean[][] map = engine.getMap().getMapRepresentation();
        Polygon p = new Polygon();
        for (int x = 0; x < 800; x++) {
            int y = 0;
            while (y < 600 && !map[x][y]) {
                y++;
            }
            p.addPoint(x, y);
        }
        p.addPoint(getWidth(), getHeight());
        p.addPoint(0, getHeight());
        this.getGraphics().fillPolygon(p);
    }

    private void paintBackgroundToPanel() {
        this.getGraphics().drawImage(background, 0, 0, this.getWidth(), this.getHeight(), 0, 0, background.getWidth(), background.getHeight(), null);
    }

    private void paintImageToPanel(BufferedImage image, int pozX1, int pozY1) {
        int height = image.getHeight();
        int width = image.getWidth();
        this.getGraphics().drawImage(image, pozX1, pozY1, pozX1 + width, pozY1 + height, 0, 0, width, height, null);
        //https://stackoverflow.com/questions/24063351/drawing-certain-parts-of-image-offset-from-the-corner
    }

    private BufferedImage rotation(BufferedImage image, int angle) {
        BufferedImage after=new BufferedImage(image.getWidth()+10,image.getHeight()+10,image.getType());
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(angle), image.getWidth() / 2, image.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        op.filter(image, after);
        return after;
    }

    private BufferedImage cannonRotation(BufferedImage image, int angle){
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(-1*angle), 0,0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(image, null);

    }

    private BufferedImage mirrorCannonRotation(BufferedImage image, int angle){
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angle), image.getWidth(),image.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(image, null);
    }

    private BufferedImage mirror(BufferedImage image) {
        AffineTransform at = AffineTransform.getScaleInstance(-1, 1);
        at.translate(-image.getWidth(null), 0);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return scaleOp.filter(image, null);
    }

    public void refresh() {
        paintBackgroundToPanel();
        paintBattleground();
        ArrayList<Tank> tanks = engine.getTanks();
        int x,y,angle;
        BufferedImage forgatott;
        for (int i = 0; i < tanks.size(); i++) {
            x=tanks.get(i).getPosition().getX();
            y=tanks.get(i).getPosition().getY();
            angle=tanks.get(i).getTurretAngle();
            forgatott=cannonRotation(upcannon,angle);
            if (i % 2 == 0) {
                paintImageToPanel(rotation(tank,tanks.get(i).getAngleToTerrain()), x-tank.getWidth()/2, y-tank.getHeight()/2);
                if (angle>0){
                    paintImageToPanel(forgatott,x+10-angle/2,y-45+angle/4-angle/14);
                    //paintImageToPanel(cannonRotation(upcannon,angle),x+55-3*angle-angle/4,y+23-cannonRotation(upcannon,angle).getHeight());
                }
                else{
                    paintImageToPanel(cannonRotation(cannon,angle),x+14,y-16);
                }
            } else {
                paintImageToPanel(rotation(mirror(tank),tanks.get(i).getAngleToTerrain()), x-tank.getWidth()/2, y-tank.getHeight()/2);
                if (angle>0){
                    paintImageToPanel(mirrorCannonRotation(mirror(upcannon),angle), x - 88,y-216);
                }else{
                    paintImageToPanel(mirrorCannonRotation(mirror(cannon),angle),x-48,y-16);
                }
            }
        }
        ArrayList<Bullet> bullets = engine.getBullets();
        for (Bullet bullet1 : bullets) {
            paintImageToPanel(bullet, bullet1.getPosition().getX() - bullet.getWidth() / 2, bullet1.getPosition().getY() - bullet.getHeight() / 2);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        engine.keyEventRecognizer(e);
        refresh();
    }

}