import React, { useEffect, useState } from "react";
import axios from "axios";
import { FaUserTie, FaUsers, FaCheckCircle } from "react-icons/fa";

const TrainerAssignment = () => {
  const [members, setMembers] = useState([]);
  const [trainers, setTrainers] = useState([]);
  const [assignments, setAssignments] = useState([]);

  const [selectedMember, setSelectedMember] = useState("");
  const [selectedTrainer, setSelectedTrainer] = useState("");

  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    fetchMembers();
    fetchTrainers();
    fetchAssignments();
  }, []);

  // Fetch all members
  const fetchMembers = async () => {
    try {
      const res = await axios.get(
        "http://localhost:8081/flexify/admin/members/list"
      );
      setMembers(res.data || []);
    } catch (err) {
      console.error(err);
    }
  };

  // Fetch all trainers
  const fetchTrainers = async () => {
    try {
      const res = await axios.get(
        "http://localhost:8081/flexify/admin/trainers/list"
      );
      setTrainers(res.data || []);
    } catch (err) {
      console.error(err);
    }
  };

  // Fetch existing assignments
  const fetchAssignments = async () => {
    try {
      const res = await axios.get(
        "http://localhost:8081/flexify/admin/assignments/list"
      );
      setAssignments(res.data || []);
    } catch (err) {
      console.error(err);
    }
  };

  // Handle assign trainer
  const handleAssign = async () => {
    setError("");
    setSuccess("");

    if (!selectedMember || !selectedTrainer) {
      setError("Please select both Member and Trainer");
      return;
    }

    setLoading(true);
    try {
      await axios.post(
        "http://localhost:8081/flexify/admin/assignments/assign-trainer",
        {
          mid: selectedMember,
          tid: selectedTrainer,
        }
      );

      setSuccess("Trainer assigned successfully!");
      setSelectedMember("");
      setSelectedTrainer("");
      fetchAssignments(); // Refresh assignment list
    } catch (err) {
      setError(
        err.response?.data || "Assignment failed. Member may already have a trainer."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container-fluid">

      {/* HEADER */}
      <div className="mb-4">
        <h3 className="fw-bold mb-1">Trainer Assignments</h3>
      </div>

      {/* ASSIGN FORM */}
      <div className="card shadow-sm border-0 mb-4">
        <div className="card-header bg-dark text-white fw-semibold">
          Assign Trainer
        </div>

        <div className="card-body">
          <div className="row g-4 align-items-end">

            {/* MEMBER SELECT */}
            <div className="col-md-5">
              <label className="form-label fw-semibold">
                <FaUsers className="me-2" />
                Select Member
              </label>
              <select
                className="form-select"
                value={selectedMember}
                onChange={(e) => setSelectedMember(e.target.value)}
              >
                <option value="">-- Choose Member --</option>
                {members.map((m) => (
                  <option key={m.mid} value={m.mid}>
                    {m.fname} {m.lname} ({m.uname})
                  </option>
                ))}
              </select>
            </div>

            {/* TRAINER SELECT */}
            <div className="col-md-5">
              <label className="form-label fw-semibold">
                <FaUserTie className="me-2" />
                Select Trainer
              </label>
              <select
                className="form-select"
                value={selectedTrainer}
                onChange={(e) => setSelectedTrainer(e.target.value)}
              >
                <option value="">-- Choose Trainer --</option>
                {trainers.map((t) => (
                  <option key={t.tid} value={t.tid}>
                    {t.fname} {t.lname} ({t.experience} yrs)
                  </option>
                ))}
              </select>
            </div>

            {/* ASSIGN BUTTON */}
            <div className="col-md-2 d-grid">
              <button
                className="btn btn-success fw-semibold"
                onClick={handleAssign}
                disabled={loading}
              >
                <FaCheckCircle className="me-1" />
                {loading ? "Assigning..." : "Assign"}
              </button>
            </div>
          </div>

          {/* STATUS MESSAGES */}
          {error && <div className="alert alert-danger mt-3">{error}</div>}
          {success && <div className="alert alert-success mt-3">{success}</div>}
        </div>
      </div>

      {/* ASSIGNMENTS LIST */}
      <div className="card shadow-sm border-0">
        <div className="card-header bg-light fw-semibold">
          Current Assignments
        </div>
        <div className="table-responsive">
          <table className="table table-hover align-middle mb-0">
            <thead className="table-dark">
              <tr>
                <th>Member</th>
                <th>Trainer</th>
                <th>Assigned On</th>
              </tr>
            </thead>
            <tbody>
              {assignments.length > 0 ? (
                assignments.map((a) => (
                  <tr key={a.assignmentId}>
                    <td>{a.memberName}</td>
                    <td>{a.trainerName}</td>
                    <td>{new Date(a.assignDate).toLocaleDateString()}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="3" className="text-center text-muted py-4">
                    No assignments found
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default TrainerAssignment;
