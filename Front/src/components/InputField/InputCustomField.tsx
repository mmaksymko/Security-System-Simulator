import React, { ChangeEvent, useState } from "react";
import styles from "./InputFile.module.css";

interface InputCustomFieldProps {
  value: number;
  onChange: (value: number) => void;
  isValid?: boolean;
  placeholder?: string;
  readOnly?: boolean;
  className?: string;
}

const InputCustomField: React.FC<InputCustomFieldProps> = ({
  value,
  onChange,
  isValid = true,
  placeholder,
  readOnly = false,
  className,
}) => {
  const [touched, setTouched] = useState(false);

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    setTouched(true);
    const newValue = parseInt(event.target.value, 10) || 0;
    onChange(newValue);
  };

  return (
    <input
      type="number"
      value={value === 0 ? "" : value}
      onChange={handleInputChange}
      placeholder={placeholder}
      className={`${styles.input} ${
        touched ? (isValid ? "" : styles.invalid) : ""
      } ${className}`}
      readOnly={readOnly}
    />
  );
};

export default InputCustomField;
