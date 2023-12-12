import React, { useState, useEffect, MouseEvent } from "react";

interface RestoreSimulationButtonProps {
  onClick?: (event: MouseEvent<HTMLButtonElement>) => void;
}

function RestoreStateButton({ onClick }: RestoreSimulationButtonProps) {
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
      Restore state
    </button>
  );
}

export default RestoreStateButton;
