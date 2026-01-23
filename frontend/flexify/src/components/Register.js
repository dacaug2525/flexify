import React from "react";

function Register() {
  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100 bg-light">
      <form
        className="p-4 bg-white rounded shadow"
        style={{ width: "100%", maxWidth: "450px" }}
      >
        <h2 className="mb-4 text-center">Create Account</h2>

        {/* First Name */}
        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            id="fname"
            placeholder="First Name"
          />
          <label htmlFor="fname">First Name</label>
        </div>

        {/* Last Name */}
        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            id="lname"
            placeholder="Last Name"
          />
          <label htmlFor="lname">Last Name</label>
        </div>
        {/* Username */}
        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            id="uname"
            placeholder="Username"
          />
          <label htmlFor="uname">Username</label>
        </div>

        {/* Password */}
        <div className="form-floating mb-3">
          <input
            type="password"
            className="form-control"
            id="password"
            placeholder="Password"
          />
          <label htmlFor="password">Password</label>
        </div>

        {/* Email */}
        <div className="form-floating mb-3">
          <input
            type="email"
            className="form-control"
            id="email"
            placeholder="Email"
          />
          <label htmlFor="email">Email</label>
        </div>

        {/* Contact */}
        <div className="form-floating mb-3">
          <input
            type="tel"
            className="form-control"
            id="contact"
            placeholder="Contact Number"
          />
          <label htmlFor="contact">Contact Number</label>
        </div>

        {/* Gender */}
        <div className="mb-3">
          <label className="form-label fw-semibold">Gender</label>
          <select className="form-select">
            <option value="">Select Gender</option>
            <option>Male</option>
            <option>Female</option>
            <option>Other</option>
          </select>
        </div>

        {/* Role */}
        <div className="mb-4">
          <label className="form-label fw-semibold">Role</label>
          <select className="form-select">
            <option value="">Select Role</option>
            <option>Admin</option>
            <option>Member</option>
            <option>Trainer</option>
          </select>
        </div>

        <button className="btn btn-primary w-100 py-2" type="submit">
          Register
        </button>

        <p className="mt-3 mb-0 text-center text-body-secondary">
          Already have an account? <a href="/login">Sign in</a>
        </p>
      </form>
    </div>
  );
}

export default Register;
