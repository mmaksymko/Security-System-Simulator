import "./RoomsStyle.scss";

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

  return <div className="Components Bedroom" id={`${bedType}`} style={style} >
      <div className="Door" />
      <div className="Label">{label}</div>
  </div>;
}

export default Bedroom;
