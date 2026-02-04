import { useEffect, useState } from "react";
import axios from "axios";
import Register from "../common/Register";
import { FaDumbbell } from "react-icons/fa"; 

const Trainers = () => {
  const [trainers, setTrainers] = useState([]);
  const [showRegister, setShowRegister] = useState(false);
  const [selected, setSelected] = useState(null);
  const [loadingDetails, setLoadingDetails] = useState(false);
  const [salary, setSalary] = useState(""); // for updating salary
  const [updating, setUpdating] = useState(false);
  const [successMsg, setSuccessMsg] = useState(""); // success message

  useEffect(() => {
    fetchTrainers();
  }, []);

  const fetchTrainers = async () => {
    try {
      const res = await axios.get(
        "http://localhost:8081/flexify/admin/alltrainers/2"
      );
      setTrainers(Array.isArray(res.data) ? res.data : []);
    } catch (err) {
      console.error("Error fetching trainers:", err);
      setTrainers([]);
    }
  };

  const viewDetails = async (uid) => {
    if (!uid) return console.error("UID missing for trainer");

    try {
      setShowRegister(false);
      setLoadingDetails(true);
      const res = await axios.get(
        `http://localhost:8081/flexify/admin/trainers/details/${uid}`
      );
      setSelected(res.data);
      setSalary(res.data.salary); // initialize salary field
      setSuccessMsg(""); // clear any previous messages
    } catch (err) {
      console.error("Error fetching trainer details", err);
    } finally {
      setLoadingDetails(false);
    }
  };

  const updateSalary = async () => {
    if (!salary) return;
    try {
      setUpdating(true);
      await axios.put(
        `http://localhost:8081/flexify/admin/trainers/update-salary/${selected.tid}`,
        { salary: Number(salary) }
      );
      setSuccessMsg("Salary updated successfully!");
      viewDetails(selected.uid); // refresh details

      // Hide message after 3 seconds
      setTimeout(() => setSuccessMsg(""), 3000);
    } catch (err) {
      console.error("Error updating salary", err);
      setSuccessMsg("Failed to update salary");
      setTimeout(() => setSuccessMsg(""), 3000);
    } finally {
      setUpdating(false);
    }
  };

  return (
    <div className="container-fluid">
      {/* HEADER */}
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h3
            className="fw-bold mb-0"
            style={{ color: "#12161d", display: "flex", alignItems: "center", gap: "0.5rem" }}
          >
            <FaDumbbell size={24} /> Trainer Management
          </h3>

          {!showRegister && !selected && (
            <button
              className="btn btn-primary px-4 shadow-sm"
              onClick={() => setShowRegister(true)}
            >
              + Add Trainer
            </button>
          )}
        </div>

      {/* REGISTER */}
      {showRegister && (
        <>
          <button
            className="btn btn-outline-secondary mb-3"
            onClick={() => setShowRegister(false)}
          >
            ← Back to Trainers
          </button>
          <Register />
        </>
      )}

      {/* TRAINER LIST */}
      {!showRegister && !selected && (
        <div className="card shadow-sm border-0 animate-fade">
          <div className="table-responsive">
            <table className="table table-hover align-middle mb-0">
              <thead className="table-dark">
                <tr>
                  <th>Username</th>
                  <th>Name</th>
                  <th>Contact</th>
                  <th className="text-center">Action</th>
                </tr>
              </thead>
              <tbody>
                {trainers.map((t) => (
                  <tr key={t.uid}>
                    <td className="fw-semibold">{t.uname}</td>
                    <td>{t.fname} {t.lname}</td>
                    <td>{t.contact}</td>
                    <td className="text-center">
                      <button
                        className="btn btn-sm btn-outline-primary"
                        onClick={() => viewDetails(t.uid)}
                      >
                        View Details
                      </button>
                    </td>
                  </tr>
                ))}
                {trainers.length === 0 && (
                  <tr>
                    <td colSpan="4" className="text-center text-muted py-4">
                      No trainers found
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      )}

      {/* TRAINER DETAILS */}
      {selected && (
        <div className="animate-slide">
          <button
            className="btn btn-outline-secondary mb-3"
            onClick={() => setSelected(null)}
          >
            ← Back to Trainers
          </button>

          {loadingDetails ? (
            <div className="text-center py-5 text-muted">
              Loading trainer details...
            </div>
          ) : (
            <div className="card shadow-lg border-0">
              <div className="card-header bg-dark text-white fw-bold">
                Trainer Profile
              </div>
              <div className="card-body row g-4">
                {/* Basic Info */}
                <div className="col-md-6">
                  <h6 className="text-muted">Basic Info</h6>
                  <p><b>Name:</b> {selected.fname} {selected.lname}</p>
                  <p><b>Email:</b> {selected.email}</p>
                  <p><b>Contact:</b> {selected.contact}</p>
                  <p><b>Gender:</b> {selected.gender}</p>
                </div>

                {/* Professional */}
                <div className="col-md-6">
                  <h6 className="text-muted">Professional</h6>
                  <p><b>Trainer ID:</b> {selected.tid}</p>
                  <p>
                    <b>Experience:</b>{" "}
                    <span className="badge bg-success">
                      {selected.experience} yrs
                    </span>
                  </p>
                  <p>
                    <b>Salary:</b>{" "}
                    <input
                      type="number"
                      value={salary}
                      onChange={(e) => setSalary(e.target.value)}
                      className="form-control form-control-sm d-inline w-auto"
                    />{" "}
                    <button
                      className="btn btn-sm btn-primary ms-2"
                      onClick={updateSalary}
                      disabled={updating}
                    >
                      {updating ? "Updating..." : "Update"}
                    </button>
                  </p>
                  {/* SUCCESS MESSAGE */}
                  {successMsg && (
                    <div className="text-success small mt-2">{successMsg}</div>
                  )}
                </div>

                {/* Specializations */}
                <div className="col-12">
                  <h6 className="text-muted">Specializations</h6>
                  {selected.specializations?.length ? (
                    selected.specializations.map((s, i) => (
                      <div key={i} className="border rounded p-2 mb-2 bg-light">
                        <b>{s.trainingName}</b>
                        <div className="text-muted small">{s.description}</div>
                      </div>
                    ))
                  ) : (
                    <p className="text-muted">No specialization assigned</p>
                  )}
                </div>
              </div>
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default Trainers;
