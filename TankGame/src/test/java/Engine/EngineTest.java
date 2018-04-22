package Engine;

import org.junit.Test;
import static org.junit.Assert.*;

public class EngineTest {

    @Test
    public void testCtorWithoutParams(){
        Engine engine = new Engine();
        assertEquals(engine.getTanks().size(),2);
        assertEquals(engine.getBullets().size(),1);
    }

    @Test
    public void recognizeEventFromOutside(){
        Engine engine = new Engine();
        //TODO
    }

}