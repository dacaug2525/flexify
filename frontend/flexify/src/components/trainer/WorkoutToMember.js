import React, { useEffect, useState } from "react";
import axios from "axios";

const TrainerWorkoutManager = () => {
  const trainerId = localStorage.getItem("trainerId");

  const [members, setMembers] = useState([]);
  const [workouts, setWorkouts] = useState([]);

  const [memberId, setMemberId] = useState("");
  const [workoutDesc, setWorkoutDesc] = useState("");
  const [days, setDays] = useState("");

  const [editId, setEditId] = useState(null);

  // 🔹 Load members for dropdown
  useEffect(() => {
    axios
      .get(`http://localhost:5259/api/trainer/workouts/trainer/${trainerId}/members`)
      .then(res => setMembers(res.data))
      .catch(() => alert("Failed to load members"));
  }, [trainerId]);

  // 🔹 Load assigned workouts
  const loadWorkouts = () => {
    axios
      .get(`http://localhost:5259/api/trainer/workouts/${trainerId}`)
      .then(res => setWorkouts(res.data))
      .catch(() => alert("Failed to load workouts"));
  };

  useEffect(() => {
    loadWorkouts();
  }, [trainerId]);

  // 🔹 Add / Update workout
  const handleSubmit = () => {
    if (!memberId || !workoutDesc || !days) {
      alert("All fields required");
      return;
    }

    const payload = {
      trainerId,
      memberId,
      workoutDesc,
      days
    };

    if (editId) {
      axios
        .put(`http://localhost:5259/api/trainer/workouts/${editId}`, payload)
        .then(() => {
          alert("Workout Updated");
          resetForm();
          loadWorkouts();
        });
    } else {
      axios
        .post(`http://localhost:5259/api/trainer/workouts`, payload)
        .then(() => {
          alert("Workout Added");
          resetForm();
          loadWorkouts();
        });
    }
  };

  const resetForm = () => {
    setMemberId("");
    setWorkoutDesc("");
    setDays("");
    setEditId(null);
  };

  // 🔹 Edit workout
  const handleEdit = (w) => {
    setEditId(w.workoutId);
    setMemberId(w.memberId);
    setWorkoutDesc(w.workoutDesc);
    setDays(w.days);
  };

  return (
    <div className="container mt-4">

      {/* 🔹 ADD / UPDATE WORKOUT */}
      <div className="card shadow mb-4">
        <div className="card-body">
          <h4 className="fw-bold text-primary">
            🏋️ Add / Update Workout
          </h4>

          <div className="row mt-3">

            <div className="col-md-4">
              <label className="form-label">Member</label>
              <select
                className="form-select"
                value={memberId}
                onChange={(e) => setMemberId(e.target.value)}
              >
                <option value="">Select Member</option>
                {members.map(m => (
                  <option key={m.memberId} value={m.memberId}>
                    {m.memberName}
                  </option>
                ))}
              </select>
            </div>

            <div className="col-md-4">
              <label className="form-label">Workout Description</label>
              <input
                type="text"
                className="form-control"
                value={workoutDesc}
                onChange={(e) => setWorkoutDesc(e.target.value)}
              />
            </div>

            <div className="col-md-4">
              <label className="form-label">Days</label>
              <input
                type="text"
                className="form-control"
                placeholder="Mon, Wed, Fri"
                value={days}
                onChange={(e) => setDays(e.target.value)}
              />
            </div>
          </div>

          <button
            className={`btn mt-3 ${editId ? "btn-warning" : "btn-success"}`}
            onClick={handleSubmit}
          >
            {editId ? "Update Workout" : "Add Workout"}
          </button>
        </div>
      </div>

      {/* 🔹 WORKOUT LIST */}
      <div className="card shadow">
        <div className="card-body">
          <h4 className="fw-bold text-primary">
            📋 Assigned Workouts
          </h4>

          {workouts.length === 0 ? (
            <div className="alert alert-info mt-3">
              No workouts assigned yet
            </div>
          ) : (
            <table className="table table-hover mt-3">
              <thead className="table-dark">
                <tr>
                  <th>Member</th>
                  <th>Workout</th>
                  <th>Days</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {workouts.map(w => (
                  <tr key={w.workoutId}>
                    <td className="fw-bold">{w.memberName}</td>
                    <td>{w.workoutDesc}</td>
                    <td>{w.days}</td>
                    <td>
                      <button
                        className="btn btn-sm btn-warning"
                        onClick={() => handleEdit(w)}
                      >
                        Edit
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>

    </div>
  );
};

export default TrainerWorkoutManager;
