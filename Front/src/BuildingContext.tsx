// BuildingContext.tsx
import React, { createContext, useContext, useState, ReactNode } from "react";

interface BuildingContextProps {
  children: ReactNode;
}

interface BuildingContextValues {
  buildingType: string;
  setBuildingType: React.Dispatch<React.SetStateAction<string>>;
  buildingName: string;
  setBuildingName: React.Dispatch<React.SetStateAction<string>>;
  numFloors: number;
  setNumFloors: React.Dispatch<React.SetStateAction<number>>;
  numRoomsPerFloor: number;
  setNumRoomsPerFloor: React.Dispatch<React.SetStateAction<number>>;
}

const BuildingContext = createContext<BuildingContextValues | undefined>(
  undefined
);

export function BuildingProvider({ children }: BuildingContextProps) {
  const [buildingType, setBuildingType] = useState<string>("");
  const [buildingName, setBuildingName] = useState<string>("");
  const [numFloors, setNumFloors] = useState<number>(0);
  const [numRoomsPerFloor, setNumRoomsPerFloor] = useState<number>(0);

  const values: BuildingContextValues = {
    buildingType,
    setBuildingType,
    buildingName,
    setBuildingName,
    numFloors,
    setNumFloors,
    numRoomsPerFloor,
    setNumRoomsPerFloor,
  };

  return (
    <BuildingContext.Provider value={values}>
      {children}
    </BuildingContext.Provider>
  );
}

export function useBuildingContext(): BuildingContextValues {
  const context = useContext(BuildingContext);

  if (!context) {
    throw new Error(
      "useBuildingContext must be used within a BuildingProvider"
    );
  }

  return context;
}
