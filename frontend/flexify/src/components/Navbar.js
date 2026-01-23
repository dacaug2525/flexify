import React from "react";
import { useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();

  const handleClick = (e, path) => {
    e.preventDefault(); //stop page reload
    navigate(path);
  };

  return (
    <nav className="container-fluid px-4">
      <div className="d-flex align-items-center justify-content-between">
        {/* LEFT : Logo */}
        <a href="#" className="h4 mb-0 text-primary text-decoration-none">
          Flexify
        </a>

        {/* CENTER : Nav items */}
        <ul className="nav mx-auto">
          <li className="nav-item">
            <a
              href="/"
              onClick={(e) => {
                handleClick(e, "/");
              }}
              className="nav-link text-primary"
            >
              Home
            </a>
          </li>
          <li className="nav-item">
            <a href="#" className="nav-link text-primary">
              Plans
            </a>
          </li>
          <li className="nav-item">
            <a href="#" className="nav-link text-primary">
              Trainers
            </a>
          </li>
          <li className="nav-item">
            <a href="#" className="nav-link text-primary">
              About Us
            </a>
          </li>
          <li className="nav-item">
            <a href="#" className="nav-link text-primary">
              Contact Us
            </a>
          </li>
        </ul>

        {/* RIGHT : Buttons */}
        <div>
          <button
            className="btn btn-sm btn-primary me-2"
            onClick={() => navigate("/login")}
          >
            Login
          </button>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
