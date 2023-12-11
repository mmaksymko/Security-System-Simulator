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
    gridRow: `span ${height}`
  };

  return <div className="Components" id={`${bedType}`} color="#D9D9D9" style={style} >
      <div className="Door" />
      <div className="Label">{label}</div>
  </div>;
}

export default Bedroom;
