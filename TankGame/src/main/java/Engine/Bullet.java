package Engine;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents the bullet in the game.
 */
public class Bullet implements Serializable{

    private Vec2D position;
    private Vec2D velocity;
    private int tankID;

    /**
     * Constructor with no paramaters is for creating a bullet object with default values.
     */
    public Bullet(){
        this.position = new Vec2D();
        this.velocity = new Vec2D();
        this.tankID = 0;
    }

    /**
     * Constructor which creates a new bullet object with the following parameters:
     * @param position the position of the bullet in the board, represented with a Vec2D.
     * @param velocity the bullet's velocity.
     * @param tankID the tank ID which identifies the owner of the bullet.
     */
    public Bullet(Vec2D position, Vec2D velocity, int tankID) {
        this.position = position;
        this.velocity = velocity;
        this.tankID = tankID;
    }


    /**
     *
     * @return returns the position of the bullet.
     */
    public Vec2D getPosition() {
        return new Vec2D(position);
    }

    /**
     * Sets the position of the bullet object to the given value.
     * @param position newValue.
     */
    public void setPosition(Vec2D position) {
        this.position = position;
    }

    /**
     *
     * @return returns the velocity of the bullet
     */
    public Vec2D getVelocity() {
        return new Vec2D(velocity);
    }

    /**
     * Sets the velocity to the given parameter
     * @param velocity newValue.
     */
    public void setVelocity(Vec2D velocity) {
        this.velocity = velocity;
    }


    /**
     *
     * @return returns the ID of the bullet.
     */
    public int getTankID() {
        return tankID;
    }

    /**
     * Sets the tankID to the given parameter
     * @param tankID newValue
     */
    public void setTankID(int tankID) {
        this.tankID = tankID;
    }

    /**
     * Equals: returns true if the given Bullet object is equal with the actual one.
     * @param o the second Bullet object
     * @return returns true if the two objects are equal else returns false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bullet)) return false;
        Bullet bullet = (Bullet) o;
        return tankID == bullet.tankID &&
                Objects.equals(getPosition(), bullet.getPosition()) &&
                Objects.equals(getVelocity(), bullet.getVelocity());
    }

    /**
     * generates hash value for the Bullet object.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getVelocity(), tankID);
    }

    /**
     * Textual representation of the actual Bullet object
     * @return returns the generated textual representation.
     */
    @Override
    public String toString() {
        return "Bullet{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", tankID=" + tankID +
                '}';
    }
}
