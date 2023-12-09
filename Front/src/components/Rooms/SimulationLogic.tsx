import React, { useState } from "react";
import Bedroom from "./Bedroom";
import "./RoomsStyle.css";
import styles from "../InputFile.module.css";

const SimulationLogic = () => {
    const [numOfRooms, setNumOfRooms] = useState(0);
    const numbers: number[] = [1, 2, 2];
    let j = 0;
  
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
      const value = parseInt(e.target.value, 10);
      setNumOfRooms(value || 0);
    };
  
    const generateBedrooms = () => {
      const bedrooms = [];
  
      for (let i = 1; i <= numOfRooms; i++) {
        bedrooms.push(<Bedroom key={i} bedNum={numbers[j]} />);
        if (j == 2) {
          j = 0;
        } else {
          j++;
        }
      }
  
      return bedrooms;
    };
  
    return (
        <div className="Components FloorPlan">
          <input
            className={styles.input}
            type="text"
            placeholder="Enter a number"
            onChange={handleInputChange}
          />
          {generateBedrooms()}
        </div>
    );
  };
  
  export default SimulationLogic;