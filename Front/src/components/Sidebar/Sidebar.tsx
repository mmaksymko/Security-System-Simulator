import React, { useState } from "react";
import styles from "./Sidebar.module.css";
import BuildingInfoItem from "../BuildingInfoItem";
import EditBtn from "../EditBuildingConfigurationButton";
import Log from "../Log/Log";

interface SidebarProps {
  buildingType: string;
  numFloors: number;
  numRoomsPerFloor: number;
  onEditButtonClick: () => void;
}

const Sidebar: React.FC<SidebarProps> = ({
  buildingType,
  numFloors,
  numRoomsPerFloor,
  onEditButtonClick,
}) => {
  return (
    <div className={styles.container}>
      <div className={styles.configurationContainer}>
        <h1 className={styles.title}>Configuration of the building</h1>
        <div className={styles.buildingInfo}>
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
      </div>
      <Log />
    </div>
  );
};

export default Sidebar;
