package ua.lpnu.security_system_simulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.building.roomBuilder.ApartmentRoomBuilder;
import ua.lpnu.security_system_simulator.model.building.roomBuilder.Builder;
import ua.lpnu.security_system_simulator.model.building.roomBuilder.RoomDirector;
import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventFactory;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.sensor.*;
import ua.lpnu.security_system_simulator.model.system.EventLog;
import ua.lpnu.security_system_simulator.model.system.Simulator;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SecuritySystemSimulatorApplication {


	public static void main(String[] args) {
		SpringApplication.run(SecuritySystemSimulatorApplication.class, args);
	}
/*
	@Autowired
	@Bean
	CommandLineRunner preLoadMongo(BuildingRepository repo, EventFactory eventFactory) throws Exception {
		return args -> {


			Simulator simulator = new Simulator();
		BuildingLevel building = new BuildingLevel("Building 1");
		simulator.setBuilding(building);

		List<Sensor> sensors1 = List.of(new FireSensor(10), new GasLeakSensor(15));
		List<Sensor> sensors2 = List.of(new FireSensor(10), new GasLeakSensor(15));
		List<Sensor> sensors3 = List.of(new OpenedDoorSensor(10), new OpenedWindowSensor(15));
		List<Sensor> sensors4 = List.of(new OpenedDoorSensor(10), new MotionSensor(15));
		List<Sensor> sensors5 = List.of(new FireSensor(10),new FireSensor(10),new FireSensor(10));

		Room room1 = new Room(RoomType.APARTMENT_ROOM, 1,2,3,4, sensors1, new ArrayList<>());
		Room room2 = new Room(RoomType.OFFICE_ROOM, 2,2,3,4, sensors3, new ArrayList<>());
		Room room3 = new Room(RoomType.APARTMENT_ROOM, 3,2,3,4, sensors1, new ArrayList<>());
		Room room4 = new Room(RoomType.APARTMENT_ROOM, 4,2,3,4, sensors2, new ArrayList<>());
		Room room5 = new Room(RoomType.KITCHEN, 5,2,3,4, sensors5, new ArrayList<>());
		Room room6 = new Room(RoomType.APARTMENT_ROOM, 6,2,3,4, sensors1, new ArrayList<>());
		Room room7 = new Room(RoomType.APARTMENT_ROOM, 7,2,3,4, sensors2, new ArrayList<>());
		Room room8 = new Room(RoomType.APARTMENT_BATHROOM, 8,2,3,4, sensors3, new ArrayList<>());
		Room room9 = new Room(RoomType.OFFICE_RESTROOM, 9,2,3,4, sensors4, new ArrayList<>());
		Room room10 = new Room(RoomType.APARTMENT_ROOM, 10,2,3,4, sensors5, new ArrayList<>());
		Room room11 = new Room(RoomType.APARTMENT_ROOM, 1,2,3,4, sensors1, new ArrayList<>());
		Room room12 = new Room(RoomType.OFFICE_ROOM, 2,2,3,4, sensors3, new ArrayList<>());
		Room room13 = new Room(RoomType.APARTMENT_ROOM, 3,2,3,4, sensors1, new ArrayList<>());
		Room room14 = new Room(RoomType.APARTMENT_ROOM, 4,2,3,4, sensors2, new ArrayList<>());
		Room room15 = new Room(RoomType.KITCHEN, 5,2,3,4, sensors5, new ArrayList<>());
		Room room16 = new Room(RoomType.APARTMENT_ROOM, 6,2,3,4, sensors1, new ArrayList<>());
		Room room17 = new Room(RoomType.APARTMENT_ROOM, 7,2,3,4, sensors2, new ArrayList<>());
		Room room18 = new Room(RoomType.APARTMENT_BATHROOM, 8,2,3,4, sensors3, new ArrayList<>());
		Room room19 = new Room(RoomType.OFFICE_RESTROOM, 9,2,3,4, sensors4, new ArrayList<>());
		Room room20 = new Room(RoomType.APARTMENT_ROOM, 10,2,3,4, sensors5, new ArrayList<>());

		BuildingLevel floor1 = new BuildingLevel("Floor 1");
		floor1.addComponent(room1);
		floor1.addComponent(room2);
		BuildingLevel floor2 = new BuildingLevel("Floor 2");
		floor2.addComponent(room3);
		floor2.addComponent(room4);
		floor2.addComponent(room5);
		floor2.addComponent(room6);
		BuildingLevel floor3 = new BuildingLevel("Floor 3");
		floor3.addComponent(room7);
		floor3.addComponent(room8);
		BuildingLevel floor4 = new BuildingLevel("Floor 4");
		floor4.addComponent(room9);
		BuildingLevel floor5 = new BuildingLevel("Floor 5");
		floor5.addComponent(room10);
		floor5.addComponent(room11);
		floor5.addComponent(room12);
		BuildingLevel floor6 = new BuildingLevel("Floor 6");
		floor6.addComponent(room13);
		floor6.addComponent(room14);
		floor6.addComponent(room15);
		BuildingLevel floor7 = new BuildingLevel("Floor 7");
		floor7.addComponent(room16);
		floor7.addComponent(room17);
		floor7.addComponent(room18);
		BuildingLevel floor8 = new BuildingLevel("Floor 8");
		floor8.addComponent(room19);
		floor8.addComponent(room20);

		building.addComponent(floor1);
		building.addComponent(floor2);
		building.addComponent(floor3);
		building.addComponent(floor4);
		building.addComponent(floor5);
		building.addComponent(floor6);
		building.addComponent(floor7);
		building.addComponent(floor8);

		List<Event> events1 = List.of(eventFactory.createEvent(EventType.FIRE, room1, DangerLevel.CONSIDERABLE),
				eventFactory.createEvent(EventType.FIRE, room1, DangerLevel.CONSIDERABLE));
		List<Event> events2 = List.of(eventFactory.createEvent(EventType.FIRE, room17, DangerLevel.CONSIDERABLE),
				eventFactory.createEvent(EventType.FLOODING, room8, DangerLevel.CONSIDERABLE));
		List<Event> events3 = List.of(eventFactory.createEvent(EventType.FIRE, room14, DangerLevel.CONSIDERABLE),
				eventFactory.createEvent(EventType.OPENED_WINDOW, room2, DangerLevel.CONSIDERABLE));
		List<Event> events4 = List.of(eventFactory.createEvent(EventType.FIRE, room12, DangerLevel.CONSIDERABLE),
				eventFactory.createEvent(EventType.OPENED_DOOR, room7, DangerLevel.CONSIDERABLE));
		List<Event> events5 = List.of(eventFactory.createEvent(EventType.FIRE, room1, DangerLevel.CONSIDERABLE),
				eventFactory.createEvent(EventType.MOTION, room2, DangerLevel.CONSIDERABLE));
		List<Event> events6 = List.of(eventFactory.createEvent(EventType.FIRE, room6, DangerLevel.CONSIDERABLE),
				eventFactory.createEvent(EventType.GAS_LEAK, room4, DangerLevel.CONSIDERABLE));


		 	repo.deleteAll();
			repo.save(building);
//			repo.save(new BuildingLevel("Building 2", building.getComponents()));
//			repo.save(new BuildingLevel("Building 3", building.getComponents()));
//			repo.save(new BuildingLevel("Building 4", building.getComponents()));
//			repo.save(new BuildingLevel("Building 5", building.getComponents()));
//			repo.save(new BuildingLevel("Building 6", building.getComponents()));
//			repo.save(new BuildingLevel("Building 7", building.getComponents()));
		};
	}
*/
}
