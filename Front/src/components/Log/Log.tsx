import React, { useEffect } from "react";
import styles from "./Log.module.css";

interface LogEntry {
  dangerLevel: string;
  eventType: string;
  location: string;
  happenedAt: string;
  result: boolean;
}

interface LogProps {
  logData: LogEntry[];
}

const Log: React.FC<LogProps> = ({ logData }) => {
  const formatTime = (timestamp: string) => {
    const date = new Date(timestamp);
    return date.toLocaleString();
  };

  const formatBoolean = (value: boolean) => (value ? "Yes" : "No");
  const convertToLowerCase = (inputString: string) => {
    return inputString.toLowerCase();
  };
  const reversedLogData = [...logData].reverse();

  return (
    <div className={styles.logContainer}>
      <h1 className={styles.title}>Log</h1>
      <div className={styles.logTableContainer}>
        <table className={styles.logTable}>
          <thead>
            <tr>
              <th className={styles.columnDanger}>Danger level</th>
              <th className={styles.columnType}>Event type</th>
              <th className={styles.columnLocation}>Location</th>
              <th className={styles.columnTime}>Time</th>
              <th className={styles.columnResult}>Result</th>
            </tr>
          </thead>
          <tbody>
            {reversedLogData.map((logEntry, index) => (
              <tr key={index}>
                <td>{convertToLowerCase(logEntry.dangerLevel)}</td>
                <td>{convertToLowerCase(logEntry.eventType)}</td>
                <td>{logEntry.location}</td>
                <td>{formatTime(logEntry.happenedAt)}</td>
                <td>{formatBoolean(logEntry.result)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Log;
