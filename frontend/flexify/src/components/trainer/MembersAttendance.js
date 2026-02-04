import React, { useEffect, useState } from "react";
import axios from "axios";

const MemberAttendance = () => {
  const [attendance, setAttendance] = useState([]);
  const [statusMap, setStatusMap] = useState({});
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  const trainerId = localStorage.getItem("tid");

  const fetchAttendance = () => {
    axios
      .get(`http://localhost:5259/api/attendance/trainer/${trainerId}`)
      .then((res) => {
        setAttendance(res.data);

        // initialize dropdown values
        const initialStatus = {};
        res.data.forEach(a => {
          initialStatus[a.mid] = a.status;
        });
        setStatusMap(initialStatus);
      })
      .catch(() => setError("Unable to load attendance"));
  };

  useEffect(() => {
    if (!trainerId) {
      setError("Trainer not logged in");
      return;
    }
    fetchAttendance();
  }, []);

  const handleStatusChange = (mid, value) => {
    setStatusMap({ ...statusMap, [mid]: value });
  };

  const markAttendance = (mid) => {
    const payload = {
      mid: mid,
      date: new Date().toISOString(),
      status: statusMap[mid]
    };

    axios
      .post("http://localhost:5259/api/attendance/mark", payload)
      .then(() => {
        setMessage("Attendance marked successfully");
        fetchAttendance(); // 🔄 refresh table
      })
      .catch(() => setError("Failed to mark attendance"));
  };

  return (
    <div className="card shadow border-0">
      <div className="card-body">

        <h4 className="mb-3">Mark Member Attendance</h4>

        {message && <div className="alert alert-success">{message}</div>}
        {error && <div className="alert alert-danger">{error}</div>}

        {attendance.length === 0 ? (
          <div className="alert alert-info">No members found.</div>
        ) : (
          <div className="table-responsive">
            <table className="table table-hover align-middle">
              <thead className="table-dark">
                <tr>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Date</th>
                  <th>Status</th>
                  <th>Action</th>
                </tr>
              </thead>

              <tbody>
                {attendance.map((a) => (
                  <tr key={a.mid}>
                    <td className="fw-semibold">{a.memberName}</td>
                    <td>{a.email}</td>
                    <td>{new Date().toLocaleDateString()}</td>

                    <td>
                      <select
                        className="form-select"
                        value={statusMap[a.mid] || "PRESENT"}
                        onChange={(e) =>
                          handleStatusChange(a.mid, e.target.value)
                        }
                      >
                        <option value="PRESENT">Present</option>
                        <option value="ABSENT">Absent</option>
                      </select>
                    </td>

                    <td>
                      <button
                        className="btn btn-success btn-sm"
                        onClick={() => markAttendance(a.mid)}
                      >
                        Save
                      </button>
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
