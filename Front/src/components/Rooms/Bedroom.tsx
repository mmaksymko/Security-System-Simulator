import "./RoomsStyle.css";

 type Props = {
  bedNum : number
 }

function Bedroom({bedNum} : Props) {
  return (
    <div className="Components Bedroom" id={`bed-${bedNum}`} />
  );
}

export default Bedroom;
