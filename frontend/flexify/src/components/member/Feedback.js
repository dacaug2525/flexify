import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import { FaStar } from "react-icons/fa";

const Feedback = () => {
  const { member } = useSelector((state) => state.member);
  const mid = member?.mid;

  const [trainers, setTrainers] = useState([]);
  const [ratings, setRatings] = useState({});
  const [comments, setComments] = useState({});
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  /* ================= FETCH TRAINERS ================= */
  useEffect(() => {
    if (!mid) return;

    axios
      .get(`http://localhost:8083/flexify/member/trainer/${mid}`)
      .then((res) => setTrainers(res.data))
      .catch(() => setError("Unable to load trainers"));
  }, [mid]);

  /* ================= STAR CLICK ================= */
  const handleRating = (trainerId, value) => {
    setRatings((prev) => ({ ...prev, [trainerId]: value }));
  };

  /* ================= COMMENT ================= */
  const handleComment = (trainerId, value) => {
    setComments((prev) => ({ ...prev, [trainerId]: value }));
  };

  /* ================= SUBMIT FEEDBACK ================= */
  const submitFeedback = async (trainerId) => {
    setError("");
    setMessage("");

    if (!ratings[trainerId]) {
      setError("Please select rating");
      return;
    }

    try {
      await axios.post("http://localhost:8083/flexify/member/feedback/add", {
        mid: mid,
        tid: trainerId,
        rating: ratings[trainerId],
        comment: comments[trainerId] || "",
      });

      setMessage("Feedback submitted successfully ✅");

      setRatings((prev) => ({ ...prev, [trainerId]: 0 }));
      setComments((prev) => ({ ...prev, [trainerId]: "" }));
    } catch {
      setError("Failed to submit feedback");
    }
  };

  return (
    <div>
      {/* ===== HEADING ===== */}
      <h3
        className="mb-4"
        style={{
          color: "#2563eb",
          fontWeight: 600,
        }}
      >
        ⭐ Trainer Feedback
      </h3>

      {error && <div className="alert alert-danger">{error}</div>}
      {message && <div className="alert alert-success">{message}</div>}

      {trainers.length === 0 && (
        <p style={{ color: "#64748b" }}>No trainers assigned</p>
      )}

      {/* ===== CARDS GRID ===== */}
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fill, minmax(320px, 1fr))",
          gap: "28px",
        }}
      >
        {trainers.map((t) => (
          <div key={t.trainerId} className="feedback-card">
            {/* TRAINER INFO */}
            <div style={{ marginBottom: 14 }}>
              <h5 className="trainer-name">{t.trainerName}</h5>
              <span className="trainer-sub">
                Experience: {t.experience} yrs
              </span>
            </div>

            <div className="trainer-sub" style={{ marginBottom: 14 }}>
              📧 {t.email}
              <br />
              📞 {t.contact}
            </div>

            {/* STAR RATING */}
            <div style={{ marginBottom: 16 }}>
              {[1, 2, 3, 4, 5].map((star) => (
                <FaStar
                  key={star}
                  size={22}
                  style={{ marginRight: 6, cursor: "pointer" }}
                  color={
                    (ratings[t.trainerId] || 0) >= star ? "#facc15" : "#cbd5e1"
                  }
                  onClick={() => handleRating(t.trainerId, star)}
                />
              ))}
            </div>

            {/* COMMENT */}
            <textarea
              className="form-control light-input"
              placeholder="Write your feedback..."
              rows={3}
              style={{ marginBottom: 14 }}
              value={comments[t.trainerId] || ""}
              onChange={(e) => handleComment(t.trainerId, e.target.value)}
            />

            {/* SUBMIT */}
            <button
              className="btn btn-primary w-100"
              onClick={() => submitFeedback(t.trainerId)}
            >
              Submit Feedback
            </button>
          </div>
        ))}
      </div>

      {/* ===== STYLES ===== */}
      <style>{`
        .feedback-card {
          background: #f8fafc;
          padding: 28px;
          border-radius: 22px;
          box-shadow: 0 14px 35px rgba(15, 23, 42, 0.15);
          transition: all 0.35s ease;
        }

        .feedback-card:hover {
          transform: translateY(-6px);
          box-shadow: 0 22px 45px rgba(15, 23, 42, 0.25);
        }

        .trainer-name {
          color: #04163e;
          font-weight: 600;
          margin-bottom: 4px;
        }

        .trainer-sub {
          font-size: 0.85rem;
          color: #475569;
        }

        .light-input {
          border-radius: 12px;
          border: 1px solid #cbd5f5;
          font-size: 0.9rem;
        }

        .light-input:focus {
          border-color: #2563eb;
          box-shadow: 0 0 0 0.15rem rgba(37,99,235,0.25);
        }
      `}</style>
    </div>
  );
};

export default Feedback;
