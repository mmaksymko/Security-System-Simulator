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
  const handleOfficeBuildingClick = (
    event: React.MouseEvent<HTMLButtonElement>
  ) => {
    console.log("office button clicked");
    const selectedType = event.currentTarget.innerText;
    setBuildingType("office");
  };
  const handleResidentalBuildingClick = (
    event: React.MouseEvent<HTMLButtonElement>
  ) => {
    console.log("residental button clicked");
    const selectedType = event.currentTarget.innerText;
    setBuildingType("residental");
  };
  const handleNumFloorsChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const value = parseInt(event.target.value, 10) || 0;
    setNumFloors(value);
  };

  const handleNumRoomsPerFloorChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const value = parseInt(event.target.value, 10) || 0;
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
              <BuildingTypeButton onClick={handleResidentalBuildingClick}>
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
              <InputField value={numFloors} onChange={handleNumFloorsChange} />
              <RandomNumberButton />
            </div>
          </div>
          <div className={styles.buildingPropertyContainer}>
            <h2 className={styles.h2}>Choose number of the rooms per floor</h2>
            <div className={styles.buildingPropertyButtons}>
              <InputField
                value={numRoomsPerFloor}
                onChange={handleNumRoomsPerFloorChange}
              />
              <RandomNumberButton />
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
