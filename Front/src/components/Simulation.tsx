import React, { useState } from "react";
import Bedroom from "./Rooms/Bedroom";
import "./Rooms/RoomsStyle.css";
import styles from "./InputFile.module.css";

const Simulation = () => {
  const [numOfRooms, setNumOfRooms] = useState(0);
  const numbers: number[] = [1, 2, 2];
  let j = 0;

  const handleInputChange = (e : React.ChangeEvent<HTMLInputElement>) => {
    const value = parseInt(e.target.value, 10);
    setNumOfRooms(value || 0);
  };

  const generateBedrooms = () => {
    const bedrooms = [];

    for (let i = 1; i <= numOfRooms; i++) {
      bedrooms.push(<Bedroom key={i} bedNum={numbers[j]} />);
      if (j == 2){
        j = 0;
      }
      else{
        j++;
      }
    }

    return bedrooms;
  };

  return (
    <div className="Components FloorPlan">
      <input
        className={styles.input}
        type="text"
        placeholder="Enter a number"
        onChange={handleInputChange}
      />
      {generateBedrooms()}
    </div>
  );
};

export default Simulation;


// const Simulation = () => {
//   const [numOfRooms, setNumOfRooms] = useState(10);
//   const roomsPerRow = 5; // Кількість кімнат у рядку
//   const maxRooms = 33; // Максимальна кількість кімнат
//   const roomWidth = 100;
//   const roomHeight = 100;
//   const wallThickness = 5;

//   const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
//     let value = parseInt(e.target.value, 10);
//     value = Math.min(value, maxRooms); // Обмежити кількість кімнат до maxRooms
//     setNumOfRooms(value || 1);
//   };

//   const generateRooms = () => {
//     const rooms = [];

//     for (let i = 0; i < numOfRooms; i++) {
//       const rowIndex = Math.floor(i / roomsPerRow);
//       const roomX = (i % roomsPerRow) * (roomWidth - wallThickness);
//       const roomY = rowIndex * (roomHeight - wallThickness);

//       const room = (
//         <Rect
//           key={`room${i}`}
//           x={roomX}
//           y={roomY}
//           width={roomWidth}
//           height={roomHeight}
//           fill="lightblue"
//           stroke="black"
//         />
//       );

//       const doorHole = (
//         <Rect
//           key={`doorHole${i}`}
//           x={roomX + roomWidth / 2 - 10}
//           y={roomY + roomHeight - 5}
//           width={20}
//           height={5}
//           fill="lightblue"
//         />
//       );

//       const doorStick = (
//         <Line
//           key={`doorStick${i}`}
//           points={[
//             roomX + roomWidth / 2,
//             roomY + roomHeight - 5,
//             roomX + roomWidth / 2,
//             roomY + roomHeight + 20,
//           ]}
//           stroke="black"
//         />
//       );

//       rooms.push(room, doorHole, doorStick);
//     }

//     return rooms;
//   };

//   return (
//     <div>
//       <input
//         type="text"
//         placeholder="Enter a number of rooms"
//         onChange={handleInputChange}
//       />
//       <Stage
//         width={roomsPerRow * (roomWidth - wallThickness) + 30}
//         height={
//           Math.ceil(numOfRooms / roomsPerRow) * (roomHeight - wallThickness) + 30
//         }
//       >
//         <Layer>{generateRooms()}</Layer>
//       </Stage>
//     </div>
//   );
// };

// export default Simulation;
