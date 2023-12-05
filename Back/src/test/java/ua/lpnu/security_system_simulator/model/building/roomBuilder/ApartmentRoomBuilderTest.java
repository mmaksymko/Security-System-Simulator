package ua.lpnu.security_system_simulator.model.building.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRoomBuilderTest {
    @Test
    public void setWindowsBeforeAreaIsSet_throwsException(){
        ApartmentRoomBuilder rb = new ApartmentRoomBuilder();
        assertThrows(NullPointerException.class, () -> {
            rb.setWindows(2);
        });
    }
    @Test
    public void setDoorsBeforeAreaIsSet_throwsException(){
        ApartmentRoomBuilder rb = new ApartmentRoomBuilder();
        assertThrows(NullPointerException.class, () -> {
            rb.setDoors(2);
        });
    }
    @Test
    public void setAreaLessOrEqualToZero_throwsException(){
        ApartmentRoomBuilder rb = new ApartmentRoomBuilder();
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
        ApartmentRoomBuilder rb = new ApartmentRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setRoomNumber(0);
        });
        assertThrows(Exception.class, () -> {
            rb.setRoomNumber(-3);
        });
    }
    @Test
    public void setWindowsLessThanZero_throwsException(){

        ApartmentRoomBuilder rb = new ApartmentRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setWindows(-3);
        });
    }
    @Test
    public void setDoorsLessThanOne_throwsException(){

        ApartmentRoomBuilder rb = new ApartmentRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setWindows(-3);
        });
        assertThrows(Exception.class, () -> {
            rb.setWindows(0);
        });
    }
    @Test
    public void setMoreThanMaxAndLessThanMinWindows_throwsException(){
        ApartmentRoomBuilder rb = new ApartmentRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setArea(20);
            rb.setWindows(4);
        });
        assertThrows(Exception.class, () -> {
            rb.setArea(30);
            rb.setWindows(0);
        });
    }
    @Test
    public void getResultSetNoDoor_throwsException(){
        ApartmentRoomBuilder rb = new ApartmentRoomBuilder();
        assertThrows(Exception.class, () -> {
            rb.setArea(20);
            rb.getResult();
        });
    }
}