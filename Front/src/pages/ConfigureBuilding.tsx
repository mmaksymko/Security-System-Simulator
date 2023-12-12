import { Link } from "react-router-dom";
import { useState } from "react";
import styles from "./ConfigureBuilding.module.css";
import BuildingTypeButton from "../components/BuildingTypeButton/BuildingTypeButton";
import GenerateBuildingButton from "../components/GenerateBuildingButton";
import RandomNumberButton from "../components/RandomNumberButton";
import InputField from "../components/InputField/InputField";
import InputFieldText from "../components/InputField/InputFieldText";
import { useBuildingContext } from "../BuildingContext";
import ContinueSimulationButton from "../components/ContinueSimulationButton";

function ConfigureBuilding() {
  const {
    buildingType,
    setBuildingType,
    buildingName,
    setBuildingName,
    numFloors,
    setNumFloors,
    numRoomsPerFloor,
    setNumRoomsPerFloor,
  } = useBuildingContext();

  const [activeType, setActiveType] = useState("");

  const handleBuildingTypeClick = (type: string) => {
    setActiveType(type);
    setBuildingType(type);
  };

  const handleNameChange = (value: string) => {
    setBuildingName(value);
  };
  const handleNumFloorsChange = (value: number) => {
    setNumFloors(value);
  };

  const handleNumRoomsPerFloorChange = (value: number) => {
    setNumRoomsPerFloor(value);
  };

  const handleGenerateBuildingClick = async () => {
    console.log(buildingType);
    await fetch(`http://localhost:8080/buildings/${buildingType}`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({floors: numFloors, rooms: numRoomsPerFloor, name: buildingName})
    })
    .then(response => response.json())
    .then(data => {
      console.log(data)
      localStorage.setItem("buildingId", data.id)
    });
  };

  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <h1 className={styles.h1}>Configure your building</h1>
        <div className={styles.propertiesContainer}>
          <div className={styles.propertiesContainer}>
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>Choose type of the building</h2>
              <div className={styles.buildingTypeButtons}>
                <div className={styles.firstRowButtons}>
                  <BuildingTypeButton
                    onClick={() => handleBuildingTypeClick("office")}
                    active={activeType === "office"}
                  >
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
                  <BuildingTypeButton
                    onClick={() => handleBuildingTypeClick("residential")}
                    active={activeType === "residential"}
                  >
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
                <BuildingTypeButton
                  onClick={() => handleBuildingTypeClick("custom")}
                  active={activeType === "custom"}
                >
                  <img
                    src="../public/wrench.png"
                    width={"30px"}
                    height={"30px"}
                  ></img>
                  <p
                    style={{
                      margin: "0px",
                      alignSelf: "center",
                    }}
                  >
                    Custom building
                  </p>
                </BuildingTypeButton>
              </div>
            </div>
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>Choose a name for the building</h2>
              <div className={styles.buildingPropertyButtons}>
                <InputFieldText
                  value={buildingName}
                  onChange={(value) => handleNameChange(value)}
                />
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
                  max={102}
                />
              </div>
            </div>
          </div>

          {(buildingType === "office" ||
            buildingType === "residential" ||
            buildingType === "") && (
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>
                Choose number of the rooms per floor
              </h2>
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
          )}
        </div>
        {(buildingType === "office" || buildingType === "residential") && (
          <Link to="/simulation">
            <GenerateBuildingButton 
             onClick={() => handleGenerateBuildingClick()}/>
          </Link>
        )}
        {buildingType === "custom" && (
          <Link to="/customBuilding">
            <GenerateBuildingButton />
          </Link>
        )}
      </div>
    </div>
  );
}

export default ConfigureBuilding;
