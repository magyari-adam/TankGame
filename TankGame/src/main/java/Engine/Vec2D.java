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

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void add(Vec2D vec){
        this.setX(this.getX() + vec.getX());
        this.setY(this.getY() + vec.getY());
    }

    public static Vec2D add(Vec2D lhs,Vec2D rhs){
        return new Vec2D(lhs.getX()+rhs.getX(),lhs.getY()+rhs.getY());
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
