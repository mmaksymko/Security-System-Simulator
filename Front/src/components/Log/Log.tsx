import styles from "./Log.module.css";
import LogTable from "./LogTable";

function Log() {
  return (
    <div className={styles.logContainer}>
      <h1 className={styles.title}>Log</h1>
      <LogTable />
    </div>
  );
}

export default Log;
