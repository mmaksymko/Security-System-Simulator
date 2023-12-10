import { Link } from "react-router-dom";
import { useState } from "react";
import styles from "./CustomBuilding.module.css";
import ComboBox from "../components/ComboBox/ComboBox";
import Switch from "../components/Switch/Switch";
import GenerateBuildingButton from "../components/GenerateBuildingButton";
import ContinueButton from "../components/ContinueButton/ContinueButton"
import RandomNumberButton from "../components/RandomNumberButton";
import InputCustomField from "../components/InputField/InputCustomField";
import { useBuildingContext } from "../BuildingContext";

function CustomBuilding() {
  const {

    numRoomsPerFloor,

    setNumRoomsPerFloor,
  } = useBuildingContext();

  const [roomNumber, setRoomNumber] = useState(0);
  const [roomWidth, setRoomWidth] = useState(0);
  const [roomLength, setRoomLength] = useState(0);
  const [numDoors, setNumDoors] = useState(0);
  const [numWindows, setNumWindows] = useState(0);

  const [isSwitch1Enabled, setSwitch1Enabled] = useState(false);
  const [isSwitch2Enabled, setSwitch2Enabled] = useState(false);
  const [selectedFloor, setSelectedFloor] = useState<string | null>(null);
  const [selectedType, setSelectedType] = useState<string | null>(null); // Declare selectedType state


  const handleNumRoomsPerFloorChange = (value: number) => {
    setNumRoomsPerFloor(value);
  };


  const handleRoomNumberChange = (value: number) => {
    setRoomNumber(value);
  };

  const handleRoomWidthChange = (value: number) => {
    setRoomWidth(value);
  };

  const handleRoomLengthChange = (value: number) => {
    setRoomLength(value);
  };

  const handleNumDoorsChange = (value: number) => {
    setNumDoors(value);
  };

  const handleNumWindowsChange = (value: number) => {
    setNumWindows(value);
  };

  const handleSwitchToggle = () => {
    setSwitch1Enabled(!isSwitch1Enabled);
  };

  const handleSwitchToggleForContinue = () => {
    setSwitch2Enabled(!isSwitch2Enabled);
  };

  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <h1 className={styles.h1}>Configure your building</h1>
        <div className={styles.propertiesContainer}>
          <div className={styles.buildingPropertyContainer}>
            <div className={styles.buildingPropertyButtons}>
            <h2 className={styles.h2}>Choose floor number</h2>
            <ComboBox options={['1', '2', '3']} onSelect={() => { }} style={{ width: '75px' }} value={selectedFloor} placeholder="1" />
            </div>
            <div/>
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>Choose number of rooms on the floor</h2>
              <div className={styles.buildingPropertyButtons}>
                <InputCustomField
                  value={numRoomsPerFloor}
                  onChange={(value) => handleNumRoomsPerFloorChange(value)} 
                  placeholder="Enter a number"
                />
                <RandomNumberButton
                  onGenerate={handleNumRoomsPerFloorChange}
                  min={1}
                  max={33}
                />
              </div>
            </div>
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>Choose type of room</h2>
              <div className={styles.buildingPropertyButtons}>
              <InputCustomField value={roomNumber} 
              onChange={(value) => handleRoomNumberChange(value)}   placeholder="Room number" />
              <ComboBox options={["Apartment room", "Apartment bathroom", "Office room", "Office restroom", "Kitchen"]} onSelect={() => { }} value={selectedType} placeholder="Room type"  />
              </div>
            </div>
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>Other settings</h2>
              <div className={styles.buildingPropertyButtons}>
              <InputCustomField value={roomWidth} 
              onChange={(value) => handleRoomWidthChange(value)} placeholder="Width" />
              <InputCustomField value={roomLength} 
              onChange={(value) => handleRoomLengthChange(value)} placeholder="Height" />
                </div>
                <div className={styles.buildingPropertyButtons}>
                <InputCustomField value={numDoors} 
                onChange={(value) => handleNumDoorsChange(value)} 
                placeholder="Number of doors"/>
                <InputCustomField value={numWindows} 
                onChange={(value) => handleNumWindowsChange(value)}
                placeholder="Number of windows" />
              </div>
            </div>

            <div className={styles.buildingPropertyButtons}>
              <h2 className={styles.h2}>Apply settings for all floors</h2>
              <Switch onToggle={handleSwitchToggle} />
            </div>
            <div className={styles.buildingPropertyButtons}>
              <h2 className={styles.h2}>Add sensors</h2>
              <Switch onToggle={handleSwitchToggleForContinue}   defaultOn={false} />
            </div>
          </div>
        </div>
        {isSwitch2Enabled ? (
          <Link to="/AddSensors">
            <ContinueButton />
          </Link>
        ) : (
          <Link to="/simulation">
            <GenerateBuildingButton />
          </Link>
        )}
      </div>
    </div>
  );
}

export default CustomBuilding;
