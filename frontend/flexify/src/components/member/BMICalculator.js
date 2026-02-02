import React, { useState } from "react";
import { useSelector } from "react-redux";
import "./BMICalculator.css";

const BMICalculator = () => {
  /* ===== REDUX DATA ===== */
  const { member } = useSelector((state) => state.member);
  const { user } = useSelector((state) => state.auth);

  const height = member?.height || ""; // cm
  const gender = user?.gender || "MALE";

  /* ===== STATE ===== */
  const [weight, setWeight] = useState("");
  const [bmi, setBmi] = useState(null);
  const [category, setCategory] = useState("");
  const [message, setMessage] = useState("");

  /* ===== BMI LOGIC ===== */
  const calculateBMI = () => {
    if (!weight || !height) return;

    const bmiValue = (weight / Math.pow(height / 100, 2)).toFixed(1);

    setBmi(bmiValue);

    if (bmiValue < 18.5) {
      setCategory("Underweight");
      setMessage(
        "Fuel your body properly. Strength training + calorie surplus will help you build lean muscle 💪",
      );
    } else if (bmiValue < 25) {
      setCategory("Fit Zone");
      setMessage(
        "You're in an excellent range. Maintain consistency and keep pushing your limits 🔥",
      );
    } else if (bmiValue < 30) {
      setCategory("Overweight");
      setMessage(
        "Fat loss with strength training will unlock visible transformation 🚀",
      );
    } else {
      setCategory("High BMI");
      setMessage(
        "Consistency beats intensity. Start small, stay disciplined, results will follow 🧠",
      );
    }
  };

  /* ===== COLOR CLASS ===== */
  const getBmiClass = () => {
    if (bmi < 18.5) return "bmi-underweight";
    if (bmi < 25) return "bmi-fit";
    if (bmi < 30) return "bmi-overweight";
    return "bmi-obese";
  };

  return (
    <div className="bmi-card hover-card">
      <h4 className="bmi-title">⚖️ BMI Calculator</h4>

      {/* ===== FORM ===== */}
      <div className="bmi-form">
        <input
          type="number"
          placeholder="Weight (kg)"
          value={weight}
          onChange={(e) => setWeight(e.target.value)}
        />

        <input
          type="number"
          placeholder="Height (cm)"
          value={height}
          disabled
        />

        <div className="bmi-gender">
          <span>Gender</span>
          <strong>{gender}</strong>
        </div>

        <button className="calculate-btn" onClick={calculateBMI}>
          Calculate BMI
        </button>
      </div>

      {/* ===== RESULT ===== */}
      {bmi && (
        <div className="bmi-result">
          <h2 className={getBmiClass()}>{bmi}</h2>

          <p className={`bmi-category ${getBmiClass()}`}>{category}</p>

          <p className="bmi-message">{message}</p>

          <small>
            BMI is a reference metric. Strength, habits & consistency matter
            more.
          </small>
        </div>
      )}
    </div>
  );
};

export default BMICalculator;
