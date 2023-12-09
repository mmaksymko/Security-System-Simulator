package ua.lpnu.security_system_simulator.model.building.roomBuilder;

import org.junit.jupiter.api.Test;
import ua.lpnu.security_system_simulator.model.building.builder.room.OfficeRoomBuilder;

import static org.junit.jupiter.api.Assertions.*;

class OfficeRoomBuilderTest {
    @Test
    public void setWindowsBeforeAreaIsSet_throwsException(){
        OfficeRoomBuilder rb = new OfficeRoomBuilder();
        assertThrows(NullPointerException.class, () -> {
            rb.setWindows(2);
        });
    }
    @Test
    public void setDoorsBeforeAreaIsSet_throwsException(){
        OfficeRoomBuilder rb = new OfficeRoomBuilder();
        assertThrows(NullPointerException.class, () -> {
            rb.setDoors(2);
        });
    }
    @Test
    public void setAreaLessOrEqualToZero_throwsException(){
        OfficeRoomBuilder rb = new OfficeRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setArea(0);
        });
        assertThrows(Exception.class, () -> {
            rb.setArea(-3);
        });
    }
    @Test
    public void setRoomNumberLessOrEqualToZero_throwsException(){
        //TODO як узагалі встановлюються номери кімнат????
        OfficeRoomBuilder rb = new OfficeRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setRoomNumber(0);
        });
        assertThrows(Exception.class, () -> {
            rb.setRoomNumber(-3);
        });
    }
    @Test
    public void setWindowsLessThanZero_throwsException(){

        OfficeRoomBuilder rb = new OfficeRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setWindows(-3);
        });
    }
    @Test
    public void setDoorsLessThanOne_throwsException(){

        OfficeRoomBuilder rb = new OfficeRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setWindows(-3);
        });
        assertThrows(Exception.class, () -> {
            rb.setWindows(0);
        });
    }
    @Test
    public void setMoreThanMaxAndLessThanMinWindows_throwsException(){
        OfficeRoomBuilder rb = new OfficeRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setArea(60);
            rb.setWindows(19);
        });
        assertThrows(Exception.class, () -> {
            rb.setArea(30);
            rb.setWindows(2);
        });
    }
    @Test
    public void getResultSetNoDoor_throwsException(){
        OfficeRoomBuilder rb = new OfficeRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setArea(20);
            rb.getResult();
        });
    }
}