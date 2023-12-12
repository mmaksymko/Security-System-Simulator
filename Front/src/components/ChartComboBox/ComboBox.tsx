import React, { useState, useEffect, CSSProperties } from "react";
import styles from "./ComboBox.module.css";

interface ComboBoxProps {
  options: string[];
  onSelect: (selectedOption: string) => void;
  onElementSelect: (selectedOption: React.ChangeEvent<HTMLSelectElement>) => void;
  style?: CSSProperties;
  value?: string | null;
  placeholder?: string;
}

const ComboBox: React.FC<ComboBoxProps> = ({
  options,
  onSelect,
  style,
  value,
  placeholder,
}) => {
  const [selectedOption, setSelectedOption] = useState<string | null>(
    value || null
  );
  const [isDropdownOpen, setDropdownOpen] = useState<boolean>(false);

  const handleOptionClick = (option: string) => {
    setSelectedOption(option);
    onSelect(option);
    setDropdownOpen(false);
  };

  const toggleDropdown = () => {
    setDropdownOpen((prev) => !prev);
  };

  useEffect(() => {
    setSelectedOption(value || null);
  }, [value]);

  return (
    <div className={styles["combo-box"]} style={style}>
      <div
        className={`${styles["selected-option"]} ${
          isDropdownOpen ? styles["open"] : ""
        }`}
        onClick={toggleDropdown}
      >
        {selectedOption !== null
          ? selectedOption
          : placeholder || "Select an option"}
        <span className={styles["arrow"]}>
          <img
            src="../public/dropdown_open.png"
            height={"12px"}
            width={"12px"}
          ></img>
        </span>
      </div>
      {isDropdownOpen && (
        <ul className={styles["options-list"]}>
          {options.map((option) => (
            <li key={option} onClick={() => handleOptionClick(option)}>
              {option}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default ComboBox;
