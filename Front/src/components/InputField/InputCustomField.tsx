import React, { ChangeEvent } from "react";
import styles from "./InputFile.module.css";

interface InputCustomFieldProps {
  value: number;
  onChange: (value: number) => void;
  placeholder?: string;
}

const InputCustomField: React.FC<InputCustomFieldProps> = ({
  value,
  onChange,
  placeholder,
}) => {
  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    const newValue = parseInt(event.target.value, 10) || 0;
    onChange(newValue);
  };

  return (
    <input
      type="number"
      value={value === 0 ? "" : value} // Змінено тут
      onChange={handleInputChange}
      placeholder={placeholder}
      className={styles.input}
    />
  );
};

export default InputCustomField;
