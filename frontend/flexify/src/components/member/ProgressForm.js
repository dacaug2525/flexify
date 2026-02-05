import React, { useEffect, useState, useCallback } from "react";
import { useSelector } from "react-redux";
import axios from "axios";

const ProgressForm = () => {
  const { member } = useSelector((state) => state.member);

  const memberId = member?.mid;
  const height = member?.height; // ✅ FROM memberSlice (cm)

  const [weight, setWeight] = useState("");
  const [bmi, setBmi] = useState(null);

  const [history, setHistory] = useState([]);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  /* ================= FETCH PROGRESS HISTORY ================= */
  const fetchHistory = useCallback(async () => {
    if (!memberId) return;

    try {
      const res = await axios.get(
        `http://localhost:8080/member/progress/${memberId}`,
      );
      setHistory(res.data);
    } catch {
      setError("Failed to load progress history");
    }
  }, [memberId]);

  useEffect(() => {
    fetchHistory();
  }, [fetchHistory]);

  /* ================= SUBMIT PROGRESS ================= */
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setMessage("");

    if (!weight || weight <= 0) {
      setError("Please enter a valid weight");
      return;
    }

    if (!height || height <= 0) {
      setError("Height not available. Update profile first.");
      return;
    }

    const bmiValue = (weight / Math.pow(height / 100, 2)).toFixed(1);
    setBmi(bmiValue);

    try {
      await axios.post("http://localhost:8080/member/progress/add", {
        mid: memberId,
        weight: Number(weight),
        bmi: Number(bmiValue),
      });

      setMessage("📈 Progress updated successfully!");
      setWeight("");
      fetchHistory();
    } catch {
      setError("Failed to update progress");
    }
  };

  /* ================= BMI MESSAGE ================= */
  const getBmiMessage = (value) => {
    if (value < 18.5)
      return "Underweight – fuel your body, strength comes with nutrition 🥗";
    if (value < 24.9)
      return "Perfect range – stay consistent and keep pushing 💪";
    if (value < 29.9) return "Overweight – discipline + routine = results 🔥";
    return "High BMI – focus on lifestyle, consistency beats intensity 🧠";
  };

  return (
    <div
      style={{
        display: "grid",
        gridTemplateColumns: "1fr 1.2fr",
        gap: "30px",
      }}
    >
      {/* ================= ADD PROGRESS FORM ================= */}
      <div className="overview-card">
        <h3
          className="mb-4"
          style={{
            color: "#000103",
            fontWeight: 600,
          }}
        >
          📊 Update Progress
        </h3>

        {error && <div className="alert alert-danger py-2">{error}</div>}
        {message && <div className="alert alert-success py-2">{message}</div>}

        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label text-black">Weight (kg)</label>
            <input
              type="number"
              className="form-control"
              value={weight}
              onChange={(e) => setWeight(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="btn btn-primary w-100">
            Save Progress
          </button>
        </form>

        {bmi && (
          <div
            style={{
              marginTop: 20,
              padding: 14,
              borderRadius: 14,
              background: "#f8fafc",
              border: "1px solid #e5e7eb",
            }}
          >
            <h5 style={{ color: "#2563eb" }}>BMI: {bmi}</h5>
            <p style={{ fontSize: "0.9rem", color: "#374151" }}>
              {getBmiMessage(bmi)}
            </p>
          </div>
        )}
      </div>

      {/* ================= PROGRESS HISTORY ================= */}
      <div className="overview-card">
        <h3
          className="mb-4"
          style={{
            color: "#000103",
            fontWeight: 600,
          }}
        >
          📜 Progress History
        </h3>

        {history.length === 0 && (
          <p style={{ color: "#6b7280" }}>No progress recorded yet</p>
        )}

        <div style={{ maxHeight: "420px", overflowY: "auto" }}>
          {history.map((p) => (
            <div
              key={p.progressId}
              style={{
                padding: 14,
                marginBottom: 12,
                borderRadius: 14,
                background: "#f9fafb",
                border: "1px solid #e5e7eb",
              }}
            >
              <strong>{p.weight} kg</strong> • BMI {p.bmi}
              <div style={{ fontSize: "0.8rem", color: "#6b7280" }}>
                {new Date(p.recordedDate).toLocaleString("en-IN")}
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ProgressForm;
