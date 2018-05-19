package Engine;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * This class represents the Tank type
 */
public class Tank implements Serializable{

    private static final long serialVersionUID = 70L;

    private Vec2D position;
    private int health;
    private int turretAngle;
    private int angleToTerrain;

    /**
     *
     * Tank ctor without params: creates a Tank object with default values.
     */
    public Tank(){
        position = new Vec2D();
        health = 0;
        turretAngle = 0;
        angleToTerrain = 0;
    }

    /**
     *
     * @param position defines the starting position of the tank
     * @param health initial health of the tank
     */
    public Tank(Vec2D position, int health){
        this.position = position;
        this.health = health;
        this.turretAngle = 0;
        this.angleToTerrain = 0;
    }


    /**
     *
     * @param position defines the starting position of the tank
     * @param health initial health of the tank
     * @param turretAngle defines the angle of the turret, should be between -20 and 50
     * @param angleToTerrain defines the tank's angle
     */
    public Tank(Vec2D position, int health, int turretAngle, int angleToTerrain){
        this.position = position;
        this.health = health;
        this.turretAngle = turretAngle;
        this.angleToTerrain = angleToTerrain;
    }


    /**
     *
     * @return returns the position of the tank
     */
    public Vec2D getPosition() {
        return new Vec2D(position);
    }

    /**
     *
     * @param position sets the position of the tank
     */
    public void setPosition(Vec2D position) {
        this.position = position;
    }


    /**
     *
     * @return returns the health of the tank
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health sets the health of the tank
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @return returns the angle of the turret
     */
    public int getTurretAngle() {
        return turretAngle;
    }

    /**
     *
     * @param turretAngle set the turretangle to the given value. The parameter should be between -20 and 50.
     */
    public void setTurretAngle(int turretAngle) {
        if (turretAngle > 50){
            this.turretAngle = 50;
        }else if(turretAngle < -20){
            this.turretAngle = -20;
        }else{
            this.turretAngle = turretAngle;
        }
    }

    /**
     *
     * @return returns the angleToTerrain
     */
    public int getAngleToTerrain() {
        return angleToTerrain;
    }

    /**
     *
     * @param angleToTerrain sets the angle to the given value
     */
    public void setAngleToTerrain(int angleToTerrain) {
        this.angleToTerrain = angleToTerrain;
    }

    /**
     *
     * @param o the second tank, should be type of Tank
     * @return returns true if two Tanks are equal in all of their parameters, else returns false
     */
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

    /**
     *
     * @return generates hash value for the tank
     */
    @Override
    public int hashCode() {
        return Objects.hash(position, health, turretAngle, angleToTerrain);
    }

    /**
     *
     * @return Textual representation of the actual Tank object
     */
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
