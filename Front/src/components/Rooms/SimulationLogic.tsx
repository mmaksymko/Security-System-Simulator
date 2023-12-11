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
  }, []);

  const generateBedrooms = () => {
    if (!jsonData) {
      return null;
    }
   
    const numOfRooms = jsonData.components[0].components.length;
    const bedrooms = [];

    for (let i = 0; i < numOfRooms; i++) {
      bedrooms.push(
        <Bedroom
          key={i}
          roomType={jsonData.components[0].components[i].roomType}
          width={jsonData.components[0].components[i].width}
          height={jsonData.components[0].components[i].lenght}
          label={
            jsonData.components[0].components[i].roomType + " " +
            jsonData.components[0].components[i].roomNumber
          }
        />
      );
    }

    return bedrooms;
  };

  return <div className="Components FloorPlan">{generateBedrooms()}</div>;
};

export default SimulationLogic;
