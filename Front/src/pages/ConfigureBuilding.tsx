import { Link } from "react-router-dom";
import styles from "./ConfigureBuilding.module.css";
import BuildingTypeButton from "../components/BuildingTypeButton";
import GenerateBuildingButton from "../components/GenerateBuildingButton";
import RandomNumberButton from "../components/RandomNumberButton";
import InputField from "../components/InputField";

const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
  console.log("office button clicked");
};

function ConfigureBuilding() {
  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <h1 className={styles.h1}>Configure your building</h1>
        <div className={styles.propertiesContainer}>
          <div className={styles.buildingPropertyContainer}>
            <h2 className={styles.h2}>Choose type of the building</h2>
            <div className={styles.buildingPropertyButtons}>
              <BuildingTypeButton onClick={handleClick}>
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
              <BuildingTypeButton onClick={handleClick}>
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
              <InputField />
              <RandomNumberButton />
            </div>
          </div>
          <div className={styles.buildingPropertyContainer}>
            <h2 className={styles.h2}>Choose number of the rooms per floor</h2>
            <div className={styles.buildingPropertyButtons}>
              <InputField />
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
