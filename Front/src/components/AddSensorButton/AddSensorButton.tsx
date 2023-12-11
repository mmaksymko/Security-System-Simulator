import React from "react";

interface AddSensorButtonProps {
  onClick: () => void;
}

function AddSensorButton({ onClick }: AddSensorButtonProps) {
  return (
    <button
      style={{
        cursor: "pointer",
        border: "none",
        borderRadius: "8px",
        color: "#fff",
        backgroundColor: "#2A80E6",
        padding: "16px 32px"
      }}
      onClick={onClick} // Now the AddSensorButton accepts onClick prop
    >
      Add sensor
    </button>
  );
}

export default AddSensorButton;
