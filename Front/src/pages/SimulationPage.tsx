import React, { useState } from "react";
import styles from "./SimulationPage.module.css";
import Sidebar from "../components/Sidebar/Sidebar";
import Simulation from "../components/Simulation/Simulation";
import { useBuildingContext } from "../BuildingContext";
import EditPopup from "../components/EditBuildingPopup/EditPopup";
import ChartPopup from "../components/ChartsPopup/ChartsPopup";

interface LogEntry {
  dangerLevel: string;
  eventType: string;
  location: string;
  happenedAt: string;
  result: boolean;
}

const SimulationPage: React.FC = () => {
  const {
    buildingType,
    buildingId,
    buildingName,
    numFloors,
    numRoomsPerFloor,
  } = useBuildingContext();
  const [isEditPopupVisible, setEditPopupVisibility] = useState(false);
  const [isChartPopupVisible, setChartPopupVisibility] = useState(false);
  const [logData, setLogData] = useState<LogEntry[]>([]);

  const handleLogDataUpdate = (newLogData: LogEntry[]) => {
    setLogData((prevLogData) => [...prevLogData, ...newLogData]);
  };

  const handleEditButtonClick = () => {
    setEditPopupVisibility(true);
  };

  const handleCloseEditPopup = () => {
    setEditPopupVisibility(false);
  };

  const handleStatsButtonClick = () => {
    setChartPopupVisibility(true);
  };

  const handleCloseChartPopup = () => {
    setChartPopupVisibility(false);
  };

  const onClearLogData = () => {
    console.log("Before clearing log data");
    setLogData([]);
    console.log("AFTER clearing log data");
  };

  return (
    <div className={styles.container}>
      <Sidebar
        buildingType={buildingType}
        buildingId={buildingId}
        buildingName={buildingName}
        numFloors={numFloors}
        numRoomsPerFloor={numRoomsPerFloor}
        onEditButtonClick={handleEditButtonClick}
        onStatsButtonClick={handleStatsButtonClick}
        logData={logData}
        setLogData={setLogData}
        onClearLogData={onClearLogData}
      />
      <Simulation logData={logData} onLogDataUpdate={handleLogDataUpdate} />
      {isEditPopupVisible && <EditPopup onClose={handleCloseEditPopup} />}
      {isChartPopupVisible && <ChartPopup onClose={handleCloseChartPopup} />}
    </div>
  );
};

export default SimulationPage;
