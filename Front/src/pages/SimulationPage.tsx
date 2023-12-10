import React from "react";
import styles from "./SimulationPage.module.css";
import Sidebar from "../components/Sidebar";
import Simulation from "../components/Simulation";
import "../components/Rooms/RoomsStyle.scss";
import { useBuildingContext } from "../BuildingContext";

const SimulationPage: React.FC = () => {
  const { buildingType, numFloors, numRoomsPerFloor } = useBuildingContext();
  return (
    <div className={styles.container}>
      <Sidebar
        buildingType={buildingType}
        numFloors={numFloors}
        numRoomsPerFloor={numRoomsPerFloor}
      />
      <Simulation />
    </div>
  );
};

export default SimulationPage;
