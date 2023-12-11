import styles from "./ChooseBuilding.module.css";
import ComboBox from "../components/ComboBox/ComboBox";
import { useState } from "react";
import { Link } from "react-router-dom";
import ContinueButton from "../components/ContinueButton/ContinueButton";
import NewBuildingButton from "../components/CreateNewBuildingButton/CreateNewBuildingButton";

function ChooseBuilding() {
  const [selectedBuildingName, setSelectedBuildingName] = useState<string>("");

  const handleSelect = (selectedOption: string) => {
    setSelectedBuildingName(selectedOption);
  };

  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <h1 className={styles.title}>
          Choose an existing building or create a new one
        </h1>
        <div className={styles.existingBuildingContainer}>
          <div className={styles.dropdownMenu}>
            <ComboBox
              options={["None", "Best", "Sombra", "Sigma"]}
              onSelect={handleSelect}
              style={{ width: "160px" }}
              value={selectedBuildingName}
              placeholder="Building name"
            />
          </div>
          {selectedBuildingName !== "" && selectedBuildingName !== "None" && (
            <Link to="/simulation">
              <ContinueButton />
            </Link>
          )}
        </div>

        <Link to="/configuration">
          <NewBuildingButton />
        </Link>
      </div>
    </div>
  );
}

export default ChooseBuilding;
