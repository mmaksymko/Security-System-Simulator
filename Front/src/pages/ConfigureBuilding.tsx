import { Link } from "react-router-dom";
import BuildingTypeButton from "../components/BuildingTypeButton";

const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
  console.log("office button clicked");
};

function ConfigureBuilding() {
  return (
    <div>
      <BuildingTypeButton onClick={handleClick}>
        <img
          src="../public/office-building_1f3e2.png"
          width={"30px"}
          height={"30px"}
        ></img>
        <p
          style={{
            margin: "0px",
            alignSelf: "center",
          }}
        >
          Office building
        </p>
      </BuildingTypeButton>
      <BuildingTypeButton onClick={handleClick}>
        <img
          src="../public/house_1f3e0.png"
          width={"30px"}
          height={"30px"}
        ></img>
        <p
          style={{
            margin: "0px",
            alignSelf: "center",
          }}
        >
          Office building
        </p>
      </BuildingTypeButton>
    </div>
  );
}

export default ConfigureBuilding;
