import { BuildingComponent } from "./BuildingLevel";
import { Sensor } from "./Sensor";
enum RoomType {
    APARTMENT_ROOM,
    APARTMENT_BATHROOM,
    OFFICE_ROOM,
    OFFICE_RESTROOM,
    KITCHEN
}
class Room implements BuildingComponent{
    roomType: string;
    roomNumber: number;
    windows: number;
    doors: number;
    length: number;
    width: number;
    sensors: Sensor[];
    logs: string[];
    constructor(roomType: string, 
                roomNumber: number, 
                windows: number, 
                doors: number, 
                length: number,
                width: number,
                sensors: Sensor[]){
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.windows = windows;
        this.doors = doors;
        this.length = length;
        this.width = width;
        this.sensors = sensors;
        this.logs = [];
    }
    addSensor(sensor: Sensor){
        this.sensors.push(sensor);
    }
}

export { Room, RoomType };