import styles from "./ChangeFloorButton.module.css";
import { useState } from "react";

function ChangeFloorButton() {
  const [floor, setFloor] = useState(1);

  const handleUpClick = () => {
    setFloor((prevFloor) => {
      localStorage.setItem("floor", prevFloor.toString());

      const newFloor = prevFloor + 1;
      window.dispatchEvent(new Event("storage"));
      return newFloor;
    });
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
}
export default ChangeFloorButton;
