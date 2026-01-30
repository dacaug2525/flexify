import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { loginSuccess } from "../redux/authSlice";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const dispatch = useDispatch();   // 🔹 Redux
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    
    try {
      const response = await axios.post(
        "http://localhost:8080/flexify/login",
        { email, password }
      );

      const user = response.data;

      // 🔹 Save to Redux
      dispatch(
        loginSuccess({
          user: user,
          role: user.role.rid, // role id
        })
      );

      // optional: still keep localStorage if needed
      localStorage.setItem("user", JSON.stringify(user));

      // 🔹 Role-based navigation
      if (user.role.rid === 1) {
        navigate("/admin");
      } else if (user.role.rid === 2) {
        navigate("/trainer/trainer-dashboard");
      } else {
        navigate("/member/member-dashboard");
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
        <h3 className="text-center mb-3">Login</h3>

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
