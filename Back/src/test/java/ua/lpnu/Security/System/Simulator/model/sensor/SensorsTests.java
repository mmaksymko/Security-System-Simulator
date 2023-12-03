package ua.lpnu.Security.System.Simulator.model.sensor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.sensor.*;

public class SensorsTests {
    @Test
    public void fireSensorWithCorrectParametersCreatedCorrectly() {
        Assertions.assertEquals(1, new FireSensor(1).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE, new FireSensor(Integer.MAX_VALUE).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE - 1, new FireSensor(Integer.MAX_VALUE - 1).getCoverageArea());
    }

    @Test
    public void floodingSensorWithCorrectParametersCreatedCorrectly() {
        Assertions.assertEquals(1, new FloodingSensor(1).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE, new FloodingSensor(Integer.MAX_VALUE).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE - 1, new FloodingSensor(Integer.MAX_VALUE - 1).getCoverageArea());
    }

    @Test
    public void openedWindowWithCorrectParametersCreatedCorrectly() {
        Assertions.assertEquals(1, new OpenedWindowSensor(1).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE, new OpenedWindowSensor(Integer.MAX_VALUE).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE - 1, new OpenedWindowSensor(Integer.MAX_VALUE - 1).getCoverageArea());
    }
    @Test
    public void openedDoorWithCorrectParametersCreatedCorrectly() {
        Assertions.assertEquals(1, new OpenedDoorSensor(1).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE, new OpenedDoorSensor(Integer.MAX_VALUE).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE - 1, new OpenedDoorSensor(Integer.MAX_VALUE - 1).getCoverageArea());
    }

    @Test
    public void gasLeakWithCorrectParametersCreatedCorrectly() {
        Assertions.assertEquals(1, new GasLeakSensor(1).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE, new GasLeakSensor(Integer.MAX_VALUE).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE - 1, new GasLeakSensor(Integer.MAX_VALUE - 1).getCoverageArea());
    }

    @Test
    public void motionWithCorrectParametersCreatedCorrectly() {
        Assertions.assertEquals(1, new MotionSensor(1).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE, new MotionSensor(Integer.MAX_VALUE).getCoverageArea());
        Assertions.assertEquals(Integer.MAX_VALUE - 1, new MotionSensor(Integer.MAX_VALUE - 1).getCoverageArea());
    }

    @Test
    public void creatingFireSensorWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FireSensor(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FireSensor(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FireSensor(Integer.MIN_VALUE + 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FireSensor(Integer.MIN_VALUE));
    }

    @Test
    public void creatingFloodingSensorWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FloodingSensor(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FloodingSensor(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FloodingSensor(Integer.MIN_VALUE + 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FloodingSensor(Integer.MIN_VALUE));
    }

    @Test
    public void creatingOpenedWindowWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedWindowSensor(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedWindowSensor(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedWindowSensor(Integer.MIN_VALUE + 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedWindowSensor(Integer.MIN_VALUE));
    }
    @Test
    public void creatingOpenedDoorWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedDoorSensor(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedDoorSensor(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedDoorSensor(Integer.MIN_VALUE + 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedDoorSensor(Integer.MIN_VALUE));
    }
    @Test
    public void creatingGasLeakWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GasLeakSensor(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GasLeakSensor(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GasLeakSensor(Integer.MIN_VALUE + 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GasLeakSensor(Integer.MIN_VALUE));
    }

    @Test
    public void creatingMotionWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MotionSensor(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MotionSensor(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MotionSensor(Integer.MIN_VALUE + 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MotionSensor(Integer.MIN_VALUE));
    }

    @Test
    public void settingFireSensorCoverageAreaWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FireSensor(1).setCoverageArea(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FireSensor(1).setCoverageArea(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FireSensor(1).setCoverageArea(Integer.MIN_VALUE+1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FireSensor(1).setCoverageArea(Integer.MIN_VALUE));
    }
    @Test
    public void settingFloodingSensorCoverageAreaWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FloodingSensor(1).setCoverageArea(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FloodingSensor(1).setCoverageArea(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FloodingSensor(1).setCoverageArea(Integer.MIN_VALUE+1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FloodingSensor(1).setCoverageArea(Integer.MIN_VALUE));
    }
    @Test
    public void settingOpenedWindowSensorCoverageAreaWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedWindowSensor(1).setCoverageArea(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedWindowSensor(1).setCoverageArea(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedWindowSensor(1).setCoverageArea(Integer.MIN_VALUE+1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedWindowSensor(1).setCoverageArea(Integer.MIN_VALUE));
    }
    @Test
    public void settingOpenedDoorSensorCoverageAreaWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedDoorSensor(1).setCoverageArea(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedDoorSensor(1).setCoverageArea(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedDoorSensor(1).setCoverageArea(Integer.MIN_VALUE+1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OpenedDoorSensor(1).setCoverageArea(Integer.MIN_VALUE));
    }
    @Test
    public void settingGasLeakSensorCoverageAreaWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GasLeakSensor(1).setCoverageArea(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GasLeakSensor(1).setCoverageArea(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GasLeakSensor(1).setCoverageArea(Integer.MIN_VALUE+1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GasLeakSensor(1).setCoverageArea(Integer.MIN_VALUE));
    }
    @Test
    public void settingMotionSensorCoverageAreaWithIncorrectParametersThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MotionSensor(1).setCoverageArea(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MotionSensor(1).setCoverageArea(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MotionSensor(1).setCoverageArea(Integer.MIN_VALUE+1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MotionSensor(1).setCoverageArea(Integer.MIN_VALUE));
    }

    @Test 
    public void fireSensorReturnsCorrectEventType(){
        Assertions.assertEquals(EventType.FIRE, new FireSensor(1).getType());
    }
    @Test
    public void floodingSensorReturnsCorrectEventType(){
        Assertions.assertEquals(EventType.FLOODING, new FloodingSensor(1).getType());
    }
    @Test
    public void openedWindowSensorReturnsCorrectEventType(){
        Assertions.assertEquals(EventType.OPENED_WINDOW, new OpenedWindowSensor(1).getType());
    }
    @Test
    public void openedDoorSensorReturnsCorrectEventType(){
        Assertions.assertEquals(EventType.OPENED_DOOR, new OpenedDoorSensor(1).getType());
    }
    @Test
    public void gasLeakSensorReturnsCorrectEventType(){
        Assertions.assertEquals(EventType.GAS_LEAK, new GasLeakSensor(1).getType());
    }
    @Test
    public void motionSensorReturnsCorrectEventType(){
        Assertions.assertEquals(EventType.MOTION, new MotionSensor(1).getType());
    }

}
