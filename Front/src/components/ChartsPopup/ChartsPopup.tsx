import styles from "./ChartsPopup.module.css";
import ComboBox from "../ComboBox/ComboBox";
import { PolarArea } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    RadialLinearScale,
    ArcElement,
    Tooltip,
    Legend,
  } from 'chart.js';
import { useEffect, useState } from "react";

ChartJS.register(RadialLinearScale, ArcElement, Tooltip, Legend);

interface ChartPopupProps {
  onClose: () => void;
}
const ChartPopup: React.FC<ChartPopupProps> = ({ onClose }) => {
    interface ChartData {
        labels: string[];
        datasets: {
          label: string;
          data: number[];
          backgroundColor: string[];
          borderColor: string[];
          borderWidth: number;
        }[];
      }
      
const [chartData, setChartData] = useState<ChartData>({
    labels: [],
    datasets: [
      {
        label: 'Floor Data',
        data: [],
        backgroundColor: ['rgba(75, 192, 192, 0.2)'],
        borderColor: ['rgba(75, 192, 192, 0.2)'],
        borderWidth: 1,
      },
    ],
  });
  const [selectedFloor, setSelectedFloor] = useState(''); 

  const fetchData = async (selectedValue: string) => {
    try {
        console.log(selectedValue)
        let connection:string = ""
        if(selectedValue === "events"){
            connection = "http://localhost:8080/stats/even/building/"
        }
        else if(selectedValue === "dangel levels"){
            connection = "http://localhost:8080/stats/danger/building/"
        }
        else{
            connection = "http://localhost:8080/stats/location/events/"
        }
      const response = await fetch(connection + localStorage.getItem("buildingId"))
      .then(resp => resp.json())
      .then(data => {
        console.log(data)
        const labels : string[] = Object.keys(data);
      const values : number[] = Object.values(data);
      for(let i = 0; i < labels.length; ++i){
        labels[i] = labels[i] + " - " + values[i]
      }
      setChartData({
        labels: labels,
        datasets: [
          {
            label: 'Floor Data',
            data: values,
            backgroundColor: [
                'rgba(255, 99, 132, 0.5)',
                'rgba(54, 162, 235, 0.5)',
                'rgba(255, 206, 86, 0.5)',
                'rgba(75, 192, 192, 0.5)',
                'rgba(153, 102, 255, 0.5)',
                'rgba(255, 159, 64, 0.5)',
                'rgba(22, 196, 59, 0.5)',
                'rgba(250, 10, 210, 0.5)'
              ],
              borderColor: [
                'rgba(255, 99, 132, 0.5)',
                'rgba(54, 162, 235, 0.5)',
                'rgba(255, 206, 86, 0.5)',
                'rgba(75, 192, 192, 0.5)',
                'rgba(153, 102, 255, 0.5)',
                'rgba(255, 159, 64, 0.5)',
                'rgba(22, 196, 59, 0.5)',
                'rgba(250, 10, 210, 0.5)'
              ],
            borderWidth: 1,
          },
        ],
      });
      })      
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    if (selectedFloor) {
      fetchData(selectedFloor);
    }
  }, [selectedFloor]); 

  const handleSelect = (s :string)=>{
    const selectedValue = s;
    setSelectedFloor(s);
  }
  return (
    <div className={styles.background} onClick={onClose}>
      <div className={styles.container} onClick={(e) => e.stopPropagation()}>
        <h1>Statistics</h1>
        <img
          src="../public/close.png"
          height={"24px"}
          width={"24px"}
          className={styles.close}
          onClick={onClose}
        />
        <div className={styles.dropdownMenu}>
            <ComboBox
              options={["events", "dangel levels", "events per floor"]}
              onSelect={handleSelect}
              style={{ width: "180px" }}
              value={"select item first"}
              placeholder="Statistics type"
            />
        </div>
        <div className={styles.margins}>
        <PolarArea 
            data={chartData as ChartData}
            
        />
        </div>
      </div>
    </div>
  );
}

export default ChartPopup;
