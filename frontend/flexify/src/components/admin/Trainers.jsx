import { useEffect, useState } from "react";
import axios from "axios";
import Register from "../common/Register";

const Trainers = () => {
  const [trainers, setTrainers] = useState([]);
  const [showRegister, setShowRegister] = useState(false);
  const [selected, setSelected] = useState(null);
  const [loadingDetails, setLoadingDetails] = useState(false);

  useEffect(() => {
    fetchTrainers();
  }, []);

  const fetchTrainers = async () => {
    try {
      const res = await axios.get(
        "http://localhost:8081/flexify/admin/trainers/list"
      );
      setTrainers(Array.isArray(res.data) ? res.data : []);
    } catch (err) {
      console.error("Error fetching trainers:", err);
      setTrainers([]);
    }
  };

      const viewDetails = async (uid) => {
      if (!uid) {
        console.error("UID missing for trainer");
        return;
      }

      try {
        setShowRegister(false);
        setLoadingDetails(true);
        const res = await axios.get(
          `http://localhost:8081/flexify/admin/trainers/details/${uid}`
        );
        setSelected(res.data);
      } catch (err) {
        console.error("Error fetching trainer details", err);
      } finally {
        setLoadingDetails(false);
      }
    };

  return (
    <div className="container-fluid">

      {/* ================= HEADER ================= */}
      <div className="d-flex justify-content-between align-items-center mb-4">
        <div>
          <h3 className="fw-bold mb-0">Trainer Management</h3>
        </div>

        {!showRegister && !selected && (
          <button
            className="btn btn-primary px-4 shadow-sm"
            onClick={() => setShowRegister(true)}
          >
            + Add Trainer
          </button>
        )}
      </div>

      {/* ================= REGISTER ================= */}
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

      {/* ================= TRAINER LIST ================= */}
      {!showRegister && !selected && (
        <div className="card shadow-sm border-0 animate-fade">
          <div className="card-header bg-light fw-semibold">
            Trainer List
          </div>

          <div className="table-responsive">
            <table className="table table-hover align-middle mb-0">
              <thead className="table-dark">
                <tr>
                  <th>Username</th>
                  <th>Name</th>
                  <th>Contact</th>
                  <th>Experience</th>
                  <th>Specialization</th>
                  <th className="text-center">Action</th>
                </tr>
              </thead>

              <tbody>
                {trainers.map((t) => (
                  <tr key={t.uid}>
                    <td className="fw-semibold">{t.uname}</td>
                    <td>{t.fname} {t.lname}</td>
                    <td>{t.contact}</td>
                    <td>
                      <span className="badge bg-secondary">
                        {t.experience ?? 0} yrs
                      </span>
                    </td>
                    <td>
                      {t.specializations?.length
                        ? t.specializations.map((s, i) => (
                            <span
                              key={i}
                              className="badge bg-info text-dark me-1"
                            >
                              {s.trainingName}
                            </span>
                          ))
                        : "—"}
                    </td>
                    <td className="text-center">
                      <button
                        className="btn btn-sm btn-outline-primary"
                        onClick={() => viewDetails(t.uid)}
                      >
                        View
                      </button>
                    </td>
                  </tr>
                ))}

                {trainers.length === 0 && (
                  <tr>
                    <td colSpan="6" className="text-center text-muted py-4">
                      No trainers found
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      )}

      {/* ================= TRAINER DETAILS ================= */}
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
                <div className="col-md-6">
                  <h6 className="text-muted">Basic Info</h6>
                  <p><b>Name:</b> {selected.fname} {selected.lname}</p>
                  <p><b>Email:</b> {selected.email}</p>
                  <p><b>Contact:</b> {selected.contact}</p>
                  <p><b>Gender:</b> {selected.gender}</p>
                </div>

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
                    <span className="badge bg-warning text-dark">
                      ₹ {selected.salary}
                    </span>
                  </p>
                </div>

                <div className="col-12">
                  <h6 className="text-muted">Specializations</h6>
                  {selected.specializations?.length ? (
                    selected.specializations.map((s, i) => (
                      <div key={i} className="border rounded p-2 mb-2 bg-light">
                        <b>{s.trainingName}</b>
                        <div className="text-muted small">
                          {s.description}
                        </div>
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
