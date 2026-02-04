import TrainerProfile from "./TrainerProfile";
import AssignedMembers from "./AssignedMembers";
import MemberAttendance from "./MembersAttendance";
import MemberProgress from "./MemberProgress";
import WorkoutToMember from "./WorkoutToMember";

import React, { useState, useEffect } from "react";
import axios from "axios";

import {
  FaHome,
  FaUser,
  FaUsers,
  FaDumbbell,
  FaCalendarAlt
} from "react-icons/fa";

const TrainerDashboard = () => {
  const [active, setActive] = useState("Dashboard");

  // ✅ DASHBOARD STATE
  const [dashboard, setDashboard] = useState({
    totalMembers: 0,
    
  });

  const [loading, setLoading] = useState(true);

  // ✅ FETCH DASHBOARD DATA
  useEffect(() => {
    const trainerId = localStorage.getItem("tid");

    if (!trainerId) return;

    axios
      .get(`http://localhost:5259/api/Dashboard/dashboard/${trainerId}`)

      .then((res) => {
        setDashboard(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Dashboard API error:", err);
        setLoading(false);
      });
  }, []);

  const menuItems = [
    { name: "Dashboard", icon: <FaHome /> },
    { name: "My Profile", icon: <FaUser /> },
    { name: "Assigned Members", icon: <FaUsers /> },
    { name: "Members Attendance", icon: <FaCalendarAlt /> },
    { name: "Members Progress", icon: <FaCalendarAlt /> },
    { name: "Workout Plans", icon: <FaDumbbell /> },
  ];

  return (
    <div className="container-fluid">
      <div className="row min-vh-100">

        {/* Sidebar */}
        <div className="col-md-3 col-lg-2 bg-dark text-white p-3">
          <ul className="nav nav-pills flex-column gap-2">
            {menuItems.map((item) => (
              <li className="nav-item" key={item.name}>
                <button
                  className={`nav-link text-start w-100 d-flex align-items-center gap-2 ${
                    active === item.name ? "active" : "text-white"
                  }`}
                  onClick={() => setActive(item.name)}
                >
                  {item.icon}
                  {item.name}
                </button>
              </li>
            ))}
          </ul>
        </div>

        {/* Main Content */}
        <div className="col-md-9 col-lg-10 p-4 bg-light">
          <h2 className="mb-4">{active}</h2>

          {/* ✅ DASHBOARD */}
          {active === "Dashboard" && (
            <div className="row g-4">
              <div className="col-md-4">
                <div className="card text-white bg-primary shadow">
                  <div className="card-body">
                    <h5>Total Members</h5>
                    <h2>{loading ? "..." : dashboard.totalMembers}</h2>
                  </div>
                </div>
              </div>
      
            </div>
          )}

          {active === "My Profile" && <TrainerProfile />}
          {active === "Assigned Members" && <AssignedMembers />}
          {active === "Members Attendance" && <MemberAttendance />}
          {active === "Members Progress" && <MemberProgress />}
          {active === "Workout Plans" && <WorkoutToMember />}

        </div>
      </div>
    </div>
  );
};

export default TrainerDashboard;
