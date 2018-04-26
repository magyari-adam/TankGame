package Engine;

import java.io.Serializable;
import java.util.Objects;

public class Tank implements Serializable{

    private static final long serialVersionUID = 70L;

    private Vec2D position;
    private int health;
    private int turretAngle;
    private int angleToTerrain;

    public Tank(){
        position = new Vec2D();
        health = 0;
        turretAngle = 0;
        angleToTerrain = 0;
    }

    public Tank(Vec2D position, int health){
        this.position = position;
        this.health = health;
        this.turretAngle = 0;
        this.angleToTerrain = 0;
    }

    public Tank(Vec2D position, int health, int turretAngle, int angleToTerrain){
        this.position = position;
        this.health = health;
        this.turretAngle = turretAngle;
        this.angleToTerrain = angleToTerrain;
    }

    public Vec2D getPosition() {
        return new Vec2D(position);
    }

    public void setPosition(Vec2D position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getTurretAngle() {
        return turretAngle;
    }

    public void setTurretAngle(int turretAngle) {
        if (turretAngle > 40){
            this.turretAngle = 40;
        }else if(turretAngle < -15){
            this.turretAngle = -15;
        }else{
            this.turretAngle = turretAngle;
        }
    }

    public int getAngleToTerrain() {
        return angleToTerrain;
    }

    public void setAngleToTerrain(int angleToTerrain) {
        this.angleToTerrain = angleToTerrain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tank tank = (Tank) o;
        return health == tank.health &&
                turretAngle == tank.turretAngle &&
                angleToTerrain == tank.angleToTerrain &&
                Objects.equals(position, tank.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, health, turretAngle, angleToTerrain);
    }

    @Override
    public String toString() {
        return "Tank{" +
                "position=" + position +
                ", health=" + health +
                ", turretAngle=" + turretAngle +
                ", angleToTerrain=" + angleToTerrain +
                '}';
    }
}
