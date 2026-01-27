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

  /* =======================
     VALIDATIONS
     ======================= */
  const isValidName = (name, min) => name.trim().length >= min;

  /* Email validation – ANY domain */

  const isValidEmail = (email) =>
  /^[a-zA-Z0-9._%+-]+@(gmail|yahoo|outlook|rediff|hotmail)\.com$/.test(email);

  const isValidPassword = (password) =>
    /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/.test(password);

  const isValidContact = (contact) =>
    /^[6-9]\d{9}$/.test(contact);

  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
    setError("");
    setSuccess("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    /* =======================
       VALIDATION CHECKS
       ======================= */
    if (!isValidName(user.fname, 3)) {
      setError("First name must be at least 3 characters");
      return;
    }

    if (!isValidName(user.lname, 2)) {
      setError("Last name must be at least 2 characters");
      return;
    }

    if (!isValidEmail(user.email)) {
  setError("Email must be Gmail, Yahoo, Outlook,Rediff or Hotmail");
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

    try {
      await axios.post("http://localhost:8080/flexify/register", {
        fname: user.fname,
        lname: user.lname,
        uname: user.uname,
        password: user.password,
        email: user.email,
        contact: user.contact,
        gender: user.gender,
        roleid: Number(user.roleid),
      });

      setSuccess("User registered successfully");

      // Reset form
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
      setError(err.response?.data || "Registration failed");
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100 bg-light">
      <form
        onSubmit={handleSubmit}
        className="p-4 bg-white rounded shadow"
        style={{ width: "100%", maxWidth: "450px" }}
      >
        <h2 className="mb-3 text-center">Register User</h2>

        {error && <div className="alert alert-danger">{error}</div>}
        {success && <div className="alert alert-success">{success}</div>}

        <input className="form-control mb-2" name="fname" value={user.fname} onChange={handleChange} placeholder="First Name" required />
        <input className="form-control mb-2" name="lname" value={user.lname} onChange={handleChange} placeholder="Last Name" required />
        <input className="form-control mb-2" name="uname" value={user.uname} onChange={handleChange} placeholder="Username" required />
        <input className="form-control mb-2" type="email" name="email" value={user.email} onChange={handleChange} placeholder="Email" required />
        <input className="form-control mb-2" name="contact" value={user.contact} onChange={handleChange} placeholder="Contact" required />
        <input className="form-control mb-2" type="password" name="password" value={user.password} onChange={handleChange} placeholder="Password" required />
        <input className="form-control mb-3" type="password" name="confirmPassword" value={user.confirmPassword} onChange={handleChange} placeholder="Confirm Password" required />

        <select className="form-select mb-2" name="gender" value={user.gender} onChange={handleChange} required>
          <option value="">Select Gender</option>
          <option>Male</option>
          <option>Female</option>
          <option>Other</option>
        </select>

        <select className="form-select mb-3" name="roleid" value={user.roleid} onChange={handleChange} required>
          <option value="">Select Role</option>
          <option value="1">Admin</option>
          <option value="2">Trainer</option>
          <option value="3">Member</option>
        </select>

        <button className="btn btn-primary w-100" type="submit">
          Register
        </button>
      </form>
    </div>
  );
}

export default Register;
