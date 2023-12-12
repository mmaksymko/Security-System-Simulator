import React, { useState, useEffect } from "react";
import styles from "./Sidebar.module.css";
import BuildingInfoItem from "../BuildingInfoItem";
import EditBtn from "../EditBuildingConfigurationButton";
import Log from "../Log/Log";

interface LogEntry {
  dangerLevel: string;
  eventType: string;
  location: string;
  happenedAt: string;
  result: boolean;
}

interface SidebarProps {
  buildingType: string;
  buildingId: number;
  buildingName: string;
  numFloors: number;
  numRoomsPerFloor: number;
  onEditButtonClick: () => void;
  logData: LogEntry[];
  setLogData: React.Dispatch<React.SetStateAction<LogEntry[]>>;
}

const Sidebar: React.FC<SidebarProps> = ({
  buildingType,
  buildingId,
  buildingName,
  numFloors,
  numRoomsPerFloor,
  onEditButtonClick,
  logData,
  setLogData,
}) => {
  const [buildingData, setBuildingData] = useState({
    buildingName: "",
    numFloors: 0,
    numRoomsPerFloor: 0,
  });

  useEffect(() => {
    const fetchBuildingData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/buildings/${buildingId}`
        );
        const data = await response.json();
        console.log("DATA: ", data);

        setBuildingData({
          buildingName: data.name,
          numFloors: data.components.length,
          numRoomsPerFloor: data.components[0].components.length,
        });
      } catch (error) {
        console.error("Error fetching building data:", error);
      }
    };

    fetchBuildingData();
  }, [buildingId]);
  return (
    <div className={styles.container}>
      <div className={styles.configurationContainer}>
        <h1 className={styles.title}>Configuration of the building</h1>
        <div className={styles.buildingInfo}>
          <BuildingInfoItem>
            <p className={styles.property}>Name of the building:</p>
            <p className={styles.value}>{buildingData.buildingName}</p>
          </BuildingInfoItem>
          <BuildingInfoItem>
            <p className={styles.property}>Number of floors:</p>
            <p className={styles.value}>{buildingData.numFloors}</p>
          </BuildingInfoItem>
          <BuildingInfoItem>
            <p className={styles.property}>Number of rooms per floor:</p>
            <p className={styles.value}>{buildingData.numRoomsPerFloor}</p>
          </BuildingInfoItem>
        </div>
        <EditBtn onClick={onEditButtonClick} />
      </div>
      <Log logData={logData} />
    </div>
  );
};

export default Sidebar;
