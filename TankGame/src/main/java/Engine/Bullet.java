package Engine;

import java.io.Serializable;
import java.util.Objects;

public class Bullet implements Serializable{

    private Vec2D position;
    private Vec2D velocity;
    private int tankID;

    public Bullet(){
        this.position = new Vec2D();
        this.velocity = new Vec2D();
        this.tankID = 0;
    }

    public Bullet(Vec2D position, Vec2D velocity, int tankID) {
        this.position = position;
        this.velocity = velocity;
        this.tankID = tankID;
    }

    public Vec2D getPosition() {
        return new Vec2D(position);
    }

    public void setPosition(Vec2D position) {
        this.position = position;
    }

    public Vec2D getVelocity() {
        return new Vec2D(velocity);
    }

    public void setVelocity(Vec2D velocity) {
        this.velocity = velocity;
    }

    public int getTankID() {
        return tankID;
    }

    public void setTankID(int tankID) {
        this.tankID = tankID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bullet)) return false;
        Bullet bullet = (Bullet) o;
        return tankID == bullet.tankID &&
                Objects.equals(getPosition(), bullet.getPosition()) &&
                Objects.equals(getVelocity(), bullet.getVelocity());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPosition(), getVelocity(), tankID);
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", tankID=" + tankID +
                '}';
    }
}
