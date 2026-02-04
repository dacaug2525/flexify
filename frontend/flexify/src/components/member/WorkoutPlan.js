import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";

const WorkoutPlan = () => {
  const { member } = useSelector((state) => state.member);
  const memberId = member?.mid;

  const [workouts, setWorkouts] = useState([]);
  const [error, setError] = useState("");

  /* ================= FETCH WORKOUT PLAN ================= */
  useEffect(() => {
    if (!memberId) return;

    const fetchWorkouts = async () => {
      try {
        const res = await axios.get(
          `http://localhost:8083/flexify/member/workout/${memberId}`,
        );
        setWorkouts(res.data);
      } catch {
        setError("Failed to fetch workout plan");
      }
    };

    fetchWorkouts();
  }, [memberId]);

  if (error) {
    return <div className="alert alert-danger">{error}</div>;
  }

  return (
    <div>
      {/* ===== PAGE HEADING ===== */}
      <h3 className="page-heading">🏋️ Workout Schedule</h3>

      {workouts.length === 0 ? (
        <p className="empty-text">No workouts assigned yet</p>
      ) : (
        <div className="workout-grid">
          {workouts.map((w) => (
            <div key={w.workoutId} className="workout-card">
              {/* ===== TRAINER ===== */}
              <h5 className="trainer-name">Trainer: {w.trainerName}</h5>

              <span className="workout-days">Assigned Days: {w.days}</span>

              {/* ===== DESCRIPTION ===== */}
              <div className="workout-body">
                <strong>Description</strong>
                <p className="workout-desc">{w.workoutDesc}</p>
              </div>
            </div>
          ))}
        </div>
      )}

      {/* ===== STYLES ===== */}
      <style>{`
        .page-heading {
          margin-bottom: 22px;
          color: #000103;
          font-weight: 600;
        }

        .empty-text {
          color: #64748b;
        }

        .workout-grid {
          display: grid;
          grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
          gap: 22px;
        }

        .workout-card {
          background: #ffffff;
          padding: 22px;
          border-radius: 18px;
          box-shadow: 0 10px 25px rgba(0,0,0,0.06);
          transition: all 0.3s ease;
        }

        .workout-card:hover {
          transform: translateY(-4px);
          box-shadow: 0 14px 30px rgba(0,0,0,0.08);
        }

        .trainer-name {
          color: #1e40af;
          font-weight: 600;
          margin-bottom: 4px;
        }

        .workout-days {
          font-size: 0.85rem;
          color: #6b7280;
        }

        .workout-body {
          margin-top: 14px;
        }

        .workout-desc {
          font-size: 0.9rem;
          color: #475569;
          margin-top: 6px;
          line-height: 1.5;
        }
      `}</style>
    </div>
  );
};

export default WorkoutPlan;
