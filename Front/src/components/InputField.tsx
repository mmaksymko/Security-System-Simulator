import styles from "./InputFile.module.css";

function InputField() {
  return (
    <input
      className={styles.input}
      type="text"
      placeholder="Enter a number"
    ></input>
  );
}
export default InputField;
