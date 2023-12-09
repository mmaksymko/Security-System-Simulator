import styles from "./ChangeFloorButton.module.css";
import { useState } from "react";

function ChangeFloorButton() {
  const [floor, setFloor] = useState(1);

  const handleUpClick = () => {
    setFloor((prevFloor) => prevFloor + 1);
  };

  const handleDownClick = () => {
    if (floor - 1 > 0) setFloor((prevFloor) => prevFloor - 1);
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
