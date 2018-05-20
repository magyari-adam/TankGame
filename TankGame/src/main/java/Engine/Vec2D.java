package Engine;

import java.io.Serializable;
import java.util.Objects;

/**
 * An X,Y pair which represents a coordinate or acts as a vector.
 */
public class Vec2D implements Serializable{

    private static final long serialVersionUID = 60L;

    /**
     * x value of the coordinate
     */
    private int x;
    /**
     * y value of the coordinate
     */
    private int y;

    /**
     *  Constructor: constructs a Vec2D object with default (0,0) values.
     */
    public Vec2D() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructor: constructs a Vec2D object with the given (x,y) parameters.
     * @param positionX X: horizontal position
     * @param positionY Y: vertical position
     */
    public Vec2D(int positionX, int positionY) {
        this.x = positionX;
        this.y = positionY;
    }

    /**
     * Copy constructor: copies an existing Vec2D object, and creates a new one from that.
     * @param other Vec2D
     */
    public Vec2D(Vec2D other){
        this.x = other.x;
        this.y = other.y;
    }

    /**
     *
     * @return returns the x position of the object.
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return returns the y position of the object.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets a new x value of the object.
     * @param x newvalue
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Sets a new y value of the object.
     * @param y newvalue
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Adds a Vec2D object to an existing one.
     * @param vec the second operand of the addition.
     */
    public void add(Vec2D vec){
        this.x += vec.x;
        this.y += vec.y;
    }

    /**
     * Static method which adds two Vec2D objects and returns a new one.
     * @param lhs Vec2D
     * @param rhs Vec2D
     * @return return value, new Vec2D
     */
    public static Vec2D add(Vec2D lhs,Vec2D rhs){
        return new Vec2D(lhs.x+rhs.x,lhs.y+rhs.y);
    }

    /**
     * Multiples a Vec2D object with the given constant.
     * @param n Parameter, a number.
     */
    public void times(int n){
        this.x *= n;
        this.y *= n;
    }

    /**
     * Length from the (0,0) point.
     * @return returns the length of the vector.
     */
    public double length(){
        return Math.sqrt(x*x+y*y);
    }

    /**
     * Textual representation of the actual Vec2D object.
     * @return the textual representation.
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * Equals: returns true if the given Vec2D object is equal with the actual one.
     * @param o the other Vec2D object
     * @return return value, can be true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2D vec2D = (Vec2D) o;
        return x == vec2D.x &&
                y == vec2D.y;
    }

    /**
     *
     * @return generates hash value for the Vec2D object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
