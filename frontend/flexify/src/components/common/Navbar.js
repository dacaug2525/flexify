import React from "react";
import { useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  return (
    <nav
      className="navbar navbar-expand-lg bg-light border-bottom px-4"
      style={{ minHeight: "65px" }}   // 🔹 height increased
    >
      {/* LEFT : Brand */}
      <span
        className="navbar-brand fw-bold text-dark"
        style={{
          fontSize: "1.6rem",        // 🔹 bigger brand text
          letterSpacing: "1px",      // 🔹 clean premium look
          cursor: "pointer",
        }}
        onClick={() => navigate("/")}
      >
        Flexify
      </span>

      {/* CENTER : Links */}
      <div className="collapse navbar-collapse justify-content-center">
        <ul className="navbar-nav gap-4">
          <li className="nav-item">
            <span
              className="nav-link text-secondary"
              style={{ cursor: "pointer" }}
              onClick={() => navigate("/")}
            >
              Home
            </span>
          </li>
          <li className="nav-item">
            <span className="nav-link text-secondary">Plans</span>
          </li>
          <li className="nav-item">
            <span className="nav-link text-secondary">Trainers</span>
          </li>
          <li className="nav-item">
            <span className="nav-link text-secondary">Contact</span>
          </li>
        </ul>
      </div>

      {/* RIGHT : Login / Logout */}
      <div>
        {!user ? (
          <button
            className="btn btn-outline-dark btn-sm px-3"
            onClick={() => navigate("/login")}
          >
            Login
          </button>
        ) : (
          <button
            className="btn btn-outline-dark btn-sm px-3"
            onClick={handleLogout}
          >
            Logout
          </button>
        )}
      </div>
    </nav>
  );
}

export default Navbar;