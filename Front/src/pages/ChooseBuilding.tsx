import styles from "./ChooseBuilding.module.css";
import ComboBox from "../components/ComboBox/ComboBox";
import { useBuildingContext } from "../BuildingContext";
import { useState } from "react";
import ContinueButton from "../components/ContinueButton/ContinueButton";

function ChooseBuilding() {
  const [selectedBuildingName, setSelectedBuildingName] = useState<
    string | null
  >(null);
  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <div className={styles.dropdownMenu}>
          <ComboBox
            options={["1", "2", "3"]}
            onSelect={() => {}}
            style={{ width: "75px" }}
            value={selectedBuildingName}
            placeholder="1"
          />
        </div>
        <ContinueButton />
      </div>
    </div>
  );
}
