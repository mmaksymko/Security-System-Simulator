import { Link } from "react-router-dom";
import { useState } from "react";
import styles from "./ConfigureBuilding.module.css";
import BuildingTypeButton from "../components/BuildingTypeButton";
import GenerateBuildingButton from "../components/GenerateBuildingButton";
import RandomNumberButton from "../components/RandomNumberButton";
import InputField from "../components/InputField";
import { useBuildingContext } from "../BuildingContext";

function ConfigureBuilding() {
  const {
    buildingType,
    setBuildingType,
    numFloors,
    setNumFloors,
    numRoomsPerFloor,
    setNumRoomsPerFloor,
  } = useBuildingContext();
  const handleOfficeBuildingClick = () => {
    setBuildingType("office");
  };

  const handleResidentialBuildingClick = () => {
    setBuildingType("residential");
  };

  const handleNumFloorsChange = (value: number) => {
    setNumFloors(value);
  };

  const handleNumRoomsPerFloorChange = (value: number) => {
    setNumRoomsPerFloor(value);
  };

  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <h1 className={styles.h1}>Configure your building</h1>
        <div className={styles.propertiesContainer}>
          <div className={styles.buildingPropertyContainer}>
            <h2 className={styles.h2}>Choose type of the building</h2>
            <div className={styles.buildingPropertyButtons}>
              <BuildingTypeButton onClick={handleOfficeBuildingClick}>
                <img
                  src="../public/office-building_1f3e2.png"
                  width={"30px"}
                  height={"30px"}
                ></img>
                <p
                  style={{
                    margin: "0px",
                    alignSelf: "center",
                  }}
                >
                  Office building
                </p>
              </BuildingTypeButton>
              <BuildingTypeButton onClick={handleResidentialBuildingClick}>
                <img
                  src="../public/house_1f3e0.png"
                  width={"30px"}
                  height={"30px"}
                ></img>
                <p
                  style={{
                    margin: "0px",
                    alignSelf: "center",
                  }}
                >
                  Residential building
                </p>
              </BuildingTypeButton>
            </div>
          </div>
          <div className={styles.buildingPropertyContainer}>
            <h2 className={styles.h2}>Choose number of the floors</h2>
            <div className={styles.buildingPropertyButtons}>
              <InputField
                value={numFloors}
                onChange={(value) => handleNumFloorsChange(value)}
              />
              <RandomNumberButton
                onGenerate={handleNumFloorsChange}
                min={1}
                max={333}
              />
            </div>
          </div>
          <div className={styles.buildingPropertyContainer}>
            <h2 className={styles.h2}>Choose number of the rooms per floor</h2>
            <div className={styles.buildingPropertyButtons}>
              <InputField
                value={numRoomsPerFloor}
                onChange={(value) => handleNumRoomsPerFloorChange(value)}
              />
              <RandomNumberButton
                onGenerate={handleNumRoomsPerFloorChange}
                min={1}
                max={33}
              />
            </div>
          </div>
        </div>

        <Link to="/simulation">
          <GenerateBuildingButton />
        </Link>
      </div>
    </div>
  );
}

export default ConfigureBuilding;
