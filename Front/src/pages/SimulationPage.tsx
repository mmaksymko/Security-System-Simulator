import styles from "./SimulationPage.module.css";
import Sidebar from "../components/Sidebar";
import Simulation from "../components/Simulation";

function SimulationPage() {
  return (
    <div className={styles.container}>
      <Sidebar />
      <Simulation />
    </div>
  );
}

export default SimulationPage;
