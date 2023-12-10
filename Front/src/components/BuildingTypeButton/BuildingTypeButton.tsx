import React, { ReactNode, MouseEvent } from "react";
import classes from "./BuildingTypeButton.module.css";
import classNames from "classnames";

interface BuildingTypeButtonProps {
  onClick?: (event: MouseEvent<HTMLButtonElement>) => void;
  children: ReactNode;
  active: boolean;
}

const BuildingTypeButton: React.FC<BuildingTypeButtonProps> = ({
  onClick = () => {},
  children,
  active = false,
}) => {
  return (
    <button
      onClick={onClick}
      className={classNames(classes.container, active && classes.active)}
    >
      <div style={{ display: "flex", flexDirection: "row", gap: "8px" }}>
        {children}
      </div>
    </button>
  );
};

export default BuildingTypeButton;
