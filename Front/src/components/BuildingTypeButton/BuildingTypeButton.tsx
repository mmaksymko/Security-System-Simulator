import React, { ReactNode, MouseEvent, useState } from "react";
import classes from "./BuildingTypeButton.module.css";
import classNames from "classnames";

interface BuildingTypeButtonProps {
  onClick?: (event: MouseEvent<HTMLButtonElement>) => void;
  children: ReactNode;
}

const BuildingTypeButton: React.FC<BuildingTypeButtonProps> = ({
  onClick = () => {},
  children,
}) => {
  const [toggleEnabled, setToggleEnabled] = useState(false);

  return (
    <button
      onClick={(e) => {
        onClick(e);
        setToggleEnabled(!toggleEnabled);
      }}
      className={classNames(classes.container, toggleEnabled && classes.active)}
    >
      <div style={{ display: "flex", flexDirection: "row", gap: "8px" }}>
        {children}
      </div>
    </button>
  );
};

export default BuildingTypeButton;
