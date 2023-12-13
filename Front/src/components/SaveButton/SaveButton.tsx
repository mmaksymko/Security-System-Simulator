import React, { useState, useEffect, MouseEvent } from "react";

interface SaveSimulationButtonProps {
  onClick?: (event: MouseEvent<HTMLButtonElement>) => void;
}

function SaveButton({ onClick }: SaveSimulationButtonProps) {
  return (
    <button
      onClick={onClick}
      style={{
        border: "none",
        padding: "12px 16px",
        color: "white",
        backgroundColor: "#2A80E6",
        borderRadius: "8px",
      }}
    >
      Save state
    </button>
  );
}

export default SaveButton;
