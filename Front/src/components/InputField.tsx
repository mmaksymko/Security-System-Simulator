// InputField.tsx

import React from "react";
import styles from "./InputFile.module.css";

interface InputFieldProps {
  value: number;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const InputField: React.FC<InputFieldProps> = ({ value, onChange }) => {
  return (
    <input
      className={styles.input}
      type="text"
      placeholder="Enter a number"
      value={value}
      onChange={onChange}
    />
  );
};

export default InputField;
