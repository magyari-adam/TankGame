package Engine;

import java.util.Objects;

public class Bullet {

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

    public Vec2D getVelocity() {
        return new Vec2D(velocity);
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
        if (!(o instanceof Bullet)) return false;
        Bullet bullet = (Bullet) o;
        return Objects.equals(getPosition(), bullet.getPosition()) &&
                Objects.equals(getVelocity(), bullet.getVelocity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getVelocity());
    }
}
