import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import styles from "./CustomBuilding.module.css";
import ComboBox from "../components/ComboBox/ComboBox";
import Switch from "../components/Switch/Switch";
import GenerateBuildingButton from "../components/GenerateBuildingButton";
import ContinueButton from "../components/ContinueButton/ContinueButton";
import RandomNumberButton from "../components/RandomNumberButton";
import InputCustomField from "../components/InputField/InputCustomField";
import { useBuildingContext } from "../BuildingContext";
import AddCustomRoomButton from "../components/AddCustomRoomButton";
import { Room } from "../model/Room";
import { BuildingLevel } from "../model/BuildingLevel";


let rooms: Room[];
let building: BuildingLevel;
rooms = [];

function CustomBuilding() {
  const { numFloors, setNumFloors, numRoomsPerFloor, setNumRoomsPerFloor } =
    useBuildingContext();
  const floorOptions = Array.from({ length: numFloors }, (_, index) =>
    (index + 1).toString()
  );

  const [roomNumber, setRoomNumber] = useState(0);
  const [roomWidth, setRoomWidth] = useState(0);
  const [roomLength, setRoomLength] = useState(0);
  const [numDoors, setNumDoors] = useState(0);
  const [numWindows, setNumWindows] = useState(0);
  const [isRoomsValid, setRoomsValid] = useState(Boolean);
  const [isButtonActive, setButtonActive] = useState(false);

  const [isSwitch1Enabled, setSwitch1Enabled] = useState(false);
  const [isSwitch2Enabled, setSwitch2Enabled] = useState(false);
  const [selectedFloor, setSelectedFloor] = useState<string | null>(null);
  const [selectedType, setSelectedType] = useState<string | null>(null);


  

  useEffect(() => {
    setButtonActive(isRoomsValid);
  }, [isRoomsValid]);

  const handleNumRoomsPerFloorChange = (value: number) => {
    if (value >= 1 && value <= 20) setRoomsValid(true);
    else setRoomsValid(false);
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

  // const handleContinueClick = () => {
  //   console.log("dkjsd")
    
  // };
  const handleAddButtonClick = () => {
    console.log(rooms.length)
    console.log(numRoomsPerFloor)
    if(rooms.length === numRoomsPerFloor){
      console.log("floor config completed");
      let floors : BuildingLevel[] = [];
      let n = + localStorage["custom_floors"];
      let i = 0
      while(i < n){
        console.log(i)
        floors.push(new BuildingLevel("Floor" + (i + 1).toString(), rooms));
        i++;
      }
      building = new BuildingLevel(localStorage.getItem("custom_name"), floors);
      localStorage.setItem("custom_building", JSON.stringify(building));
      return;
    }
    let roomType: string = "KITCHEN";
    if(selectedType === "Apartment room"){
      roomType = "APARTMENT_ROOM";
    }
    else if(selectedType === "Apartment bathroom"){
      roomType = "APARTMENT_BATHROOM";
    }
    else if(selectedType === "Office room"){
      roomType = "OFFICE_ROOM";
    }
    else if(selectedType === "Office restroom"){
      roomType = "OFFICE_RESTROOM";
    }
    let newRoom = new Room(roomType, roomNumber, numWindows, numDoors, roomLength, roomWidth,  []);
    rooms.push(newRoom);
  }

  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <h1 className={styles.h1}>Configure your building</h1>
        <div className={styles.propertiesContainer}>
          <div className={styles.buildingPropertyContainer}>
            <div className={styles.buildingPropertyButtons}>
              {/* <h2 className={styles.h2}>Choose floor number</h2>
              <ComboBox
                options={floorOptions}
                onSelect={(selectedValue) => setSelectedFloor(selectedValue)}
                style={{ width: "75px" }}
                value={selectedFloor}
                placeholder="1"
              /> */}
            </div>
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>Choose number of rooms on the floor</h2>
              <div className={styles.buildingPropertyButtons}>
                <InputCustomField
                  isValid={isRoomsValid}
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
                <InputCustomField
                  value={roomNumber}
                  onChange={(value) => handleRoomNumberChange(value)}
                  placeholder="Room number"
                />
                <ComboBox
                  options={[
                    "Apartment room",
                    "Apartment bathroom",
                    "Office room",
                    "Office restroom",
                    "Kitchen",
                  ]}
                  onSelect={() => {}}
                  value={selectedType}
                  placeholder="Room type"
                />
              </div>
            </div>
            <div className={styles.buildingPropertyContainer}>
              <h2 className={styles.h2}>Other settings</h2>
              <div className={styles.buildingPropertyButtons}>
                <InputCustomField
                  value={roomWidth}
                  onChange={(value) => handleRoomWidthChange(value)}
                  placeholder="Width"
                />
                <InputCustomField
                  value={roomLength}
                  onChange={(value) => handleRoomLengthChange(value)}
                  placeholder="Height"
                />
              </div>
              <div className={styles.buildingPropertyButtons}>
                <InputCustomField
                  value={numDoors}
                  onChange={(value) => handleNumDoorsChange(value)}
                  placeholder="Number of doors"
                />
                <InputCustomField
                  value={numWindows}
                  onChange={(value) => handleNumWindowsChange(value)}
                  placeholder="Number of windows"
                />
              </div>
            </div>
            <AddCustomRoomButton 
            disabled={!isButtonActive}
            onClick={handleAddButtonClick}
            />

            {/* <div className={styles.buildingPropertyButtons}>
              <h2 className={styles.h2}>Apply settings for all floors</h2>
              <Switch onToggle={handleSwitchToggle} />
            </div> */}
            <div className={styles.buildingPropertyButtons}>
              <h2 className={styles.h2}>Add sensors</h2>
              <Switch
                onToggle={handleSwitchToggleForContinue}
                defaultOn={false}
              />
            </div>
          </div>
        </div>
        {isSwitch2Enabled ? (
          <Link to="/AddSensors">
            <ContinueButton />
          </Link>
        ) : (
          <Link to="/simulation">
            <GenerateBuildingButton disabled={!isButtonActive} />
          </Link>
        )}
      </div>
    </div>
  );
}

export default CustomBuilding;
