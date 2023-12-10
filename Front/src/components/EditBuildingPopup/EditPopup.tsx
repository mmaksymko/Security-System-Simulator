import styles from "./EditPopup.module.css";
import InputField from "../InputField/InputField";
import BuildingTypeButton from "../BuildingTypeButton/BuildingTypeButton";
import { useBuildingContext } from "../../BuildingContext";
import RandomNumberButton from "../RandomNumberButton";
import SubmitButton from "../SubmitButton";
import InputFieldText from "../InputField/InputFieldText";
import { useState } from "react";

interface EditPopupProps {
  onClose: () => void;
}
const EditPopup: React.FC<EditPopupProps> = ({ onClose }) => {
  const {
    buildingType,
    setBuildingType,
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
    <div className={styles.background} onClick={onClose}>
      <div className={styles.container} onClick={(e) => e.stopPropagation()}>
        <h1>Edit your building</h1>
        <img
          src="../public/close.png"
          height={"24px"}
          width={"24px"}
          className={styles.close}
          onClick={onClose}
        />
        <div className={styles.propertiesContainer}>
          <div className={styles.propertiesContainer}>
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>Choose new type of the building</h2>
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
              <h2 className={styles.h2}>Choose new number of the floors</h2>
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
          </div>
          {(buildingType === "office" || buildingType === "residential") && (
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>
                Choose new number of the rooms per floor
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
        <SubmitButton onClick={onClose} />
      </div>
    </div>
  );
};

export default EditPopup;
