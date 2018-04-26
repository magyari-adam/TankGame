package Engine;

import java.io.Serializable;
import java.util.Objects;

public class Bullet implements Serializable{

    private Vec2D position;
    private Vec2D velocity;

    public Bullet(){
        this.position = new Vec2D();
        this.velocity = new Vec2D();
    }

    public Bullet(Vec2D position, Vec2D velocity) {
        this.position = position;
        this.velocity = velocity;
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

    @Override
    public String toString() {
        return "Bullet{" +
                "position=" + position +
                ", velocity=" + velocity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bullet bullet = (Bullet) o;
        return Objects.equals(position, bullet.position) &&
                Objects.equals(velocity, bullet.velocity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getVelocity());
    }
}
