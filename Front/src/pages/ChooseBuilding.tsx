import styles from "./ChooseBuilding.module.css";
import ComboBox from "../components/ComboBox/ComboBox";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import ContinueButton from "../components/ContinueButton/ContinueButton";
import NewBuildingButton from "../components/CreateNewBuildingButton/CreateNewBuildingButton";
import { useBuildingContext } from "../BuildingContext";

interface Building {
  id: number;
  name: string;
}

function ChooseBuilding() {
  const { buildingName, setBuildingName, setBuildingId } = useBuildingContext();
  const [selectedBuilding, setSelectedBuilding] = useState<{
    id: string;
    name: string;
  }>({ id: "", name: "" });
  const [buildingOptions, setBuildingOptions] = useState<
    { id: string; name: string }[]
  >([]);

  const handleSelect = (selectedOption: string) => {
    const selectedBuilding = buildingOptions.find(
      (building) => building.name === selectedOption
    ) || { id: "", name: "" };
    setSelectedBuilding(selectedBuilding);
    setBuildingName(selectedBuilding.name);
    setBuildingId(parseInt(selectedBuilding.id, 10));
    localStorage.setItem("buildingId", selectedBuilding.id);
  };

  useEffect(() => {
    const fetchBuildings = async () => {
      try {
        const response = await fetch("http://localhost:8080/buildings");
        if (!response.ok) {
          throw new Error(
            `Failed to fetch: ${response.status} ${response.statusText}`
          );
        }
        const buildings = await response.json();
        const buildingOptions = buildings.map((building: Building) => ({
          id: building.id,
          name: building.name,
        }));
        setBuildingOptions([{ id: "", name: "None" }, ...buildingOptions]);
      } catch (error) {
        console.error("Error fetching buildings:", error);
      }
    };

    fetchBuildings();
  }, []);

  return (
    <div className={styles.background}>
      <div className={styles.container}>
        <h1 className={styles.title}>
          Choose an existing building or create a new one
        </h1>
        <div className={styles.existingBuildingContainer}>
          <div className={styles.dropdownMenu}>
            <ComboBox
              options={buildingOptions.map((building) => building.name)}
              onSelect={handleSelect}
              style={{ width: "180px" }}
              value={selectedBuilding.name}
              placeholder="Building name"
            />
          </div>
          {selectedBuilding.name !== "" && selectedBuilding.name !== "None" && (
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
