// RandomNumberButton.jsx
import React from "react";

interface RandomNumberButtonProps {
  onGenerate: (value: number) => void;
  min?: number;
  max?: number;
}

const RandomNumberButton: React.FC<RandomNumberButtonProps> = ({
  onGenerate,
  min = 1,
  max = 100,
}) => {
  const generateRandomNumber = () => {
    const randomNumber = Math.floor(Math.random() * (max - min + 1) + min);
    onGenerate(randomNumber);
  };

  return (
    <button
      onClick={generateRandomNumber}
      style={{
        backgroundColor: "#000",
        border: "none",
        borderRadius: "8px",
        color: "#fff",
        cursor: "pointer",
        padding: "8px 24px",
      }}
    >
      Get random number
    </button>
  );
};

export default RandomNumberButton;
