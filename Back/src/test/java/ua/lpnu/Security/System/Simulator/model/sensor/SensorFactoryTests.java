package ua.lpnu.Security.System.Simulator.model.sensor;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.sensor.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ua.lpnu.security_system_simulator.model.sensor.SensorFactory.class)
public class SensorFactoryTests {
    @Autowired
    private SensorFactory sensorFactory;
    @Test
    public void sensorFactoryReturnsCorrectFireSensor(){
        Assertions.assertEquals(new FireSensor(15), sensorFactory.createSensor(EventType.FIRE, 15));
        Assertions.assertEquals(new FireSensor(Integer.MAX_VALUE), sensorFactory.createSensor(EventType.FIRE, Integer.MAX_VALUE));
        Assertions.assertEquals(new FireSensor(Integer.MAX_VALUE-1), sensorFactory.createSensor(EventType.FIRE, Integer.MAX_VALUE-1));
        Assertions.assertEquals(new FireSensor(1), sensorFactory.createSensor(EventType.FIRE, 1));
    }
    @Test
    public void sensorFactoryReturnsCorrectFloodingSensor(){
        Assertions.assertEquals(new FloodingSensor(15), sensorFactory.createSensor(EventType.FLOODING, 15));
        Assertions.assertEquals(new FloodingSensor(Integer.MAX_VALUE), sensorFactory.createSensor(EventType.FLOODING, Integer.MAX_VALUE));
        Assertions.assertEquals(new FloodingSensor(Integer.MAX_VALUE-1), sensorFactory.createSensor(EventType.FLOODING, Integer.MAX_VALUE-1));
        Assertions.assertEquals(new FloodingSensor(1), sensorFactory.createSensor(EventType.FLOODING, 1));
    }

    @Test
    public void sensorFactoryReturnsCorrectOpenedWindowSensor(){
        Assertions.assertEquals(new OpenedWindowSensor(15), sensorFactory.createSensor(EventType.OPENED_WINDOW, 15));
        Assertions.assertEquals(new OpenedWindowSensor(Integer.MAX_VALUE), sensorFactory.createSensor(EventType.OPENED_WINDOW, Integer.MAX_VALUE));
        Assertions.assertEquals(new OpenedWindowSensor(Integer.MAX_VALUE-1), sensorFactory.createSensor(EventType.OPENED_WINDOW, Integer.MAX_VALUE-1));
        Assertions.assertEquals(new OpenedWindowSensor(1), sensorFactory.createSensor(EventType.OPENED_WINDOW, 1));
    }

    @Test
    public void sensorFactoryReturnsCorrectOpenedDoorSensor(){
        Assertions.assertEquals(new OpenedDoorSensor(15), sensorFactory.createSensor(EventType.OPENED_DOOR, 15));
        Assertions.assertEquals(new OpenedDoorSensor(Integer.MAX_VALUE), sensorFactory.createSensor(EventType.OPENED_DOOR, Integer.MAX_VALUE));
        Assertions.assertEquals(new OpenedDoorSensor(Integer.MAX_VALUE-1), sensorFactory.createSensor(EventType.OPENED_DOOR, Integer.MAX_VALUE-1));
        Assertions.assertEquals(new OpenedDoorSensor(1), sensorFactory.createSensor(EventType.OPENED_DOOR, 1));
    }

    @Test
    public void sensorFactoryReturnsCorrectGasLeakSensor(){
        Assertions.assertEquals(new GasLeakSensor(15), sensorFactory.createSensor(EventType.GAS_LEAK, 15));
        Assertions.assertEquals(new GasLeakSensor(Integer.MAX_VALUE), sensorFactory.createSensor(EventType.GAS_LEAK, Integer.MAX_VALUE));
        Assertions.assertEquals(new GasLeakSensor(Integer.MAX_VALUE-1), sensorFactory.createSensor(EventType.GAS_LEAK, Integer.MAX_VALUE-1));
        Assertions.assertEquals(new GasLeakSensor(1), sensorFactory.createSensor(EventType.GAS_LEAK, 1));
    }
    @Test
    public void sensorFactoryReturnsCorrectMotionSensor(){
        Assertions.assertEquals(new MotionSensor(15), sensorFactory.createSensor(EventType.MOTION, 15));
        Assertions.assertEquals(new MotionSensor(Integer.MAX_VALUE), sensorFactory.createSensor(EventType.MOTION, Integer.MAX_VALUE));
        Assertions.assertEquals(new MotionSensor(Integer.MAX_VALUE-1), sensorFactory.createSensor(EventType.MOTION, Integer.MAX_VALUE-1));
        Assertions.assertEquals(new MotionSensor(1), sensorFactory.createSensor(EventType.MOTION, 1));
    }
    @Test
    public void sensorFactoryReturnsNullWhenEventTypeIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> sensorFactory.createSensor(null, 15));
    }

    @Test
    public void sensorFactoryReturnsNullWhenCoverageAreaIsNegative(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> sensorFactory.createSensor(EventType.FIRE, -15));
    }
}
