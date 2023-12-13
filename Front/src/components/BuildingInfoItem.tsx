import React, { ReactNode, MouseEvent, useState } from "react";

interface BuildingInfoItemProps {
  children: ReactNode;
}

const BuildingInfoItem: React.FC<BuildingInfoItemProps> = ({ children }) => {
  return (
    <div style={{ display: "flex", flexDirection: "row", gap: "8px" }}>
      {children}
    </div>
  );
};

export default BuildingInfoItem;
