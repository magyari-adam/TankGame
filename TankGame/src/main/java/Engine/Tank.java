package Engine;

import java.util.Objects;

public class Tank {

    private Vec2D actualPosition;
    private int amountOfHealth;

    public Tank(){
        actualPosition = new Vec2D();
        amountOfHealth = 0;
    }

    public Tank(Vec2D actualPosition, int amountOfHealth){
        this.actualPosition = actualPosition;
        this.amountOfHealth = amountOfHealth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tank)) return false;
        Tank tank = (Tank) o;
        return amountOfHealth == tank.amountOfHealth &&
                Objects.equals(actualPosition, tank.actualPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actualPosition, amountOfHealth);
    }

    public Vec2D getActualPosition() {
        return new Vec2D(actualPosition);
    }

    public int getAmountOfHealth() {
        return amountOfHealth;
    }

    @Override
    public String toString() {
        return "Tank{" +
                "actualPosition=" + actualPosition +
                ", amountOfHealth=" + amountOfHealth +
                '}';
    }

    public void updatePosition(Vec2D param){
        actualPosition = param;
    }
}
