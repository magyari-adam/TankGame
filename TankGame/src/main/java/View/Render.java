package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Render extends JPanel implements KeyListener {

    private BufferedImage image;
    private int destpozX1;
    private int destpozY1;
    private int destpozX2;
    private int destpozY2;
    private int subsourcepozX1;
    private int subsourcepozY1;
    private int subsourcepozX2;
    private int subsourcepozY2;

    public Render(){
        this.setPreferredSize(new Dimension(800,600));
        this.setMinimumSize(new Dimension(800,600));
        this.setMaximumSize(new Dimension(800,600));
        setOpaque(false);

    }

    public Render(int width, int height){
        this.setPreferredSize(new Dimension(width,height));
        this.setMinimumSize(new Dimension(width,height));
        this.setMaximumSize(new Dimension(width,height));
    }

    public void paintbattleground(int width, int height,Graphics graphics) {
        Graphics2D battleGround = (Graphics2D) graphics;
        battleGround.setStroke(new BasicStroke(2));
        battleGround.setColor(Color.GREEN);
        Polygon p = new Polygon();
        for (int x = 0; x < width; x++) {
            p.addPoint(x, (int) (height/2- Math.round((float)20*Math.sin(4/10.f*x*x+6/10.f*x+20))));

        }
        battleGround.drawPolygon(p);
    }

    public void paintbackgroundtopanel(BufferedImage backgound,int pozX, int pozY){
            this.image=backgound;
            this.destpozX1=pozX;
            this.destpozY1=pozY;
            this.destpozX2=this.getWidth();
            this.destpozY2=this.getHeight();
            this.subsourcepozX1=0;
            this.subsourcepozY1=0;
            this.subsourcepozX2=image.getWidth();
            this.subsourcepozY2=image.getHeight();
            paintComponent(this.getGraphics());
    }

    public void paintimagetopanel(BufferedImage image,int sx1,int sy1,int sx2,int sy2,int pozX1, int pozY1,int pozX2,int pozY2){
        this.image=image;
        this.destpozX1=pozX1;
        this.destpozY1=pozY1;
        this.destpozX2=pozX2;
        this.destpozY2=pozY2;
        this.subsourcepozX1=sx1;
        this.subsourcepozY1=sy1;
        this.subsourcepozX2=sx2;
        this.subsourcepozY2=sy2;
        paintComponent(this.getGraphics());
        //https://stackoverflow.com/questions/24063351/drawing-certain-parts-of-image-offset-from-the-corner
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,destpozX1,destpozY1,destpozX2,destpozY2,subsourcepozX1,subsourcepozY1,subsourcepozX2,subsourcepozY2,null);
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

        int keycode=e.getKeyCode();
        switch (keycode){
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_RIGHT:
                break;
        }
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
}
