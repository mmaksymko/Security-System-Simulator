import React, { MouseEvent } from "react";

interface ContinueSimulationButtonProps {
  onClick?: (event: MouseEvent<HTMLButtonElement>) => void;
}

function ContinueSimulationButton({ onClick }: ContinueSimulationButtonProps) {
  return (
    <button
      onClick={onClick}
      style={{
        display: "flex",
        flexDirection: "row",
        gap: "12px",
        border: "none",
        backgroundColor: "transparent",
        zIndex: 10,
      }}
    >
      <div
        style={{
          backgroundColor: "#2A80E6",
          height: "84px",
          width: "42px",
          borderRadius: "16px",
        }}
      ></div>
      <div
        style={{
          backgroundColor: "#2A80E6",
          height: "84px",
          width: "42px",
          borderRadius: "16px",
        }}
      ></div>
    </button>
  );
}

export default ContinueSimulationButton;
