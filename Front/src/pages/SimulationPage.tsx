// SimulationPage.tsx
import React, { useState } from "react";
import styles from "./SimulationPage.module.css";
import Sidebar from "../components/Sidebar/Sidebar";
import Simulation from "../components/Simulation/Simulation";
import Log from "../components/Log/Log"; // Import Log component
import { useBuildingContext } from "../BuildingContext";
import EditPopup from "../components/EditBuildingPopup/EditPopup";

interface LogEntry {
  dangerLevel: string;
  eventType: string;
  happenedAt: string;
  location: string;
  result: boolean;
}

const SimulationPage: React.FC = () => {
  const { buildingType, buildingName, numFloors, numRoomsPerFloor } =
    useBuildingContext();
  const [isEditPopupVisible, setEditPopupVisibility] = useState(false);
  const [logData, setLogData] = useState<LogEntry[]>([]);

  const handleLogDataUpdate = (newLogData: LogEntry[]) => {
    console.log("Updating logData in SimulationPage:", newLogData);

    setLogData((prevLogData) => [...prevLogData, ...newLogData]);
  };

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
        buildingName={buildingName}
        numFloors={numFloors}
        numRoomsPerFloor={numRoomsPerFloor}
        onEditButtonClick={handleEditButtonClick}
        logData={logData}
        setLogData={setLogData}
      />
      <Simulation logData={logData} onLogDataUpdate={handleLogDataUpdate} />
      {isEditPopupVisible && <EditPopup onClose={handleCloseEditPopup} />}
    </div>
  );
};

export default SimulationPage;
