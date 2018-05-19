package Engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class TankTest {

    @Test
    public void testSetTurretAngleWithWrongParameters() {
        Tank tank = new Tank();
        tank.setTurretAngle(-25);
        assertEquals(tank.getTurretAngle(),-20);
    }

    @Test
    public void testSetTurretAngleWithWrongParameters2() {
        Tank tank = new Tank();
        tank.setTurretAngle(100);
        assertEquals(tank.getTurretAngle(),50);
    }

    @Test
    public void testSetTurretAngleWithGoodParameters2() {
        Tank tank = new Tank();
        tank.setTurretAngle(10);
        assertEquals(tank.getTurretAngle(),10);
    }

    @Test
    public void testEquals(){
        Tank tank = new Tank();
        Tank tank1 = new Tank();
        assertEquals(tank.equals(tank1),true);
    }

    @Test
    public void testEquals2(){
        Tank tank = new Tank(new Vec2D(),10,10,10);
        Tank tank1 = new Tank(new Vec2D(),10,10,10);
        assertEquals(tank.equals(tank1),true);
    }

    @Test
    public void testEquals3(){
        Tank tank = new Tank(new Vec2D(),10,10,10);
        Tank tank1 = new Tank(new Vec2D(),1,10,10);
        assertEquals(tank.equals(tank1),false);
    }
}