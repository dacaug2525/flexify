import React, { useState } from "react";
import axios from "axios";

const AssignMembership = ({ memberId }) => {
  const [planId, setPlanId] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [status, setStatus] = useState("ACTIVE");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  const handleAssign = async (e) => {
    e.preventDefault();

    if (!planId || !startDate || !endDate) {
      setError("All fields are required");
      return;
    }

    try {
      await axios.post(
        "http://localhost:8080/member/membership/assign",
        {
          memberId,
          planId,
          startDate,
          endDate,
          status,
        },
      );
      setMessage("Membership assigned successfully");
      setError("");
    } catch {
      setError("Failed to assign membership");
    }
  };

  return (
    <form onSubmit={handleAssign}>
      {error && <div className="alert alert-danger">{error}</div>}
      {message && <div className="alert alert-success">{message}</div>}

      <div className="mb-3">
        <label>Plan ID</label>
        <input
          type="number"
          className="form-control"
          value={planId}
          onChange={(e) => setPlanId(e.target.value)}
          required
        />
      </div>

      <div className="mb-3">
        <label>Start Date</label>
        <input
          type="date"
          className="form-control"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
          required
        />
      </div>

      <div className="mb-3">
        <label>End Date</label>
        <input
          type="date"
          className="form-control"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
          required
        />
      </div>

      <div className="mb-3">
        <label>Status</label>
        <select
          className="form-control"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        >
          <option value="ACTIVE">Active</option>
          <option value="INACTIVE">Inactive</option>
        </select>
      </div>

      <button type="submit" className="btn btn-primary">
        Assign Membership
      </button>
    </form>
  );
};

export default AssignMembership;
