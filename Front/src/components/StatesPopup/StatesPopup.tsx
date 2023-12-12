import styles from "./StatesPopup.module.css";
import { useBuildingContext } from "../../BuildingContext";
import SubmitButton from "../SubmitButton";
import { useState, useEffect } from "react";
import ComboBox from "../ComboBox/ComboBox";

interface EditPopupProps {
  onClose: () => void;
}
const StatesPopup: React.FC<EditPopupProps> = ({ onClose }) => {
  const [logOptions, setLogOptions] = useState<string[]>([]);
  const [selectedLogId, setSelectedLogId] = useState<string | undefined>();
  const { buildingName, setBuildingName, buildingId, setBuildingId } =
    useBuildingContext();

  useEffect(() => {
    const fetchBuildingData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/buildings/${buildingId}/logs`
        );
        console.log("building ID: ", buildingId);
        const data = await response.json();
        const logEntries = data.map((logEntry: any, index: number) =>
          index.toString()
        );
        setLogOptions(["Select an option", ...logEntries]);

        console.log("DATA: ", data);
      } catch (error) {
        console.error("Error fetching building data:", error);
      }
    };

    fetchBuildingData();
  }, []);
  const handleSelect = (selectedOption: string) => {
    const selectedIndex = logOptions.indexOf(selectedOption);

    setSelectedLogId(
      selectedIndex !== -1 ? selectedIndex.toString() : undefined
    );
  };
  const handleSubmitClick = async () => {};
  return (
    <div className={styles.background} onClick={onClose}>
      <div className={styles.container} onClick={(e) => e.stopPropagation()}>
        <h1>Restore state of simulation</h1>
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
              <h2 className={styles.h2}>Choose state of the simulation</h2>
              <div className={styles.buildingPropertyButtons}>
                <ComboBox
                  options={logOptions}
                  onSelect={handleSelect}
                  style={{ width: "120px" }}
                  value={selectedLogId || undefined}
                  placeholder="0"
                />
              </div>
            </div>
          </div>
        </div>
        <SubmitButton onClick={handleSubmitClick} />
      </div>
    </div>
  );
};

export default StatesPopup;
