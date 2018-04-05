package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Render extends JPanel {
    private int xPoz;
    private int yPoz;
    private JPanel panel;
    private BufferedImage image;

    public Render() {
        this.xPoz = 0;
        this.yPoz = 0;
    }

    public Render(int xPoz, int yPoz, JPanel panel) {
        this.xPoz = xPoz;
        this.yPoz = yPoz;
        this.panel = panel;
    }

    public Render(int xPoz, int yPoz, JPanel panel, BufferedImage image,Graphics graphics) {
        this.xPoz = xPoz;
        this.yPoz = yPoz;
        this.panel = panel;
        this.image = image;
    }

    public void paintbattleground(int width, int height,Graphics graphics) {
        Graphics2D battleGround = (Graphics2D) graphics;
        battleGround.setStroke(new BasicStroke(2));
        battleGround.setColor(Color.GREEN);
        Polygon p = new Polygon();
        for (int x = 0; x <= 800; x++) {
            p.addPoint(x, (int) (height/2- Math.round((float)20*Math.sin(4/10*x*x+6/10*x+20))));
        }
        battleGround.drawPolygon(p);
    }

    public void paintbackgroundimage(Graphics graphics) {
        Graphics2D background = (Graphics2D) graphics;
        background.drawImage(image, 0, 0, panel);
    }

    public void imagerender(int diffx, int diffy,Graphics graphics) {
        xPoz += diffx;
        yPoz += diffy;
        if (xPoz != panel.getWidth() && xPoz != panel.getHeight() && yPoz != panel.getWidth() && yPoz != panel.getHeight()) {
            Graphics2D imagePicture = (Graphics2D) graphics;
            imagePicture.drawImage(image, xPoz, yPoz, panel);
        } else {
            xPoz = panel.getWidth() - 30;
            yPoz = panel.getHeight() - 30;
            Graphics2D imagePicture = (Graphics2D) graphics;
            imagePicture.drawImage(image, xPoz, yPoz, panel);
        }
    }

    public int getxPoz() {
        return xPoz;
    }

    public void setxPoz(int xPoz) {
        this.xPoz = xPoz;
    }

    public int getyPoz() {
        return yPoz;
    }

    public void setyPoz(int yPoz) {
        this.yPoz = yPoz;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Render)) return false;
        Render render = (Render) o;
        return getxPoz() == render.getxPoz() &&
                getyPoz() == render.getyPoz() &&
                Objects.equals(getPanel(), render.getPanel()) &&
                Objects.equals(getImage(), render.getImage()) &&
                Objects.equals(getGraphics(), render.getGraphics());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getxPoz(), getyPoz(), getPanel(), getImage(), getGraphics());
    }
}
