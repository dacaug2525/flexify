import React, { useState } from "react";

// send HTTP requests to backend
import axios from "axios";

function Register() {
   // which form to show: trainer | member | null
  const [userType, setUserType] = useState(null);

  // common state for both forms
  const [user, setUser] = useState({
    fname: "",
    lname: "",
    uname: "",
    password: "",
    email: "",
    contact: "",
    gender: "",
    roleid: "",
  });

  // handle input change
  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  // submit form
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await axios.post("http://localhost:8080/flexify/register", {
        ...user,
        roleid: parseInt(user.roleid),
      });

      alert(
        userType === "trainer"
          ? "Trainer registered successfully"
          : "Member registered successfully"
      );

      // reset form
      setUser({
        fname: "",
        lname: "",
        uname: "",
        password: "",
        email: "",
        contact: "",
        gender: "",
        roleid: "",
      });
      setUserType(null);
    } catch (error) {
      console.error(error);
      alert("Registration failed");
    }
  };

  // when admin clicks buttons
  const openTrainerForm = () => {
    setUserType("trainer");
    setUser({ ...user, roleid: "2" });
  };

  const openMemberForm = () => {
    setUserType("member");
    setUser({ ...user, roleid: "3" });
  };

  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100 bg-light">
      <div
        className="p-4 bg-white rounded shadow"
        style={{ width: "100%", maxWidth: "450px" }}
      >
        {/* BUTTONS */}
        {!userType && (
          <>
            <h3 className="text-center mb-4">Admin Panel</h3>

            <button
              className="btn btn-success w-100 mb-3"
              onClick={openTrainerForm}
            >
              ➕ Register Trainer
            </button>

            <button
              className="btn btn-primary w-100"
              onClick={openMemberForm}
            >
              ➕ Register Member
            </button>
          </>
        )}

        {/* FORM */}
        {userType && (
          <form onSubmit={handleSubmit}>
            <h3 className="text-center mb-4">
              {userType === "trainer"
                ? "Trainer Registration"
                : "Member Registration"}
            </h3>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                name="fname"
                placeholder="First Name"
                value={user.fname}
                onChange={handleChange}
                required
              />
              <label>First Name</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                name="lname"
                placeholder="Last Name"
                value={user.lname}
                onChange={handleChange}
                required
              />
              <label>Last Name</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                name="uname"
                placeholder="Username"
                value={user.uname}
                onChange={handleChange}
                required
              />
              <label>Username</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="password"
                className="form-control"
                name="password"
                placeholder="Password"
                value={user.password}
                onChange={handleChange}
                required
              />
              <label>Password</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="email"
                className="form-control"
                name="email"
                placeholder="Email"
                value={user.email}
                onChange={handleChange}
                required
              />
              <label>Email</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="tel"
                className="form-control"
                name="contact"
                placeholder="Contact"
                value={user.contact}
                onChange={handleChange}
                required
              />
              <label>Contact</label>
            </div>

            <div className="mb-3">
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

            {/* ROLE SHOWN BUT DISABLED */}
            <div className="mb-3">
              <label className="form-label fw-semibold">Role</label>
              <input
                type="text"
                className="form-control"
                value={userType === "trainer" ? "Trainer" : "Member"}
                disabled
              />
            </div>

            <button className="btn btn-primary w-100 mb-2" type="submit">
              Register
            </button>

            <button
              type="button"
              className="btn btn-secondary w-100"
              onClick={() => setUserType(null)}
            >
              Back
            </button>
          </form>
        )}
      </div>
    </div>
  );
}


export default Register;
