import styles from "./StatesPopup.module.css";
import { useBuildingContext } from "../../BuildingContext";
import SubmitButton from "../SubmitButton";
import { useState, useEffect } from "react";
import ComboBox from "../ComboBox/ComboBox";

interface LogEntry {
  dangerLevel: string;
  eventType: string;
  location: string;
  happenedAt: string;
  result: boolean;
}

interface EditPopupProps {
  onClose: () => void;
  onClearLogData: () => void;
}
const StatesPopup: React.FC<EditPopupProps> = ({ onClose, onClearLogData }) => {
  const [logOptions, setLogOptions] = useState<string[]>([]);
  const [selectedLogId, setSelectedLogId] = useState<string | undefined>();
  const { buildingName, setBuildingName, buildingId, setBuildingId } =
    useBuildingContext();

  useEffect(() => {
    const fetchBuildingData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/buildings/${localStorage.getItem("buildingId")}/logs`
        );
        console.log("building ID: ", localStorage.getItem("buildingId"));
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
    setSelectedLogId(
      (logOptions.indexOf(selectedOption) - 1).toString()
    );
  };
  const handleSubmitClick = async () => {
    console.log("sending post request with a chosen log id");
    try {
      if (selectedLogId !== undefined && selectedLogId !== "0") {
        const response = await fetch(
          `http://localhost:8080/buildings/${localStorage.getItem("buildingId")}/logs/${selectedLogId}`,
          {
            method: "POST",
          }
        );

        if (response.ok) {
          console.log("POST request successful");
          onClearLogData();
          onClose();
        } else {
          console.error("POST request failed");
        }
      } else {
        console.error("Invalid selected log ID");
      }
    } catch (error) {
      console.error("Error sending POST request:", error);
    }
    console.log("finished posting data. start claring previous log data");
  };

  return (
    <div className={styles.background} onClick={onClose}>
      <div className={styles.container} onClick={(e) => e.stopPropagation()}>
        <h1 className={styles.title}>Restore state of simulation</h1>
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
