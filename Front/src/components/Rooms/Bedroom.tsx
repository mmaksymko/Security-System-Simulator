import "./RoomsStyle.scss";

 type Props = {
  roomType : string
  width : number
  height : number
  //label : string
 }

function Bedroom({roomType: bedType, width, height} : Props) {
  const style = {
    gridColumn: `span ${width}`,
    gridRow: `span ${height}`,
  };

  return <div className="Components Bedroom" id={`${bedType}`} style={style} >
      <div className="Door" />
  </div>;
}

export default Bedroom;
