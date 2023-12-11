// function GenerateBuildingButton() {
//   return (
//     <button
//       style={{
//         cursor: "pointer",
//         border: "none",
//         borderRadius: "8px",
//         color: "#fff",
//         backgroundColor: "#2A80E6",
//         padding: "16px 32px",
//       }}
//     >
//       Generate the building
//     </button>
//   );
// }

import React, { ReactNode, MouseEvent } from "react";

interface GenerateBuildingButtonProps {
  onClick?: (event: MouseEvent<HTMLButtonElement>) => void;
  // children: ReactNode;
  // active: boolean;
}

const GenerateBuildingButton: React.FC<GenerateBuildingButtonProps> = ({
  onClick = () => { }
}) => {
  return (
    <button
      onClick={onClick}
      style={{
        cursor: "pointer",
        border: "none",
        borderRadius: "8px",
        color: "#fff",
        backgroundColor: "#2A80E6",
        padding: "16px 32px",
      }}
    >
      Generate the building
    </button>
  );
}

export default GenerateBuildingButton;
