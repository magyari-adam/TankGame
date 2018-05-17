package Engine;

import org.junit.Test;
import static org.junit.Assert.*;

public class EngineTest {

    @Test
    public void testCtorWithoutParams() {
        Engine engine = new Engine();
        assertEquals(engine.getTanks().size(), 2);
        assertEquals(engine.getBullets().size(), 0);
    }

    @Test
    public void testChangeTurretAngleByOneWithValidParams() {
        Engine engine = new Engine();
        int angle = engine.getTanks().get(0).getTurretAngle();
        engine.changeTurretAngleByOne(1, 0);
        assertEquals(engine.getTanks().get(0).getTurretAngle(), angle + 1);
    }

    @Test
    public void testChangeTurretAngleByOneWithInValidSmallTankID() {
        Engine engine = new Engine();
        int angle = engine.getTanks().get(0).getTurretAngle();
        engine.changeTurretAngleByOne(1, -1);
        assertEquals(engine.getTanks().get(0).getTurretAngle(), angle);
    }

    @Test
    public void testChangeTurretAngleByOneWithInValidBigTankID() {
        Engine engine = new Engine();
        int angle = engine.getTanks().get(0).getTurretAngle();
        engine.changeTurretAngleByOne(1, 100);
        assertEquals(engine.getTanks().get(0).getTurretAngle(), angle);
    }

    @Test
    public void testMoveWithWrongData1() {
        Engine engine = new Engine();
        int actualPos = engine.getTanks().get(0).getPosition().getX();
        engine.move(800);
        assertEquals(actualPos, engine.getTanks().get(0).getPosition().getX());
    }

    @Test
    public void testMoveWithWrongData2() {
        Engine engine = new Engine();
        int actualPos = engine.getTanks().get(0).getPosition().getX();
        engine.move(-300);
        assertEquals(actualPos, engine.getTanks().get(0).getPosition().getX());
    }

    @Test
    public void testMoveWithGoodData() {
        Engine engine = new Engine();
        engine.move(100);
        assertEquals(100, engine.getTanks().get(0).getPosition().getX());
    }

    @Test
    public void testMoveWithTwoParamsWrongTankID() {
        Engine engine = new Engine();
        int actualPos = engine.getTanks().get(0).getPosition().getX();
        engine.move(100, -1);
        assertEquals(actualPos, engine.getTanks().get(0).getPosition().getX());
    }

    @Test
    public void testMoveWithTwoParamsWrongTankID2() {
        Engine engine = new Engine();
        int actualPos = engine.getTanks().get(0).getPosition().getX();
        engine.move(100, 100);
        assertEquals(actualPos, engine.getTanks().get(0).getPosition().getX());
    }

    @Test
    public void testShootWithValidParams() {
        Engine engine = new Engine();
        int size = engine.getBullets().size();
        engine.shoot(10, 0);
        assertEquals(size + 1, engine.getBullets().size());
    }

}