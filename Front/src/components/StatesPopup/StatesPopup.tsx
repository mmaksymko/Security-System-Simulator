import styles from "./StatesPopup.module.css";
import { useBuildingContext } from "../../BuildingContext";
import SubmitButton from "../SubmitButton";
import { useState, useEffect } from "react";
import ComboBox from "../ComboBox/ComboBox";
import Log from "../Log/Log"

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
  onLogDataUpdate: (newLogData: LogEntry[], clear: boolean) => void;
}
const StatesPopup: React.FC<EditPopupProps> = ({ onClose, onClearLogData, onLogDataUpdate }) => {
  const [logOptions, setLogOptions] = useState<string[]>([]);
  const [selectedLogId, setSelectedLogId] = useState<string | undefined>();
  const [logData, setLogData] = useState<LogEntry[]>([]);

  const { buildingName, setBuildingName, buildingId, setBuildingId } =
    useBuildingContext();

  useEffect(() => {
    const fetchBuildingData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/buildings/${localStorage.getItem("buildingId")}/logs`
        );
        // console.log("building ID: ", localStorage.getItem("buildingId"));
        const data = await response.json();
        const logEntries = data.map((logEntry: any, index: number) =>
          index.toString()
        );
        setLogOptions(["Select an option", ...logEntries]);

        // console.log("DATA: ", data);
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
      if (selectedLogId !== undefined) {
        const response = await fetch(
          `http://localhost:8080/buildings/${localStorage.getItem("buildingId")}/logs/${selectedLogId}`,
          {
            method: "POST",
          }
        );
        const json = await response.json();
        const entries = json as LogEntry[];
        // for (let entry of entries)
        //   entry.location = `${parseInt((Math.random() * 4 + 1).toFixed()) * 100 + parseInt((Math.random() * 4 + 1).toFixed())}`

        console.log(entries);

        onLogDataUpdate(entries, true)
        onLogDataUpdate(entries, false)


        if (response.ok) {
          console.log("POST request successful");
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
