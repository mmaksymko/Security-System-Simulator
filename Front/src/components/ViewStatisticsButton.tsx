import React from "react";

interface ViewStatisticsButtonButtonProps {
  onClick: () => void;
}

const ViewStatisticsButton: React.FC<
    ViewStatisticsButtonButtonProps
> = ({ onClick }) => {
  return (
    <button
      style={{
        cursor: "pointer",
        display: "flex",
        flexDirection: "row",
        gap: "8px",
        justifyContent: "center",
        alignItems: "center",
        border: "none",
        borderRadius: "8px",
        color: "#fff",
        backgroundColor: "#2A80E6",
        padding: "8px 16px",
        width: "150px",
      }}
      onClick={onClick} // Add onClick prop here
    >
      <p>Statistics</p>
      <img
        src="../public/stats.png"
        width={"24px"}
        height={"24px"}
        alt="Edit icon"
      />
    </button>
  );
};

export default ViewStatisticsButton;
