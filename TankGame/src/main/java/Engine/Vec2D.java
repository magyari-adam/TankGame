package Engine;

import java.util.Objects;

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

    public Vec2D(Vec2D other){
        this.x = other.x;
        this.y = other.y;
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
        this.x += vec.x;
        this.y += vec.y;
    }

    public static Vec2D add(Vec2D lhs,Vec2D rhs){
        return new Vec2D(lhs.x+rhs.x,lhs.y+rhs.y);
    }

    public void times(int n){
        this.x *= n;
        this.y *= n;
    }

    public double length(){
        return Math.sqrt(x*x+y*y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2D vec2D = (Vec2D) o;
        return x == vec2D.x &&
                y == vec2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
