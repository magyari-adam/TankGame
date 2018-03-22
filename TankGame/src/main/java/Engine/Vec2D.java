package Engine;

public class Vec2D {

    private int x;
    private int y;

    public Vec2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2D(int positionX, int positionY) {
        this.x = positionX;
        this.y = positionY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vec2D)) {
            return false;
        }
        if (((Vec2D) obj).x == this.x && ((Vec2D) obj).y == this.y) {
            return true;
        } else return false;
    }

    @Override
    public int hashCode() {
        return (("" + this.x) + this.y).hashCode();
    }

    public Vec2D(Vec2D other){
        this(other.getX(),other.getY());
    }
}
