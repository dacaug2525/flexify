import React, { useEffect, useState, useCallback } from "react";
import { useSelector } from "react-redux";
import axios from "axios";

const Attendance = () => {
  const { member } = useSelector((state) => state.member);
  const mid = member?.mid;

  const [status, setStatus] = useState("PRESENT");
  const [history, setHistory] = useState([]);
  const [presentCount, setPresentCount] = useState(0);
  const [absentCount, setAbsentCount] = useState(0);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  /* ===== TODAY DATE ===== */
  const today = new Date().toLocaleDateString("en-IN", {
    weekday: "long",
    day: "2-digit",
    month: "short",
    year: "numeric",
  });

  /* ================= FETCH HISTORY ================= */
  const fetchHistory = useCallback(async () => {
    if (!mid) return;
    try {
      const res = await axios.get(
        `http://localhost:8083/flexify/member/attendence/${mid}`,
      );
      setHistory(res.data);
    } catch {
      setError("Failed to load attendance history");
    }
  }, [mid]);

  /* ================= FETCH COUNTS ================= */
  const fetchCounts = useCallback(async () => {
    if (!mid) return;
    try {
      const [p, a] = await Promise.all([
        axios.get(
          `http://localhost:8083/flexify/member/attendence/count/${mid}/PRESENT`,
        ),
        axios.get(
          `http://localhost:8083/flexify/member/attendence/count/${mid}/ABSENT`,
        ),
      ]);
      setPresentCount(p.data);
      setAbsentCount(a.data);
    } catch {
      // optional
    }
  }, [mid]);

  useEffect(() => {
    fetchHistory();
    fetchCounts();
  }, [fetchHistory, fetchCounts]);

  /* ================= MARK ATTENDANCE ================= */
  const markAttendance = async () => {
    setMessage("");
    setError("");

    if (!mid) {
      setError("Member profile not available");
      return;
    }

    try {
      await axios.post("http://localhost:8083/flexify/member/attendence/mark", {
        mid,
        status,
      });

      setMessage("Attendance marked successfully ✅");
      fetchHistory();
      fetchCounts();
    } catch (err) {
      setError(
        err.response?.data?.message || "Attendance already marked for today",
      );
    }
  };

  return (
    <div className="overview-card">
      {/* ===== HEADING ===== */}
      <h3 className="attendance-heading">📅 Attendance</h3>

      {/* ===== SUMMARY CARDS ===== */}
      <div className="count-wrapper">
        <div className="count-card present">
          <span className="count-label">Present</span>
          <strong>{presentCount}</strong>
        </div>

        <div className="count-card absent">
          <span className="count-label">Absent</span>
          <strong>{absentCount}</strong>
        </div>
      </div>

      {/* ===== TODAY ===== */}
      <div className="today-box">
        📆 Today: <span>{today}</span>
      </div>

      {/* ===== STATUS ===== */}
      <div className="status-btns">
        <button
          className={`btn ${
            status === "PRESENT" ? "btn-success" : "btn-outline-success"
          }`}
          onClick={() => setStatus("PRESENT")}
        >
          ✅ Present
        </button>

        <button
          className={`btn ${
            status === "ABSENT" ? "btn-danger" : "btn-outline-danger"
          }`}
          onClick={() => setStatus("ABSENT")}
        >
          ❌ Absent
        </button>
      </div>

      <button className="btn btn-primary mb-3" onClick={markAttendance}>
        Mark Attendance
      </button>

      {/* ===== FEEDBACK ===== */}
      {error && <div className="text-danger mb-2">{error}</div>}
      {message && <div className="text-success mb-2">{message}</div>}

      {/* ===== HISTORY ===== */}
      <h5 className="history-heading">📜 Attendance History</h5>

      <div className="history-box">
        {history.length === 0 && (
          <p className="muted-text">No attendance records</p>
        )}

        {history.map((a) => (
          <div key={a.attendenceId} className="history-row">
            <span>
              {new Date(a.date).toLocaleDateString("en-IN", {
                day: "2-digit",
                month: "short",
                year: "numeric",
              })}
            </span>

            <span
              className={`status ${
                a.status === "PRESENT" ? "present" : "absent"
              }`}
            >
              {a.status}
            </span>
          </div>
        ))}
      </div>

      {/* ===== STYLES ===== */}
      <style>{`
        .attendance-heading {
          color: #2563eb;
          margin-bottom: 20px;
          font-weight: 600;
        }

        .count-wrapper {
          display: flex;
          gap: 20px;
          margin-bottom: 25px;
        }

        .count-card {
          background: #ffffff;
          padding: 16px;
          border-radius: 14px;
          min-width: 120px;
          text-align: center;
          box-shadow: 0 8px 20px rgba(0,0,0,0.08);
        }

        .count-card strong {
          display: block;
          font-size: 1.6rem;
          margin-top: 4px;
        }

        .count-card.present strong { color: #16a34a; }
        .count-card.absent strong { color: #dc2626; }

        .count-label {
          font-size: 1.0rem;
          color: #010307;
        }

        .today-box {
          margin-bottom: 18px;
          padding: 10px 14px;
          border-radius: 12px;
          background: #f8fafc;
          color: #334155;
          display: inline-block;
          font-weight: 500;
        }

        .today-box span {
          color: #2563eb;
        }

        .status-btns {
          display: flex;
          gap: 16px;
          margin-bottom: 20px;
        }

        .history-heading {
          margin-top: 25px;
          margin-bottom: 12px;
          color: #1e40af;
        }

        .history-box {
          max-height: 260px;
          overflow-y: auto;
        }

        .history-row {
          display: flex;
          justify-content: space-between;
          padding: 10px 12px;
          margin-bottom: 8px;
          border-radius: 10px;
          background: #f8fafc;
        }

        .history-row span {
          font-size: 0.9rem;
        }

        .status.present {
          color: #16a34a;
          font-weight: 600;
        }

        .status.absent {
          color: #dc2626;
          font-weight: 600;
        }

        .muted-text {
          color: #64748b;
        }
      `}</style>
    </div>
  );
};

export default Attendance;
