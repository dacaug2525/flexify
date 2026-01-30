import React, { useState } from "react";
import Navbar from "../common/Navbar";
import {
  FaHome,
  FaUsers,
  FaUserTie,
  FaDumbbell,
  FaMoneyBill,
  FaCommentDots
} from "react-icons/fa";
import { Outlet, useNavigate } from "react-router-dom";

const AdminLayout = () => {
  const [active, setActive] = useState("Dashboard");
  const navigate = useNavigate();

  const menuItems = [
    { name: "Dashboard", icon: <FaHome />, path: "/admin/dashboard" },
    { name: "Members", icon: <FaUsers />, path: "/admin/members" },
    { name: "Trainers", icon: <FaUserTie />, path: "/admin/trainers" },
    { name: "Plans", icon: <FaDumbbell />, path: "/admin/plans" },
    { name: "Payments", icon: <FaMoneyBill />, path: "/admin/payments" },
    { name: "Feedback", icon: <FaCommentDots />, path: "/admin/feedback" },
  ];

  const handleNavigation = (item) => {
    setActive(item.name);
    navigate(item.path);
  };

  return (
    <div className="container-fluid">
      <div className="row min-vh-100">

        {/* Sidebar */}
        <div className="col-md-3 col-lg-2 bg-dark text-white p-3">
          <h4 className="text-center mb-4">Admin Panel</h4>

          <ul className="nav nav-pills flex-column gap-2">
            {menuItems.map((item) => (
              <li className="nav-item" key={item.name}>
                <button
                  className={`nav-link text-start w-100 d-flex align-items-center gap-2 ${
                    active === item.name ? "active" : "text-white"
                  }`}
                  onClick={() => handleNavigation(item)}
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
          <Outlet />
        </div>

      </div>
    </div>
  );
};

export default AdminLayout;
