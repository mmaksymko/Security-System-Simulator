import "./RoomsStyle.scss";
import React, { useState } from "react";

 type Props = {
  roomType : string
  width : number
  height : number
  label : string
  emojies: string[]
 }

function Bedroom({roomType: bedType, width, height, label, emojies} : Props) {
  const style = {
    gridColumn: `span ${width}`,
    gridRow: `span ${height}`
  };
  const handleRoomClick = () => {
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
  const generateEmojies = () => {
    if (!emojies) {
      return null;
    }
    const emojiesList = [];

    for (let i = 0; i < emojies.length; i++) {
     
      emojiesList.push(
        <div
        className="Smiley" style={smileyStyle}>emojies[i]</div>
      
      );
    }

    return emojiesList;
  };
  return <div className="Components Bedroom" id={`${bedType}`} style={style}onClick={handleRoomClick}>
      <div className="Door" />
      <div className="Label">{label}</div>
      <div>{generateEmojies()}</div>
      <div className="Smiley" style={smileyStyle}>😊</div>
      </div>;

}

export default Bedroom;
