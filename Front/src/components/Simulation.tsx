import ChangeFloorButton from "./ChangeFloorButton";
import SimulationLogic from "./Rooms/SimulationLogic";
import styles from "./InputFile.module.css";
import React, { useState } from "react";
import ChangeFloorButton from "./ChangeFloorButton";
import StartSimulationButton from "./StartSimulationButton";
import ContinueSimulationButton from "./ContinueSimulationButton";
import styles from "./Simulation.module.css";

function Simulation() {
  const [simulationState, setSimulationState] = useState("start");

  const handleStartClick = () => {
    setSimulationState("continue");
  };

  const handleStopClick = () => {
    setSimulationState("stop");
  };
  const handleContinueClick = () => {
    setSimulationState("continue");
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