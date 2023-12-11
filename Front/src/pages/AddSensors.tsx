import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import styles from "./CustomBuilding.module.css";
import GenerateBuildingButton from "../components/GenerateBuildingButton";
import ComboBox from "../components/ComboBox/ComboBox";
import AddSensorButton from "../components/AddSensorButton/AddSensorButton";
import Switch from "../components/Switch/Switch";
import sensorStyles from "../components/SensorStyles.module.css";

function AddSensors() {
  const [isSwitch1Enabled, setSwitch1Enabled] = useState(false);
  const [isSwitch2Enabled, setSwitch2Enabled] = useState(true);
  const navigate = useNavigate();

  const handleSwitchToggle = () => {
    setSwitch1Enabled(!isSwitch1Enabled);
  };

  const handleSwitchToggleForContinue = () => {
    setSwitch2Enabled(!isSwitch2Enabled);
  };

  if (!isSwitch2Enabled) {
    navigate("/customBuilding");
  }

  const [selectedFloor, setSelectedFloor] = useState<string | null>(null);
  const [selectedRoom, setSelectedRoom] = useState<string | null>(null);
  const [selectedSensors, setSelectedSensors] = useState<string[]>([]);
  const [selectedSensor, setSelectedSensor] = useState<string | null>(null);

  const handleAddSensor = () => {
    if (selectedSensor) {
      setSelectedSensors((prevSelectedSensors: string[]) => [
        ...prevSelectedSensors,
        selectedSensor,
      ]);
    }
  };

  const handleComboBoxSelection = (selectedOption: string) => {
    setSelectedSensor(selectedOption);
  };

  const handleRemoveSensor = (removedSensor: string) => {
    setSelectedSensors((prevSelectedSensors: string[]) =>
      prevSelectedSensors.filter((sensor) => sensor !== removedSensor)
    );
  };

  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <h1 className={styles.h1}>Configure your building</h1>
        <div className={styles.propertiesContainer}>
          <div className={styles.buildingPropertyButtons}>
            <h2 className={styles.h2}>Choose floor number</h2>
            <ComboBox
              options={["1", "2", "3"]}
              onSelect={() => {}}
              style={{ width: "75px" }}
              value={selectedFloor}
              placeholder="1"
            />
          </div>
          <div className={styles.buildingPropertyButtons}>
            <h2 className={styles.h2}>Choose room number</h2>
            <ComboBox
              options={["1", "2", "3"]}
              onSelect={() => {}}
              style={{ width: "75px" }}
              value={selectedRoom}
              placeholder="1"
            />
          </div>
          <div className={styles.buildingPropertyContainer}>
            <h2 className={styles.h2}>Choose type of sensor</h2>
            <div className={styles.buildingPropertyButtons}>
              <ComboBox
                options={["Fire", "Gas leak", "Opened door"]}
                onSelect={handleComboBoxSelection}
                value={selectedSensor}
                placeholder="Sensor type"
              />
              <AddSensorButton onClick={handleAddSensor} />
            </div>
          </div>
          <div className={styles.buildingPropertyContainer}>
            <h2 className={styles.h2}>Sensors in the current room</h2>
            <div className={styles.buildingPropertyButtons}>
              {selectedSensors.map((sensor, index) => (
                <div key={index} className={sensorStyles.selectedSensor}>
                  {sensor}
                  <span
                    className={sensorStyles.removeSensor}
                    onClick={() => handleRemoveSensor(sensor)}
                  >
                    &#10006;
                  </span>
                </div>
              ))}
            </div>
          </div>
          <div className={styles.buildingPropertyButtons}>
            <h2 className={styles.h2}>Apply settings for all floors</h2>
            <Switch onToggle={handleSwitchToggle} />
          </div>
          <div className={styles.buildingPropertyButtons}>
            <h2 className={styles.h2}>Add sensors</h2>
            <Switch onToggle={handleSwitchToggleForContinue} defaultOn={true} />
          </div>
        </div>
        <Link to="/simulation">
          <GenerateBuildingButton />
        </Link>
      </div>
    </div>
  );
}

export default AddSensors;
