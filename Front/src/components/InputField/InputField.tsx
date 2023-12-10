// InputField.jsx
import React, { ChangeEvent } from "react";
import styles from "./InputFile.module.css";

interface InputFieldProps {
  value: number;
  onChange: (value: number) => void;
}

const InputField: React.FC<InputFieldProps> = ({ value, onChange }) => {
  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    const newValue = parseInt(event.target.value, 10) || 0;
    onChange(newValue);
  };

  return (
    <input
      type="number"
      value={value}
      onChange={handleInputChange}
      className={styles.input}
    />
  );
};

export default InputField;
