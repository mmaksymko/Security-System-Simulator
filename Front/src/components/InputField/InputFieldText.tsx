import React, { ChangeEvent, useState } from "react";
import styles from "./InputFieldText.module.css";

interface InputFieldProps {
  value: string;
  onChange: (value: string) => void;
  isValid?: boolean;
  placeholder?: string;
  readOnly?: boolean;
  className?: string;
}

const InputFieldText: React.FC<InputFieldProps> = ({
  value,
  onChange,
  isValid = true,
  placeholder = "Enter a name",
  readOnly = false,
  className,
}) => {
  const [touched, setTouched] = useState(false);

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    setTouched(true);
    const newValue = event.target.value;
    onChange(newValue);
  };
  return (
    <input
      type="text"
      value={value}
      onChange={handleInputChange}
      className={`${styles.input} ${
        touched ? (isValid ? "" : styles.invalid) : ""
      } ${className}`}
      placeholder={placeholder}
      readOnly={readOnly}
    />
  );
};

export default InputFieldText;
