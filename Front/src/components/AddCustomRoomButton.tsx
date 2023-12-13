import React, { ReactNode, MouseEvent } from "react";

interface AddCustomRoomButtonProps {
  onClick?: (event: MouseEvent<HTMLButtonElement>) => void;
  disabled?: boolean;
}

const AddCustomRoomButton: React.FC<AddCustomRoomButtonProps> = ({
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
      Add
    </button>
  );
};

export default AddCustomRoomButton;
