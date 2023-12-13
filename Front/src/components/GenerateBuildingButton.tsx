import React, { ReactNode, MouseEvent } from "react";

interface GenerateBuildingButtonProps {
  onClick?: (event: MouseEvent<HTMLButtonElement>) => void;
  disabled?: boolean;
}

const GenerateBuildingButton: React.FC<GenerateBuildingButtonProps> = ({
  onClick = () => {},
  disabled = false,
}) => {
  return (
    <button
      onClick={onClick}
      disabled={disabled}
      style={{
        cursor: "pointer",
        border: "none",
        borderRadius: "8px",
        color: "#fff",
        backgroundColor: "#2A80E6",
        padding: "16px 32px",
        opacity: disabled ? 0.5 : 1,
      }}
    >
      Generate the building
    </button>
  );
};

export default GenerateBuildingButton;
