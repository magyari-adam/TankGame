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
    private Engine engine;
    private boolean map[][];

    public Render() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(800, 600));
        this.setMinimumSize(new Dimension(800, 600));
        this.setMaximumSize(new Dimension(800, 600));

        try {
            background = ImageIO.read(getClass().getResource("/assets/bg.png"));
            tank = ImageIO.read(getClass().getResource("/assets/tank_no_cannon.png"));
            bullet = ImageIO.read(getClass().getResource("/assets/bullet.png"));
            cannon=ImageIO.read(getClass().getResource("/assets/cannon.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        engine = new Engine();
    }

    public void paintBattleground() {
        map = engine.getMap().getMapRepresentation();
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

    public void paintBackgroundToPanel() {
        this.getGraphics().drawImage(background, 0, 0, this.getWidth(), this.getHeight(), 0, 0, background.getWidth(), background.getHeight(), null);
    }

    public void paintImageToPanel(BufferedImage image, int pozX1, int pozY1) {
        int height = tank.getHeight();
        int width = tank.getWidth();
        this.getGraphics().drawImage(image, pozX1, pozY1, pozX1 + width, pozY1 + height, 0, 0, width, height, null);
        //https://stackoverflow.com/questions/24063351/drawing-certain-parts-of-image-offset-from-the-corner
    }

    public BufferedImage rotation(BufferedImage image, double theta) {
        AffineTransform tx = new AffineTransform();
        tx.rotate(theta, image.getWidth() / 2, image.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(image, null);

    }

    public BufferedImage mirror(BufferedImage image) {
        AffineTransform at = AffineTransform.getScaleInstance(-1, 1);
        at.translate(-image.getWidth(null), 0);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return scaleOp.filter(image, null);
    }

    public void refresh() {
        paintBackgroundToPanel();
        paintBattleground();
        ArrayList<Tank> tanks = engine.getTanks();
        for (int i = 0; i < tanks.size(); i++) {
            if (i % 2 == 0) {
                paintImageToPanel(tank, tanks.get(i).getPosition().getX(), tanks.get(i).getPosition().getY());
                paintImageToPanel(cannon,tanks.get(i).getPosition().getX()+55,tanks.get(i).getPosition().getY()+13);
            } else {
                paintImageToPanel(mirror(tank), tanks.get(i).getPosition().getX(), tanks.get(i).getPosition().getY());
                paintImageToPanel(mirror(cannon),tanks.get(i).getPosition().getX()-6,tanks.get(i).getPosition().getY()+13);

            }
        }
        ArrayList<Bullet> bullets = engine.getBullets();
        for (int i=0;i<bullets.size();i++){
            paintImageToPanel(bullet,bullets.get(i).getPosition().getX(),bullets.get(i).getPosition().getY());
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        engine.keyEventRecognizer(e);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void start() {
        refresh();
    }
}
