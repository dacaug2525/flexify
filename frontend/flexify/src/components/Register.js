import React, { useState } from "react";

// send HTTP requests to backend
import axios from "axios";

function Register() {
  // State to store all form input values
  // Keys MUST match backend DTO field names
  const [user, setUser] = useState({
    fname: "",
    lname: "",
    uname: "",
    password: "",
    email: "",
    contact: "",
    gender: "",
    roleid: "", // Role ID (1=Admin, 2=Trainer, 3=Member)
  });

  // Handles input changes for all fields
  // Uses 'name' attribute to update the correct field
  const handleChange = (e) => {
    setUser({
      ...user, // Keep existing values
      [e.target.name]: e.target.value, // Update changed field
    });
  };

  // Called when form is submitted
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send POST request to backend register API
      await axios.post("http://localhost:8080/flexify/register", {
        ...user,
        roleid: parseInt(user.roleid),
      });

      alert("User registered successfully");
    } catch (error) {
      // Handle API or server errors
      console.error(error);
      alert("Registration failed");
    }
  };

  return (
    // Center the form vertically and horizontally
    <div className="d-flex justify-content-center align-items-center min-vh-100 bg-light">
      {/* Registration form */}
      <form
        onSubmit={handleSubmit}
        className="p-4 bg-white rounded shadow"
        style={{ width: "100%", maxWidth: "450px" }}
      >
        <h2 className="mb-4 text-center">Register User</h2>

        {/* First Name input */}
        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            name="fname"
            placeholder="First Name"
            onChange={handleChange}
            required
          />
          <label>First Name</label>
        </div>

        {/* Last Name input */}
        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            name="lname"
            placeholder="Last Name"
            onChange={handleChange}
            required
          />
          <label>Last Name</label>
        </div>

        {/* Username input */}
        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            name="uname"
            placeholder="Username"
            onChange={handleChange}
            required
          />
          <label>Username</label>
        </div>

        {/* Password input */}
        <div className="form-floating mb-3">
          <input
            type="password"
            className="form-control"
            name="password"
            placeholder="Password"
            onChange={handleChange}
            required
          />
          <label>Password</label>
        </div>

        {/* Email input */}
        <div className="form-floating mb-3">
          <input
            type="email"
            className="form-control"
            name="email"
            placeholder="Email"
            onChange={handleChange}
            required
          />
          <label>Email</label>
        </div>

        {/* Contact number input */}
        <div className="form-floating mb-3">
          <input
            type="tel"
            className="form-control"
            name="contact"
            placeholder="Contact"
            onChange={handleChange}
            required
          />
          <label>Contact</label>
        </div>

        {/* Gender dropdown */}
        <div className="mb-3">
          <label className="form-label fw-semibold">Gender</label>
          <select
            className="form-select"
            name="gender"
            onChange={handleChange}
            required
          >
            <option value="">Select Gender</option>
            <option>Male</option>
            <option>Female</option>
            <option>Other</option>
          </select>
        </div>

        {/* Role dropdown */}
        <div className="mb-4">
          <label className="form-label fw-semibold">Role</label>
          <select
            className="form-select"
            name="roleid"
            onChange={handleChange}
            required
          >
            <option value="">Select Role</option>
            <option value="1">Admin</option>
            <option value="2">Trainer</option>
            <option value="3">Member</option>
          </select>
        </div>

        {/* Submit button */}
        <button className="btn btn-primary w-100" type="submit">
          Register
        </button>
      </form>
    </div>
  );
}

export default Register;
