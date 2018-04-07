package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Render extends JPanel {

    private BufferedImage image;
    private int pozX;
    private int pozY;

    public Render(){
        this.setPreferredSize(new Dimension(800,600));
        this.setMinimumSize(new Dimension(800,600));
        this.setMaximumSize(new Dimension(800,600));
        this.setLayout(new GridLayout(1,1));

    }

    public Render(int width, int height){
        this.setPreferredSize(new Dimension(width,height));
        this.setMinimumSize(new Dimension(width,height));
        this.setMaximumSize(new Dimension(width,height));
        this.setLayout(new GridLayout(1,1));
    }

    public void paintbattleground(int width, int height,Graphics graphics) {
        Graphics2D battleGround = (Graphics2D) graphics;
        battleGround.setStroke(new BasicStroke(2));
        battleGround.setColor(Color.GREEN);
        Polygon p = new Polygon();
        for (int x = 0; x <= width; x++) {
            p.addPoint(x, (int) (height/2- Math.round((float)20*Math.sin(4/10*x*x+6/10*x+20))));
        }
        battleGround.drawPolygon(p);
    }

    public void paintimagetopanel(BufferedImage backgound,int pozX, int pozY){
            this.image=backgound;
            this.pozX=pozX;
            this.pozY=pozY;
            paintComponent(this.getGraphics());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,pozX,pozY,null);
    }

}
