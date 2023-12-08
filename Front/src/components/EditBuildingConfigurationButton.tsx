function EditBuildingConfigurationButton() {
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
        width: "100px",
      }}
    >
      <p>Edit</p>
      <img src="../public/edit.png" width={"24px"} height={"24px"}></img>
    </button>
  );
}

export default EditBuildingConfigurationButton;
