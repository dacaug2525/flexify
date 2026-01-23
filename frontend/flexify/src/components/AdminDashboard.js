import React from "react";
import { Link, useNavigate } from "react-router-dom";

function AdminDashboard() {
  const navigate = useNavigate();

  return (
    <div className="container mt-4">
      <h2 className="mb-4 text-primary">Admin Dashboard</h2>

      <div className="row g-4">
        {/* Register User */}
        <div className="col-md-4">
          <div className="card shadow-sm text-center">
            <div className="card-body">
              <h5 className="card-title">Register User</h5>
              <p className="card-text">Add Admin, Trainer or Member</p>
              <Link to="/register" className="btn btn-primary btn-sm">
                Register
              </Link>
            </div>
          </div>
        </div>

        {/* Other Admin cards */}
        <div className="col-md-4">
          <div className="card shadow-sm text-center">
            <div className="card-body">
              <h5 className="card-title">Manage Users</h5>
              <p className="card-text">View / Edit / Delete users</p>
              <button className="btn btn-primary btn-sm">Go</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
