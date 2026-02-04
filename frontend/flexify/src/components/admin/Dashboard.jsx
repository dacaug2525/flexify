import React, { useEffect, useState } from "react";
import axios from "axios";
import { FaUsers, FaDumbbell } from "react-icons/fa";

const AdminDashboard = () => {
  const [stats, setStats] = useState({ totalTrainers: 0, totalMembers: 0 });
  const [users, setUsers] = useState([]);

  useEffect(() => {
    // Fetch dashboard stats
    axios
      .get("http://localhost:8081/flexify/admin/dashboard")
      .then((res) => setStats(res.data))
      .catch((err) => console.error("Error fetching dashboard stats", err));

    // Fetch users (Name, Email, Role)
    axios
      .get("http://localhost:8081/flexify/admin/dashboard/users")
      .then((res) => setUsers(res.data))
      .catch((err) => console.error("Error fetching users", err));
  }, []);

  const cards = [
    { title: "Total Trainers", value: stats.totalTrainers, color: "#198754", icon: <FaDumbbell /> },
    { title: "Total Members", value: stats.totalMembers, color: "#0d6efd", icon: <FaUsers /> },
  ];

  return (
    <div className="container-fluid p-4 bg-light min-vh-100">
  {/* Welcome Message */}
  <h2 className="fw-bold mb-4" style={{ color: "#171a1f", display: "flex", alignItems: "center", gap: "0.5rem" }}>
    <FaUsers /> Welcome Admin
  </h2>

  {/* Info Cards */}
  <div className="row g-4 mb-5">
    {cards.map((c, i) => (
      <div key={i} className="col-xl-3 col-md-6">
        <div className="card text-white shadow border-0" style={{ background: c.color, borderRadius: 14 }}>
          <div className="card-body d-flex align-items-center gap-3">
            <div style={{ fontSize: 30 }}>{c.icon}</div>
            <div>
              <small>{c.title}</small>
              <h3 className="fw-bold mb-0">{c.value}</h3>
            </div>
          </div>
        </div>
      </div>
    ))}
  </div>

      {/* Users Table */}
      <div className="card shadow-sm border-0">
        <div className="card-header bg-dark text-white fw-bold">
          Trainer & Member Profiles
        </div>

        <div className="card-body p-0">
          <table className="table table-hover mb-0 align-middle">
            <thead className="table-secondary">
              <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
              </tr>
            </thead>

            <tbody>
              {users.map((u) => (
                <tr key={u.uid}>
                  <td>{u.name}</td>
                  <td>{u.email}</td>
                  <td>
                    <span className={`badge ${u.role === "Trainer" ? "bg-success" : "bg-primary"}`}>
                      {u.role}
                    </span>
                  </td>
                </tr>
              ))}

              {users.length === 0 && (
                <tr>
                  <td colSpan="3" className="text-center text-muted py-4">
                    No data available
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

export default AdminDashboard;
