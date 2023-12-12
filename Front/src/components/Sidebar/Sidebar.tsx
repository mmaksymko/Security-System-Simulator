import React, { useState } from "react";
import styles from "./Sidebar.module.css";
import BuildingInfoItem from "../BuildingInfoItem";
import EditBtn from "../EditBuildingConfigurationButton";
import StatsBtn from "../ViewStatisticsButton";
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
  buildingName: string;
  numFloors: number;
  numRoomsPerFloor: number;
  onEditButtonClick: () => void;
  onStatsButtonClick: () => void;
  logData: LogEntry[];
  setLogData: React.Dispatch<React.SetStateAction<LogEntry[]>>;
}

const Sidebar: React.FC<SidebarProps> = ({
  buildingType,
  buildingName,
  numFloors,
  numRoomsPerFloor,
  onEditButtonClick,
  onStatsButtonClick,
  logData,
  setLogData,
}) => {
  return (
    <div className={styles.container}>
      <div className={styles.configurationContainer}>
        <h1 className={styles.title}>Configuration of the building</h1>
        <div className={styles.buildingInfo}>
          <BuildingInfoItem>
            <p className={styles.property}>Name of the building:</p>
            <p className={styles.value}>{buildingName}</p>
          </BuildingInfoItem>
          <BuildingInfoItem>
            <p className={styles.property}>Type of the building:</p>
            <p className={styles.value}>{buildingType}</p>
          </BuildingInfoItem>
          <BuildingInfoItem>
            <p className={styles.property}>Number of floors:</p>
            <p className={styles.value}>{numFloors}</p>
          </BuildingInfoItem>
          <BuildingInfoItem>
            <p className={styles.property}>Number of rooms per floor:</p>
            <p className={styles.value}>{numRoomsPerFloor}</p>
          </BuildingInfoItem>
        </div>
        <EditBtn onClick={onEditButtonClick} />
        <StatsBtn onClick={onStatsButtonClick}/>
      </div>
      <Log logData={logData} />
    </div>
  );
};

export default Sidebar;
