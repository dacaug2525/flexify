import React from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { logout } from "../redux/authSlice";

function Navbar() {
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();

  const { isAuthenticated } = useSelector((state) => state.auth);
  const isLoginPage = location.pathname === "/login";

  const handleLogout = () => {
    dispatch(logout());
    localStorage.removeItem("user");
    navigate("/login");
  };

  return (
    <nav
      style={{
        height: "70px",
        background: "linear-gradient(90deg, #020b1f, #04152f)",
        borderBottom: "1px solid rgba(255,255,255,0.08)",
        display: "grid",
        gridTemplateColumns: "auto 1fr auto",
        alignItems: "center",
        padding: "0 40px",
        position: "sticky",
        top: 0,
        zIndex: 1000,
      }}
    >
      {/* ===== BRAND ===== */}
      <div
        onClick={() => navigate("/")}
        style={{
          fontSize: "1.8rem",
          fontWeight: 900,
          letterSpacing: "2px",
          cursor: "pointer",
          color: "#ffffff",
        }}
      >
        Flex
        <span
          style={{
            color: "#38bdf8",
            textShadow: "0 0 12px rgba(56,189,248,0.6)",
          }}
        >
          ify
        </span>
      </div>

      {/* ===== CENTER LINKS ===== */}
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          gap: "36px",
          fontSize: "1rem",
          fontWeight: 500,
          color: "#cbd5e1",
        }}
      >
        <span style={linkStyle} onClick={() => navigate("/")}>
          Home
        </span>

        <span style={linkStyle} onClick={() => navigate("/plans")}>
          Plans
        </span>

        <span style={linkStyle} onClick={() => navigate("/contact")}>
          Contact Us
        </span>
      </div>

      {/* ===== AUTH ACTION ===== */}
      <div style={{ width: "120px", textAlign: "right" }}>
        {!isAuthenticated && !isLoginPage && (
          <button onClick={() => navigate("/login")} style={buttonStyle}>
            Login
          </button>
        )}

        {isAuthenticated && (
          <button onClick={handleLogout} style={buttonStyle}>
            Logout
          </button>
        )}
      </div>
    </nav>
  );
}

/* ===== STYLES ===== */

const linkStyle = {
  cursor: "pointer",
  transition: "color 0.25s ease",
};

const buttonStyle = {
  background: "transparent",
  border: "1px solid #38bdf8",
  color: "#38bdf8",
  padding: "6px 18px",
  borderRadius: "20px",
  fontWeight: 600,
  cursor: "pointer",
};

export default Navbar;
