import React, { useState, useEffect } from 'react';
import styles from './Switch.module.css';

interface SwitchProps {
  onToggle: () => void;
  defaultOn?: boolean; // Додали опційний параметр defaultOn
}

const Switch: React.FC<SwitchProps> = ({ onToggle, defaultOn = false }) => {
  const [isChecked, setChecked] = useState(defaultOn);

  useEffect(() => {
    // Забезпечуємо коректне оновлення стану, якщо defaultOn змінилося
    setChecked(defaultOn);
  }, [defaultOn]);

  const toggleSwitch = () => {
    setChecked(!isChecked);
    onToggle();
  };

  return (
    <div
      className={`${styles['switch-container']} ${isChecked ? styles['checked'] : ''}`}
      onClick={toggleSwitch}
    >
      <div className={styles['switch-track']}>
        <div className={styles['switch-handle']}></div>
      </div>
    </div>
  );
};

export default Switch;
