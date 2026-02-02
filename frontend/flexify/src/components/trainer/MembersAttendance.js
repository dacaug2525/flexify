import React, { useEffect, useState } from "react";
import axios from "axios";

const MemberAttendance = () => {
  const [attendance, setAttendance] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const trainerId = localStorage.getItem("trainerId");

    if (!trainerId) {
      setError("Trainer not logged in");
      return;
    }

    axios
      .get(`http://localhost:5259/api/attendance/trainer/${trainerId}`)
      .then((res) => {
        console.log("Attendance Data:", res.data);
        setAttendance(res.data);
      })
      .catch((err) => {
        console.error("AXIOS ERROR:", err);
        setError("Unable to load attendance");
      });
  }, []);

  if (error) {
    return <div className="alert alert-danger">{error}</div>;
  }

  return (
    <div className="card shadow border-0">
      <div className="card-body">
        

        {attendance.length === 0 ? (
          <div className="alert alert-info">
            No attendance records found.
          </div>
        ) : (
          <div className="table-responsive">
            <table className="table table-hover align-middle">
              <thead className="table-dark">
                <tr>
                  <th>Member ID</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Date</th>
                  <th>Status</th>
                </tr>
              </thead>

              <tbody>
                {attendance.map((a) => (
                  <tr key={a.attendanceId}>
                    <td className="fw-bold text-primary">{a.mid}</td>

                    <td className="fw-semibold">{a.memberName}</td>

                    <td>{a.email}</td>

                    <td>
                      {new Date(a.date).toLocaleDateString()}
                    </td>

                    <td>
                      <span
                        className={`badge rounded-pill px-3 py-2 ${
                          a.status?.toLowerCase() === "present"
                            ? "bg-success"
                            : "bg-danger"
                        }`}
                      >
                        {a.status}
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>

            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default MemberAttendance;
