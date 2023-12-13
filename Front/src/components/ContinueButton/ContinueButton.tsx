import React, { ReactNode, MouseEvent } from "react";

interface ContinueButtonProps {
  onClick?: (event: MouseEvent<HTMLButtonElement>) => void;
  disabled?: boolean;
}

const ContinueButton: React.FC<ContinueButtonProps> = ({
  onClick = () => {},
  disabled = false,
}) => {
    return (
      <button
        style={{
          cursor: "pointer",
          border: "none",
          borderRadius: "8px",
          color: "#fff",
          backgroundColor: "#2A80E6",
          padding: "16px 32px",
          width: 217,
          height: 48
        }}
      >
        Continue
      </button>
    );
  }
  
  export default ContinueButton;
  