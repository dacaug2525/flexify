import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      // Send login credentials to backend
      const response = await axios.post("http://localhost:8080/flexify/login", {
        email,
        password,
      });

      const user = response.data; // User object from backend
      console.log(user);

      // store user data
      localStorage.setItem("user", JSON.stringify(user));

      // navigate user based on role ID
      if (user.role.rid === 1) {
        navigate("/admin-dashboard");
      } else if (user.role.rid === 2) {
        navigate("/trainer-dashboard");
      } else {
        navigate("/member-dashboard");
      }
    } catch (err) {
      setError("Invalid email or password");
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100 bg-light">
      <form
        onSubmit={handleLogin}
        className="p-4 bg-white rounded shadow"
        style={{ width: "100%", maxWidth: "380px" }}
      >
        <h3 className="text-center mb-3">Sign In</h3>

        {error && <div className="alert alert-danger">{error}</div>}

        <div className="form-floating mb-3">
          <input
            type="email"
            className="form-control"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <label>Email</label>
        </div>

        <div className="form-floating mb-3">
          <input
            type="password"
            className="form-control"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <label>Password</label>
        </div>

        <button className="btn btn-primary w-100" type="submit">
          Login
        </button>
      </form>
    </div>
  );
}

export default Login;
