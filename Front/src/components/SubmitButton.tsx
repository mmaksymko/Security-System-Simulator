interface SubmitButtonProps {
  onClick?: () => void;
}

const SubmitButton: React.FC<SubmitButtonProps> = ({ onClick }) => {
  return (
    <button
      style={{
        border: "none",
        backgroundColor: "#2A80E6",
        color: "white",
        borderRadius: "8px",
        padding: "8px 16px",
        marginTop: "32px",
      }}
      onClick={onClick}
    >
      Submit
    </button>
  );
};

export default SubmitButton;
