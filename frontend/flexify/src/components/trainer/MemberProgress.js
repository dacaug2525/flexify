import React, { useEffect, useState } from "react";
import axios from "axios";

const BASE_URL = "http://localhost:5259/api/progress";

const TrainerProgressManager = () => {
  const trainerId = localStorage.getItem("tid");

  const [members, setMembers] = useState([]);
  const [progressList, setProgressList] = useState([]);
  const [memberId, setMemberId] = useState("");
  const [weight, setWeight] = useState("");
  const [remark, setRemark] = useState("");
  const [editId, setEditId] = useState(null);

  // Load members
  useEffect(() => {
    if (!trainerId) return;
    axios.get(`${BASE_URL}/trainer/${trainerId}/members`)
      .then(res => setMembers(res.data))
      .catch(err => console.log(err));
  }, [trainerId]);

  // Load progress
  const loadProgress = () => {
    if (!trainerId) return;
    axios.get(`${BASE_URL}/trainer/${trainerId}`)
      .then(res => setProgressList(res.data))
      .catch(err => console.log(err));
  };

  useEffect(() => { loadProgress(); }, [trainerId]);

  const resetForm = () => {
    setMemberId("");
    setWeight("");
    setRemark("");
    setEditId(null);
  };

  const handleSubmit = () => {
    if (!memberId || !weight) {
      alert("Member and Weight are required");
      return;
    }

    const payload = {
      Mid: Number(memberId),
      Weight: Number(weight),
      Remark: remark || ""
    };

    if (editId) {
      // UPDATE
      axios.put(`${BASE_URL}/trainer/${trainerId}/${editId}`, payload)
        .then(() => { alert("Progress Updated"); resetForm(); loadProgress(); })
        .catch(err => alert(err.response?.data || "Update failed"));
    } else {
      // ADD
      axios.post(`${BASE_URL}/trainer/${trainerId}`, payload)
        .then(() => { alert("Progress Added"); resetForm(); loadProgress(); })
        .catch(err => alert(err.response?.data || "Add failed"));
    }
  };

  const handleEdit = (p) => {
    setEditId(p.progressId);
    setMemberId(p.mid);
    setWeight(p.weight);
    setRemark(p.remark || "");
  };

  return (
    <div className="container mt-4">
      {/* Add/Update Form */}
      <div className="card shadow mb-4">
        <div className="card-body">
          <h4 className="fw-bold text-primary">📈 Add / Update Progress</h4>
          <div className="row mt-3">
            <div className="col-md-4">
              <label className="form-label">Member</label>
              <select className="form-select" value={memberId} onChange={e => setMemberId(e.target.value)}>
                <option value="">Select Member</option>
                {members.map(m => <option key={m.memberId} value={m.memberId}>{m.memberName}</option>)}
              </select>
            </div>
            <div className="col-md-4">
              <label className="form-label">Weight (kg)</label>
              <input type="number" className="form-control" value={weight} onChange={e => setWeight(e.target.value)} />
            </div>
            <div className="col-md-4">
              <label className="form-label">Remark</label>
              <input type="text" className="form-control" value={remark} onChange={e => setRemark(e.target.value)} />
            </div>
          </div>
          <button className={`btn mt-3 ${editId ? "btn-warning" : "btn-success"}`} onClick={handleSubmit}>
            {editId ? "Update Progress" : "Add Progress"}
          </button>
        </div>
      </div>

      {/* Progress List */}
      <div className="card shadow">
        <div className="card-body">
          <h4 className="fw-bold text-primary">📋 Members Progress</h4>
          {progressList.length === 0 ? (
            <div className="alert alert-info mt-3">No progress added yet</div>
          ) : (
            <table className="table table-hover mt-3">
              <thead className="table-dark">
                <tr>
                  <th>Member</th>
                  <th>Weight</th>
                  <th>BMI</th>
                  <th>Remark</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {progressList.map(p => (
                  <tr key={p.progressId}>
                    <td>{p.memberName}</td>
                    <td>{p.weight}</td>
                    <td><span className="badge bg-success">{p.bmi}</span></td>
                    <td>{p.remark}</td>
                    <td><button className="btn btn-sm btn-warning" onClick={() => handleEdit(p)}>Edit</button></td>
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

export default TrainerProgressManager;
