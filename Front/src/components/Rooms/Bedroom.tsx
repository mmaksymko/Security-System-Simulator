import "./RoomsStyle.scss";
import React, { useState } from "react";

 type Props = {
  roomType : string
  width : number
  height : number
  label : string
 }

function Bedroom({roomType: bedType, width, height, label} : Props) {
  const style = {
    gridColumn: `span ${width}`,
    gridRow: `span ${height}`,
  };
  const handleRoomClick = () => {
    // Генеруємо нові координати смайлика при кліці на кімнату
    setEmojiPosition({
      top: Math.floor(Math.random() * height) + 1,
      left: Math.floor(Math.random() * width) + 1,
    });
  };
  const [emojiPosition, setEmojiPosition] = useState({
    top: Math.floor(Math.random() * height *(Math.floor(Math.random() * 100)  )),
    left: Math.floor(Math.random() * width *(Math.floor(Math.random() * 70))),
  });
  const smileyStyle = {
    top: `${emojiPosition.top * 100 / height}%`,
    left: `${emojiPosition.left * 100 / width}%`,
    position: "absolute" as const,
    zIndex: 2,
  };
 
  return <div className="Components Bedroom" id={`${bedType}`} style={style}onClick={handleRoomClick}>
      <div className="Door" />
      <div className="Label">{label}</div>
      <div className="Smiley" style={smileyStyle}>😊</div>
      </div>;

}

export default Bedroom;
