import React, { ChangeEvent, useState } from "react";
import styles from "./InputFile.module.css";

interface InputFieldProps {
  value: number;
  onChange: (value: number) => void;
  isValid?: boolean;
  placeholder?: string;
  readOnly?: boolean;
  className?: string;
}

const InputField: React.FC<InputFieldProps> = ({
  value,
  onChange,
  isValid = true,
  placeholder = "0",
  readOnly = false,
  className,
}) => {
  const [touched, setTouched] = useState(false);

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    setTouched(true);
    const newValue = parseInt(event.target.value, 10);
    onChange(newValue);
  };

  return (
    <input
      type="number"
      value={value}
      onChange={handleInputChange}
      onBlur={() => setTouched(true)}
      className={`${styles.input} ${
        touched || isValid ? "" : styles.invalid
      } ${className}`}
      placeholder={placeholder}
      readOnly={readOnly}
    />
  );
};

export default InputField;
