import React, { useState, MouseEvent } from "react";

interface StartSimulationButtonProps {
  onClick?: (event: MouseEvent<HTMLButtonElement>) => void;
}

function StartSimulationButton({ onClick }: StartSimulationButtonProps) {
  const [isVisible, setIsVisible] = useState(true);

  const handleClick = (event: MouseEvent<HTMLButtonElement>) => {
    setIsVisible(false);
    // Call the provided onClick function, if any
    if (onClick) {
      onClick(event);
    }
  };

  return (
    <button
      onClick={handleClick}
      id="startSimulation"
      style={{
        border: "none",
        borderRadius: "12px",
        backgroundColor: "#2A80E6",
        color: "white",
        fontFamily: "Mutter LVS",
        fontSize: "32px",
        alignItems: "center",
        justifyContent: "center",
        display: isVisible ? "block" : "none",
        padding: "24px 24px",
        zIndex: "10",
      }}
    >
      Start simulation
    </button>
  );
}

export default StartSimulationButton;
