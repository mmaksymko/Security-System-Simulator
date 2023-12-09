import ChangeFloorButton from "./ChangeFloorButton";
import SimulationLogic from "./Rooms/SimulationLogic";
import styles from "./InputFile.module.css";

function Simulation() {
  return (
    <div className={styles.container}>
      <ChangeFloorButton />
      <p style={{ color: "#000" }}><SimulationLogic /></p>
    </div>
  );
}

export default Simulation;