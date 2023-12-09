import React, { useState } from "react";
import styles from "./SimulationPage.module.css";
import Sidebar from "../components/Sidebar";
import Simulation from "../components/Simulation";
import "../components/Rooms/RoomsStyle.css";
import { useBuildingContext } from "../BuildingContext";
import EditPopup from "../components/EditBuildingPopup/EditPopup";

const SimulationPage: React.FC = () => {
  const { buildingType, numFloors, numRoomsPerFloor } = useBuildingContext();
  const [isEditPopupVisible, setEditPopupVisibility] = useState(false);
  const handleEditButtonClick = () => {
    setEditPopupVisibility(true);
  };
  const handleCloseEditPopup = () => {
    setEditPopupVisibility(false);
  };
  return (
    <div className={styles.container}>
      <Sidebar
        buildingType={buildingType}
        numFloors={numFloors}
        numRoomsPerFloor={numRoomsPerFloor}
        onEditButtonClick={handleEditButtonClick}
      />
      <Simulation />
      {isEditPopupVisible && <EditPopup onClose={handleCloseEditPopup} />}
    </div>
  );
};

export default SimulationPage;
