import React, { useEffect, useState } from "react";
import axios from "axios";
import { FaUser, FaEnvelope, FaPhone, FaVenusMars, FaBriefcase, FaRupeeSign } from "react-icons/fa";

const TrainerProfile = () => {
 const [trainer, setTrainer] = useState(null);
const [editMode, setEditMode] = useState(false);
const [formData, setFormData] = useState({});
const [error, setError] = useState("");


  useEffect(() => {
    const trainerId = localStorage.getItem("trainerId");
    console.log("Trainer ID:", trainerId);

    if (!trainerId) {
      setError("Trainer ID not found. Please login again.");
      return;
    }

    axios
      .get(`http://localhost:5259/api/trainer/profile/${trainerId}`)
      .then((res) => {
        console.log("Trainer API Response:", res.data);
        setTrainer(res.data);
        localStorage.setItem("tid", res.data.tid);
      })
      .catch((err) => {
        console.error("AXIOS ERROR:", err);
        setError("Unable to load trainer profile");
      });
  }, []);

  if (error) return <div className="alert alert-danger">{error}</div>;
  if (!trainer) return <div className="alert alert-info">Loading profile...</div>;

  return (
  <div className="container mt-4">
    <div className="row justify-content-center">
      <div className="col-lg-9">

        <div className="card shadow-lg border-0 rounded-4 overflow-hidden">

          {/* 🔵 HEADER */}
          <div className="bg-primary text-white p-4 position-relative">
            <span className="badge bg-dark position-absolute top-0 end-0 m-3 fs-6">
              Trainer ID: {trainer.tid}
            </span>

            <h3 className="fw-bold mb-1">
              {trainer.fname} {trainer.lname}
            </h3>
            <p className="mb-0 opacity-75">Certified Gym Trainer</p>
          </div>

          {/* 🔹 BODY */}
          <div className="card-body p-4">
            <div className="row g-4">

              {/* Username */}
              <div className="col-md-6">
                <div className="d-flex align-items-center p-3 bg-light rounded-3">
                  <FaUser className="text-primary fs-3 me-3" />
                  <div>
                    <div className="text-muted small">Username</div>
                    <div className="fw-bold fs-5">{trainer.uname}</div>
                  </div>
                </div>
              </div>

              {/* Email */}
              <div className="col-md-6">
                <div className="d-flex align-items-center p-3 bg-light rounded-3">
                  <FaEnvelope className="text-danger fs-3 me-3" />
                  <div>
                    <div className="text-muted small">Email</div>
                    <div className="fw-bold fs-5">{trainer.email}</div>
                  </div>
                </div>
              </div>

              {/* Contact */}
              <div className="col-md-6">
                <div className="d-flex align-items-center p-3 bg-light rounded-3">
                  <FaPhone className="text-success fs-3 me-3" />
                  <div>
                    <div className="text-muted small">Contact</div>
                    <div className="fw-bold fs-5">{trainer.contact}</div>
                  </div>
                </div>
              </div>

              {/* Gender */}
              <div className="col-md-6">
                <div className="d-flex align-items-center p-3 bg-light rounded-3">
                  <FaVenusMars className="text-warning fs-3 me-3" />
                  <div>
                    <div className="text-muted small">Gender</div>
                    <div className="fw-bold fs-5">{trainer.gender}</div>
                  </div>
                </div>
              </div>

              {/* Experience */}
              <div className="col-md-6">
                <div className="d-flex align-items-center p-3 bg-light rounded-3">
                  <FaBriefcase className="text-info fs-3 me-3" />
                  <div>
                    <div className="text-muted small">Experience</div>
                    <div className="fw-bold fs-5">
                      {trainer.experience} Years
                    </div>
                  </div>
                </div>
              </div>

              {/* Salary */}
              <div className="col-md-6">
                <div className="d-flex align-items-center p-3 bg-light rounded-3">
                  <FaRupeeSign className="text-success fs-3 me-3" />
                  <div>
                    <div className="text-muted small">Salary</div>
                    <div className="fw-bold fs-5 text-success">
                      ₹ {trainer.salary}
                    </div>
                  </div>
                </div>
              </div>

            </div>
          </div>

          {/* 🔻 FOOTER */}
          <div className="card-footer bg-white text-center border-0 pb-3">
            <span className="badge rounded-pill bg-primary fs-6 px-4 py-2">
              Active Trainer
            </span>
          </div>

        </div>

      </div>
    </div>
  </div>
);


};

export default TrainerProfile;
