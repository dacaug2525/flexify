import React, { useEffect, useState } from "react";
import axios from "axios";
import MemberProgress from "./MemberProgress"; // ✅ IMPORTANT

const AssignedMembers = () => {
  const [members, setMembers] = useState([]);
  const [error, setError] = useState("");
  const [selectedMember, setSelectedMember] = useState(null);

  useEffect(() => {
    const trainerId = localStorage.getItem("trainerId");

    if (!trainerId) {
      setError("Trainer not logged in");
      return;
    }

    axios
      .get(`http://localhost:5259/api/trainer/members/${trainerId}`)
      .then((res) => {
        console.log("Assigned Members:", res.data);
        setMembers(res.data.data);
      })
      .catch((err) => {
        console.error(err);

        setError("Unable to load assigned members");
      });
  }, []);

  if (error) {
    return <div className="alert alert-danger">{error}</div>;
  }

  return (
    <div className="card shadow border-0">
      <div className="card-body">
        <h4 className="fw-bold text-primary mb-3">👥 Assigned Members</h4>

        {members.length === 0 ? (
          <div className="alert alert-info">No members assigned yet.</div>
        ) : (
          <>
            <div className="table-responsive">
              <table className="table table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>Member ID</th>
                    <th>Member</th>
                    <th>Email</th>
                    <th>Contact</th>
                    <th>Height (cm)</th>
                    <th>Weight (kg)</th>
                    <th>Status</th>
                  </tr>
                </thead>

                <tbody>
                  {members.map((m) => (
                    <tr
                      key={m.mid}
                      onClick={() => setSelectedMember(m)}
                      style={{ cursor: "pointer" }}
                    >
                      <td className="fw-bold text-primary">{m.mid}</td>

                      <td>
                        {m.fname} {m.lname}
                        <div className="text-muted small">@{m.uname}</div>
                      </td>

                      <td>{m.email}</td>
                      <td>{m.contact}</td>
                      <td>{m.height}</td>
                      <td>{m.weight}</td>

                      <td>
                        <span
                          className={`badge rounded-pill px-3 py-2 ${
                            m.status?.toLowerCase() === "active"
                              ? "bg-success"
                              : "bg-danger"
                          }`}
                        >
                          {m.status}
                        </span>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>

            {/* ✅ SHOW PROGRESS WHEN MEMBER IS SELECTED */}
            {selectedMember && <MemberProgress member={selectedMember} />}
          </>
        )}
      </div>
    </div>
  );
};

export default AssignedMembers;
