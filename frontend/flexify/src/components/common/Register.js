import React, { useState } from "react";
import axios from "axios";

function Register() {
  const [user, setUser] = useState({
    fname: "",
    lname: "",
    uname: "",
    password: "",
    confirmPassword: "",
    email: "",
    contact: "",
    gender: "",
    roleid: "",
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  // ===================== VALIDATIONS =====================
  const isValidName = (name, min) => name.trim().length >= min;

  const isValidEmail = (email) =>
    /^[a-zA-Z0-9._%+-]+@(gmail|yahoo|outlook|rediff|hotmail)\.com$/.test(email);

  const isValidPassword = (password) =>
    /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/.test(password);

  const isValidContact = (contact) =>
    /^[6-9]\d{9}$/.test(contact);

  // ===================== HANDLERS =====================
  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
    setError("");
    setSuccess("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // ===== VALIDATIONS =====
    if (!isValidName(user.fname, 3)) {
      setError("First name must be at least 3 characters");
      return;
    }
    if (!isValidName(user.lname, 2)) {
      setError("Last name must be at least 2 characters");
      return;
    }
    if (!isValidEmail(user.email)) {
      setError("Email must be Gmail, Yahoo, Outlook, Rediff or Hotmail");
      return;
    }
    if (!isValidContact(user.contact)) {
      setError("Contact number must be 10 digits and start with 6–9");
      return;
    }
    if (!isValidPassword(user.password)) {
      setError(
        "Password must have 8 chars, 1 uppercase, 1 number & 1 special character"
      );
      return;
    }
    if (user.password !== user.confirmPassword) {
      setError("Passwords do not match");
      return;
    }
    if (!user.roleid) {
      setError("Please select a role");
      return;
    }

    try {
      // ===== SEND PAYLOAD MATCHING BACKEND =====
      const payload = {
        fname: user.fname,
        lname: user.lname,
        uname: user.uname,
        password: user.password,
        email: user.email,
        contact: user.contact,
        gender: user.gender,
        roleid: parseInt(user.roleid),
      };

      await axios.post("http://localhost:8080/auth/register", payload, {
        headers: { "Content-Type": "application/json" },
      });

      setSuccess("User registered successfully");
      setUser({
        fname: "",
        lname: "",
        uname: "",
        password: "",
        confirmPassword: "",
        email: "",
        contact: "",
        gender: "",
        roleid: "",
      });
    } catch (err) {
      const msg =
        err.response?.data?.message ||
        err.response?.data?.error ||
        "Registration failed";
      setError(msg);
    }
  };

  // ===================== RENDER =====================
  return (
    <div className="d-flex justify-content-center mt-5">
      <div className="card shadow-lg border-0 w-100" style={{ maxWidth: "520px" }}>
        {/* Card Header */}
        <div className="card-header bg-dark text-white text-center fw-bold">
          User Registration
        </div>

        <div className="card-body p-4">
          {error && <div className="alert alert-danger">{error}</div>}
          {success && <div className="alert alert-success">{success}</div>}

          <form onSubmit={handleSubmit}>
            <div className="row g-3">

              {/* First Name */}
              <div className="col-md-6">
                <label className="form-label fw-semibold">First Name</label>
                <input
                  type="text"
                  className="form-control"
                  name="fname"
                  value={user.fname}
                  onChange={handleChange}
                  placeholder="Enter first name"
                  required
                />
              </div>

              {/* Last Name */}
              <div className="col-md-6">
                <label className="form-label fw-semibold">Last Name</label>
                <input
                  type="text"
                  className="form-control"
                  name="lname"
                  value={user.lname}
                  onChange={handleChange}
                  placeholder="Enter last name"
                  required
                />
              </div>

              {/* Username */}
              <div className="col-12">
                <label className="form-label fw-semibold">Username</label>
                <input
                  type="text"
                  className="form-control"
                  name="uname"
                  value={user.uname}
                  onChange={handleChange}
                  placeholder="Choose a username"
                  required
                />
              </div>

              {/* Email */}
              <div className="col-12">
                <label className="form-label fw-semibold">Email</label>
                <input
                  type="email"
                  className="form-control"
                  name="email"
                  value={user.email}
                  onChange={handleChange}
                  placeholder="example@gmail.com"
                  required
                />
              </div>

              {/* Contact */}
              <div className="col-12">
                <label className="form-label fw-semibold">Contact Number</label>
                <input
                  type="text"
                  className="form-control"
                  name="contact"
                  value={user.contact}
                  onChange={handleChange}
                  placeholder="10-digit mobile number"
                  required
                />
              </div>

              {/* Password */}
              <div className="col-md-6">
                <label className="form-label fw-semibold">Password</label>
                <div className="input-group">
                  <input
                    type={showPassword ? "text" : "password"}
                    className="form-control"
                    name="password"
                    value={user.password}
                    onChange={handleChange}
                    placeholder="Strong password"
                    required
                  />
                  <span
                    className="input-group-text"
                    style={{ cursor: "pointer" }}
                    onClick={() => setShowPassword(!showPassword)}
                  >
                    <i className={`bi ${showPassword ? "bi-eye-slash" : "bi-eye"}`}></i>
                  </span>
                </div>
              </div>

              {/* Confirm Password */}
              <div className="col-md-6">
                <label className="form-label fw-semibold">Confirm Password</label>
                <div className="input-group">
                  <input
                    type={showPassword ? "text" : "password"}
                    className="form-control"
                    name="confirmPassword"
                    value={user.confirmPassword}
                    onChange={handleChange}
                    placeholder="Re-enter password"
                    required
                  />
                  <span className="input-group-text">
                    <i className="bi bi-lock"></i>
                  </span>
                </div>
              </div>

              {/* Gender */}
              <div className="col-12">
                <label className="form-label fw-semibold">Gender</label>
                <select
                  className="form-select"
                  name="gender"
                  value={user.gender}
                  onChange={handleChange}
                  required
                >
                  <option value="">Select Gender</option>
                  <option>Male</option>
                  <option>Female</option>
                  <option>Other</option>
                </select>
              </div>

              {/* Role */}
              <div className="col-12">
                <label className="form-label fw-semibold">Role</label>
                <select
                  className="form-select"
                  name="roleid"
                  value={user.roleid}
                  onChange={handleChange}
                  required
                >
                  <option value="">Select Role</option>
                  <option value={3}>Member</option>
                  <option value={2}>Trainer</option>
                </select>
              </div>
            </div>

            <button className="btn btn-primary w-100 mt-4 fw-semibold">
              Register
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Register;
