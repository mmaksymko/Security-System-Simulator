import styles from "./SimulationPage.module.css";
import Sidebar from "../components/Sidebar";
import Simulation from "../components/Simulation";
import "../components/Rooms/RoomsStyle.css"

function SimulationPage() {
  return (
    <div className={styles.container}>
      <Sidebar />
      <div id="floorPlan" className="App">
        <Simulation />
      </div>
    </div>
  );
}

export default SimulationPage;
