import React, { useState } from "react";
import {
  FaHome,
  FaUserEdit,
  FaShoppingCart,
  FaCreditCard,
  FaDumbbell,
  FaCalendarCheck,
  FaRedo,
  FaStar
} from "react-icons/fa";

const MemberDashboard = () => {
  const [active, setActive] = useState("Dashboard");

  const menuItems = [
    { name: "Dashboard", icon: <FaHome /> },
    { name: "Update Account", icon: <FaUserEdit /> },
    { name: "Purchase Plan", icon: <FaShoppingCart /> },
    { name: "Make Payment", icon: <FaCreditCard /> },
    { name: "Workout Plan", icon: <FaDumbbell /> },
    { name: "Mark Attendance", icon: <FaCalendarCheck /> },
    { name: "Renew Membership", icon: <FaRedo /> },
    { name: "Feedback & Rating", icon: <FaStar /> },
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

          {active === "Dashboard" && (
            <div className="row g-4">
              <div className="col-md-4">
                <div className="card text-white bg-primary shadow">
                  <div className="card-body">
                    <h5 className="card-title">Active Plan</h5>
                    <h2>Gold</h2>
                  </div>
                </div>
              </div>

              <div className="col-md-4">
                <div className="card text-white bg-success shadow">
                  <div className="card-body">
                    <h5 className="card-title">Attendance</h5>
                    <h2>18 Days</h2>
                  </div>
                </div>
              </div>

              <div className="col-md-4">
                <div className="card text-white bg-warning shadow">
                  <div className="card-body">
                    <h5 className="card-title">Pending Payment</h5>
                    <h2>₹0</h2>
                  </div>
                </div>
              </div>
            </div>
          )}

          {active !== "Dashboard" && (
            <div className="card shadow">
              <div className="card-body">
                <h5>{active}</h5>
                <p>Feature UI will be added here.</p>
              </div>
            </div>
          )}
        </div>

      </div>
    </div>
  );
};

export default MemberDashboard;
