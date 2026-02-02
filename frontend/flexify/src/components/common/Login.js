import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { loginSuccess } from "../redux/authSlice";
import "./Login.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/flexify/login", {
        email,
        password,
      });

      const user = response.data;

      dispatch(
        loginSuccess({
          user,
          role: user.role.rid,
        }),
      );

      localStorage.setItem("user", JSON.stringify(user));

      if (user.role.rid === 1) navigate("/admin");
      else if (user.role.rid === 2) navigate("/trainer/trainer-dashboard");
      else navigate("/member/member-dashboard");
    } catch {
      setError("Invalid email or password");
    }
  };

  return (
    <div className="login-page">
      {/* LEFT : INFO */}
      <div className="login-left-info">
        <p className="tagline">
          Your all-in-one gym management & workout tracking platform.
        </p>

        <ul className="features">
          <li>🏋️ Smart workout & training plans</li>
          <li>📊 Track BMI & performance</li>
          <li>💳 Membership & payments</li>
          <li>📅 Attendance & renewals</li>
          <li>⭐ Feedback & ratings</li>
        </ul>

        <p className="footer-text">
          Built for members, trainers, and gym owners.
        </p>
      </div>

      {/* RIGHT : LOGIN FORM */}
      <div className="login-right">
        <form className="login-card" onSubmit={handleLogin}>
          <h3>Login to Flexify</h3>

          {error && <div className="login-error">{error}</div>}

          <input
            type="email"
            placeholder="Email address"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          <button type="submit">Login</button>
        </form>
      </div>
    </div>
  );
}

export default Login;
