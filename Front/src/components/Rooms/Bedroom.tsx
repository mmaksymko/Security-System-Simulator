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
  const [emojiPosition, setEmojiPosition] = useState({
    top: Math.floor(Math.random() * height *(Math.floor(Math.random() * 45)  )),
    left: Math.floor(Math.random() * width *(Math.floor(Math.random() * 45))),
  });
  const smileyStyle = {
    top: `${emojiPosition.top }%`,
    left: `${emojiPosition.left}%`,
    position: "absolute" as const,
    zIndex: 2,
  };
  return <div className="Components Bedroom" id={`${bedType}`} style={style} >
      <div className="Door" />
      <div className="Label">{label}</div>
      <div className="Smiley" style={smileyStyle}>😊</div>
  </div>;
}

export default Bedroom;
