import React from "react";
import styles from "./LogTable.module.css";

interface LogEntry {
  floor: string;
  room: string;
  sensor: string;
  time: string;
}

interface LogTableProps {
  logData: LogEntry[];
  setLogData: React.Dispatch<React.SetStateAction<LogEntry[]>>;
}

function LogTable({ logData, setLogData }: LogTableProps) {
  return (
    <div className={styles.logTableContainer}>
      <table className={styles.logTable}>
        <thead>
          <tr>
            <th className={styles.columnFloor}>Floor</th>
            <th className={styles.columnRoom}>Room</th>
            <th className={styles.columnSensor}>Sensor</th>
            <th className={styles.columnTime}>Time</th>
          </tr>
        </thead>
        <tbody>
          {logData.map((logEntry, index) => (
            <tr key={index}>
              <td>{logEntry.floor}</td>
              <td>{logEntry.room}</td>
              <td>{logEntry.sensor}</td>
              <td>{logEntry.time}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default LogTable;
