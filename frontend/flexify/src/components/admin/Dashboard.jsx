import React from "react";

const AdminDashboard = () => {
  return (
    <>
      <h2 className="mb-4">Dashboard</h2>

      <div className="row g-4">
        <div className="col-md-4">
          <div className="card text-white bg-primary shadow">
            <div className="card-body">
              <h5>Total Members</h5>
              <h2>120</h2>
            </div>
          </div>
        </div>

        <div className="col-md-4">
          <div className="card text-white bg-success shadow">
            <div className="card-body">
              <h5>Total Trainers</h5>
              <h2>15</h2>
            </div>
          </div>
        </div>

        <div className="col-md-4">
          <div className="card text-white bg-warning shadow">
            <div className="card-body">
              <h5>Monthly Revenue</h5>
              <h2>₹75,000</h2>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default AdminDashboard;
