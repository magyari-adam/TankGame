package View;

import Engine.Engine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Render extends JPanel implements KeyListener {

    private BufferedImage background;
    private BufferedImage tank;
    private Engine engine;
    boolean map[][];

    public Render(){
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(800,600));
        this.setMinimumSize(new Dimension(800,600));
        this.setMaximumSize(new Dimension(800,600));

        try {
            background = ImageIO.read(getClass().getResource("/assets/bg.png"));
            tank=ImageIO.read(getClass().getResource("/assets/tankfull.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //setOpaque(false);
        engine=new Engine();
    }

    public void paintbattleground() {
        map=engine.getMap().getMapRepresentation();
        Polygon p=new Polygon();
        for (int x=0;x<800;x++){
            int y=0;
            while(y<600&&!map[x][y]){
                    y++;
            }
            p.addPoint(x,y);
        }
        p.addPoint(getWidth(),getHeight());
        p.addPoint(0,getHeight());
        this.getGraphics().fillPolygon(p);
    }

    public void paintbackgroundtopanel(){
        this.getGraphics().drawImage(background,0,0,this.getWidth(),this.getHeight(),0,0,background.getWidth(),background.getHeight(),null);
    }

    public void paintimagetopanel(int pozX1, int pozY1){
        int height=tank.getHeight();
        int width=tank.getWidth();
        this.getGraphics().drawImage(tank,pozX1,pozY1,pozX1+width,pozY1+height,0,0,width,height,null);
        //https://stackoverflow.com/questions/24063351/drawing-certain-parts-of-image-offset-from-the-corner
    }

    public BufferedImage rotation(BufferedImage image,double theta){
        AffineTransform tx = new AffineTransform();
        tx.rotate(theta, image.getWidth() / 2, image.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        return op.filter(image, null);
    }
    public void  refresh(){
        paintbackgroundtopanel();
        paintbattleground();
        paintimagetopanel(50,50);
    }


    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        engine.keyEventRecognizer(e);
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void start() {
        refresh();
        //Timer t=new Timer();
    }
}
