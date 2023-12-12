interface SubmitButtonProps {
  onClick?: () => void;
  disabled?: boolean;
}

const SubmitButton: React.FC<SubmitButtonProps> = ({
  onClick = () => {},
  disabled = false,
}) => {
  return (
    <button
      onClick={onClick}
      disabled={disabled}
      style={{
        border: "none",
        backgroundColor: "#2A80E6",
        color: "white",
        borderRadius: "8px",
        padding: "8px 16px",
        marginTop: "32px",
        opacity: disabled ? 0.5 : 1,
      }}
    >
      Submit
    </button>
  );
};

export default SubmitButton;
