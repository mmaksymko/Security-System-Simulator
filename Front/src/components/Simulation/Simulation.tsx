import ChangeFloorButton from "../ChangeFloorButton/ChangeFloorButton";
import styles from "./Simulation.module.css";
import { useState, useEffect } from "react";
import StartSimulationButton from "../StartSimulationButton";
import ContinueSimulationButton from "../ContinueSimulationButton";
import { useBuildingContext } from "../../BuildingContext";
import SimulationLogic from "../Rooms/SimulationLogic";

interface Building {
  id: number;
  name: string;
}

interface LogEntry {
  dangerLevel: string;
  eventType: string;
  location: string;
  happenedAt: string;
  result: boolean;
}

interface SimulationProps {
  logData: LogEntry[];
  onLogDataUpdate: (newLogData: LogEntry[]) => void;
}

const Simulation: React.FC<SimulationProps> = ({
  logData,
  onLogDataUpdate,
}) => {
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
  }, [buildingName, setBuildingId, onLogDataUpdate, logData]);

  const handleStartClick = () => {
    setSimulationState("continue");
    console.log("building id is: " + localStorage.getItem("buildingId"));
    const newEventSource = new EventSource(
      `http://localhost:8080/simulation/${localStorage.getItem("buildingId")}`
    );

    newEventSource.onmessage = function (event) {
      console.log("Received message: " + event.data);
      const logEntry: LogEntry = JSON.parse(event.data);
      onLogDataUpdate([...logData, logEntry]);
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
    fetch("http://localhost:8080/simulation/resume");
  };

  const resetSimulation = () => {
    setSimulationState("start");
  };

  return (
    <div className={styles.container}>
      <ChangeFloorButton buildingId={buildingId} />
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
};

export default Simulation;
