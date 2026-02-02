import React, { useEffect, useState } from "react";
import axios from "axios";
import { FaUsers, FaDumbbell } from "react-icons/fa";

const AdminDashboard = () => {
  const [stats, setStats] = useState({
    totalTrainers: 0,
    totalMembers: 0,
  });

  useEffect(() => {
    // Fetch dashboard stats
    axios
      .get("http://localhost:8081/flexify/admin/dashboard")
      .then((res) => setStats(res.data))
      .catch((err) => console.error(err));
  }, []);

  const colors = {
    trainers: "#28a745",
    members: "#0d6efd",
  };

  const infoCards = [
    { title: "Total Trainers", value: stats.totalTrainers, icon: <FaDumbbell />, color: colors.trainers },
    { title: "Total Members", value: stats.totalMembers, icon: <FaUsers />, color: colors.members },
  ];

  return (
    <div className="container-fluid p-4" style={{ backgroundColor: "#f8f9fa", minHeight: "100vh" }}>
      {/* Header */}
      <div className="mb-4">
        <h2 className="fw-bold mb-1">Admin Dashboard</h2>
      </div>

      {/* Info Cards */}
      <div className="row g-4">
        {infoCards.map((card, index) => (
          <div key={index} className="col-lg-3 col-md-6">
            <div
              className="card text-white h-100 shadow-sm"
              style={{
                backgroundColor: card.color,
                borderRadius: "12px",
                transition: "transform 0.3s, box-shadow 0.3s",
                cursor: "pointer",
              }}
              onMouseEnter={(e) => {
                e.currentTarget.style.transform = "scale(1.05)";
                e.currentTarget.style.boxShadow = "0 10px 20px rgba(0,0,0,0.3)";
              }}
              onMouseLeave={(e) => {
                e.currentTarget.style.transform = "scale(1)";
                e.currentTarget.style.boxShadow = "0 4px 6px rgba(0,0,0,0.1)";
              }}
            >
              <div className="card-body d-flex align-items-center gap-3 p-4">
                <div
                  style={{
                    fontSize: "2rem",
                    width: "60px",
                    height: "60px",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                  }}
                >
                  {card.icon}
                </div>
                <div>
                  <h6 className="mb-1">{card.title}</h6>
                  <h3 className="fw-bold mb-0">{card.value}</h3>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AdminDashboard;
