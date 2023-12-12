enum EventType {
    SIMULATION_START,
    FIRE,
    FLOODING,
    GAS_LEAK,
    MOTION,
    OPENED_WINDOW,
    OPENED_DOOR,
    FIRE_REACTION,
    FLOODING_REACTION,
    GAS_LEAK_REACTION,
    MOTION_REACTION,
    OPENED_WINDOW_REACTION,
    OPENED_DOOR_REACTION
}
class Sensor {
    coverageArea: number;
    type: string;
    constructor(coverageArea: number, type: string) {
        this.coverageArea = coverageArea;
        this.type = type;
    }
}

export { Sensor, EventType };