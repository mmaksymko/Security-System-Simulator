import ChangeFloorButton from "./ChangeFloorButton";
import styles from "./Simulation.module.css";

function Simulation() {
  return (
    <div className={styles.container}>
      <ChangeFloorButton />
      <p style={{ color: "#000" }}>simulation is here</p>
    </div>
  );
}

export default Simulation;
