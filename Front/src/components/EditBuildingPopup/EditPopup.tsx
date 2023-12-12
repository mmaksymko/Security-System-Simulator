import styles from "./EditPopup.module.css";
import InputField from "../InputField/InputField";
import BuildingTypeButton from "../BuildingTypeButton/BuildingTypeButton";
import { useBuildingContext } from "../../BuildingContext";
import RandomNumberButton from "../RandomNumberButton";
import SubmitButton from "../SubmitButton";
import InputFieldText from "../InputField/InputFieldText";
import { useState, useEffect } from "react";

interface EditPopupProps {
  onClose: () => void;
}
const EditPopup: React.FC<EditPopupProps> = ({ onClose }) => {
  const {
    buildingType,
    setBuildingType,
    buildingId,
    setBuildingId,
    buildingName,
    setBuildingName,
    numFloors,
    setNumFloors,
    numRoomsPerFloor,
    setNumRoomsPerFloor,
  } = useBuildingContext();
  const [activeType, setActiveType] = useState("");
  const [isFloorsValid, setFloorsValid] = useState(true);
  const [isRoomsValid, setRoomsValid] = useState(true);
  const [isNameValid, setNameValid] = useState(true);
  const [buildingData, setBuildingData] = useState({
    buildingName: "",
    numFloors: 0,
    numRoomsPerFloor: 0,
  });
  useEffect(() => {
    const fetchBuildingData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/buildings/${localStorage.getItem("buildingId")}`
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
  }, []);

  const handleNumFloorsChange = (value: number) => {
    const isValid = value >= 1 && value <= 200;
    setFloorsValid(isValid);
    setNumFloors(value);
  };
  const handleNameChange = (value: string) => {
    const isValid = value != "";
    setNameValid(isValid);
    setBuildingName(value);
  };

  const handleNumRoomsPerFloorChange = (value: number) => {
    const isValid = value >= 1 && value <= 20;
    setRoomsValid(isValid);
    setNumRoomsPerFloor(value);
  };

  const handleSubmitClick = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/buildings/${localStorage.getItem("buildingId")}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            name: buildingName,
          }),
        }
      );

      if (response.ok) {
        onClose();
      } else {
        console.error("Failed to update building data");
      }
    } catch (error) {
      console.error("Error updating building data:", error);
    }
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
            <div className={styles.duildingPropertyContainer}>
              <h2 className={styles.h2}>Choose new name for the building</h2>
              <div className={styles.buildingPropertyButtons}>
                <InputFieldText
                  value={buildingData.buildingName}
                  onChange={(value) => handleNameChange(value)}
                />
              </div>
            </div>
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>Choose new number of the floors</h2>
              <div className={styles.buildingPropertyButtons}>
                <InputField
                  value={buildingData.numFloors}
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
          <div className={styles.buildingPropertyContainer}>
            <h2 className={styles.h2}>
              Choose new number of the rooms per floor
            </h2>
            <div className={styles.buildingPropertyButtons}>
              <InputField
                value={buildingData.numRoomsPerFloor}
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
        <SubmitButton onClick={handleSubmitClick} />
      </div>
    </div>
  );
};

export default EditPopup;
