import styles from "./ChangeFloorButton.module.css";
import React, { useState, useEffect } from "react";

interface ChangeFloorBtnProps {
  buildingId: number;
}

const ChangeFloorButton: React.FC<ChangeFloorBtnProps> = ({ buildingId }) => {
  const [floor, setFloor] = useState(1);
  const [buildingData, setBuildingData] = useState({
    numFloors: 0,
  });
  useEffect(() => {
    const fetchBuildingData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/buildings/${localStorage.getItem(
            "buildingId"
          )}`
        );
        const data = await response.json();
        //console.log("DATA: ", data);

        setBuildingData({
          numFloors: data.components.length,
        });
      } catch (error) {
        console.error("Error fetching building data:", error);
      }
    };

    fetchBuildingData();
  }, [buildingId]);
  const handleUpClick = () => {
    if (floor + 1 <= buildingData.numFloors) {
      //console.log(buildingData.numFloors);
      setFloor((prevFloor) => {
        localStorage.setItem("floor", prevFloor.toString());

        const newFloor = prevFloor + 1;
        window.dispatchEvent(new Event("storage"));
        return newFloor;
      });
    }
  };

  const handleDownClick = () => {
    if (floor - 1 > 0)
      setFloor((prevFloor) => {
        const newValue = prevFloor - 1;
        localStorage.setItem("floor", (newValue - 1).toString());
        window.dispatchEvent(new Event("storage"));
        return newValue;
      });
  };

  return (
    <div className={styles.container}>
      <div className={styles.buttonsContainer}>
        <button onClick={handleUpClick}>
          <img src="../public/arrow-up.png" width={"24px"} height={"24px"} />
        </button>
        <p>{floor}</p>
        <button onClick={handleDownClick}>
          <img src="../public/arrow-down.png" width={"24px"} height={"24px"} />
        </button>
      </div>
      <p>floor</p>
    </div>
  );
};

export default ChangeFloorButton;
