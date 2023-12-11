import ChangeFloorButton from "../ChangeFloorButton/ChangeFloorButton";
import SimulationLogic from "../Rooms/SimulationLogic";
import styles from "./Simulation.module.css";
import { useState, useEffect } from "react";
import StartSimulationButton from "../StartSimulationButton";
import ContinueSimulationButton from "../ContinueSimulationButton";
import { useBuildingContext } from "../../BuildingContext";

interface Building {
  id: number;
  name: string;
}

function Simulation() {
  const [simulationState, setSimulationState] = useState("start");
  const [eventSource, setEventSource] = useState<EventSource | null>(null);
  const { buildingName, setBuildingName, buildingId, setBuildingId } =
    useBuildingContext();

  useEffect(() => {
    const fetchBuildingId = async () => {
      try {
        const response = await fetch("http://localhost:8080/buildings");
        const buildings: Building[] = await response.json();
        console.log("Buildings:", buildings);

        const selectedBuilding = buildings.find(
          (building) => building.name === buildingName
        );

        if (selectedBuilding) {
          setBuildingId(selectedBuilding.id);
          console.log("Selected Building:", selectedBuilding);
        }
      } catch (error) {
        console.error("Error fetching buildings:", error);
      }
    };

    fetchBuildingId();
  }, [buildingName, setBuildingId]);

  const handleStartClick = () => {
    setSimulationState("continue");
    console.log("building id is: " + buildingId);
    const newEventSource = new EventSource(
      `http://localhost:8080/simulation/${buildingId}`
    );

    newEventSource.onmessage = function (event) {
      console.log("Received message: " + event.data);
    };

    newEventSource.addEventListener("Update", function (event) {
      console.log(event.data);
    });

    newEventSource.onerror = function (event) {
      console.error("Error occurred: ", event);
      newEventSource.close();
    };

    newEventSource.addEventListener("complete", (e) => {
      console.log(e.data);
      newEventSource.close();
    });

    setEventSource(newEventSource);
  };

  const handleStopClick = () => {
    setSimulationState("stop");
    fetch("http://localhost:8080/simulation/pause");
  };
  const handleContinueClick = () => {
    setSimulationState("continue");
    fetch("http://localhost/8080/simulation/resume");
  };

  const resetSimulation = () => {
    setSimulationState("start");
  };

  return (
    <div className={styles.container}>
      <ChangeFloorButton />
      {simulationState === "start" && (
        <StartSimulationButton onClick={handleStartClick} />
      )}
      {simulationState === "stop" && (
        <ContinueSimulationButton onClick={handleContinueClick} />
      )}
      <div className={styles.simulation} onClick={handleStopClick}>
        <SimulationLogic />
      </div>
      {simulationState === "continue" && (
        <p className={styles.message}>
          Press on the floor map to stop simulation
        </p>
      )}
      {simulationState === "stop" && (
        <p className={styles.message}>
          Press on the floor map to continue simulation
        </p>
      )}
    </div>
  );
}

export default Simulation;
