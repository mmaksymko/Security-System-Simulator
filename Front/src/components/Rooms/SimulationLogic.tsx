import { useEffect, useState } from "react";
import Bedroom from "./Bedroom";
import "./RoomsStyle.scss";
import {
  Room,
  GetBuildingsResponse,
  getBuilding,
  getBuildings,
} from "../FetchAPI/customBuildFetchGET";

const SimulationLogic = () => {
  const [jsonData, setJsonData] = useState<GetBuildingsResponse>();
  const [floor, setFloor] = useState<number>(() => {
    const floorNum = localStorage.getItem("floor");
    return floorNum !== null ? parseInt(floorNum, 10) : 1;
  });

  useEffect(() => {
    const fetchData = async () => {
      const buildingId = localStorage.getItem("buildingId");
      try {
        const result: GetBuildingsResponse | string | undefined =
          await getBuilding(buildingId);

        if (typeof result !== "string" && typeof result !== "undefined") {
          setJsonData(result);
        } else {
          console.error("Error: Received unexpected string response");
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();

    const handleStorageChange = () => {
      const floorNum = localStorage.getItem("floor");
      if (floorNum !== null) {
        setFloor(parseInt(floorNum, 10));
      }
    };

    window.addEventListener("storage", handleStorageChange);

    return () => {
      window.removeEventListener("storage", handleStorageChange);
    };
  }, []);

  const generateBedrooms = () => {
    if (!jsonData) {
      return null;
    }
    const j = floor;
    if(j >= jsonData.components.length) return;

    const numOfRooms = jsonData.components[j].components.length;
    const bedrooms = [];

    for (let i = 0; i < numOfRooms; i++) {
      bedrooms.push(
        <Bedroom
          key={i}
          roomType={jsonData.components[j].components[i].roomType}
          width={jsonData.components[j].components[i].width}
          height={jsonData.components[j].components[i].lenght}
          label={
            jsonData.components[j].components[i].roomType +
            " " +
            jsonData.components[j].components[i].roomNumber
          }
        />
      );
    }

    return bedrooms;
  };

  return <div className="Components FloorPlan">{generateBedrooms()}</div>;
};

export default SimulationLogic;
