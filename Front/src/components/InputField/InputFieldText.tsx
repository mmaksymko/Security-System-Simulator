// InputField.jsx
import React, { ChangeEvent } from "react";
import styles from "./InputFieldText.module.css";

interface InputFieldProps {
  value: string;
  onChange: (value: string) => void;
}

const InputField: React.FC<InputFieldProps> = ({ value, onChange }) => {
  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    const newValue = event.target.value;
    onChange(newValue);
  };

  return (
    <input
      type="text"
      value={value}
      onChange={handleInputChange}
      className={styles.input}
      placeholder="Enter a name"
    />
  );
};

export default InputField;
