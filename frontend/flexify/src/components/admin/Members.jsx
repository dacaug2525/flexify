import { useEffect, useState } from "react";
import axios from "axios";
import Register from "../common/Register";

const Members = () => {
  const [members, setMembers] = useState([]);
  const [showRegister, setShowRegister] = useState(false);
  const [selected, setSelected] = useState(null);

  /* ================= FETCH MEMBERS LIST ================= */
  useEffect(() => {
    fetchMembers();
  }, []);

  const fetchMembers = async () => {
    try {
      const res = await axios.get(
        "http://localhost:8081/flexify/admin/members/list"
      );
      setMembers(res.data);   // ← all 5 members will come here
    } catch (err) {
      console.error("Error fetching members", err);
    }
  };

  /* ================= FETCH MEMBER DETAILS ================= */
  const viewDetails = async (uid) => {
    try {
      const res = await axios.get(
        `http://localhost:8081/flexify/admin/members/details/${uid}`
      );
      setSelected(res.data);
    } catch (err) {
      console.error("Error fetching member details", err);
    }
  };

  return (
    <>
      {/* ================= HEADER ================= */}
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h3 className="fw-bold">Members</h3>

        {!showRegister && !selected && (
          <button
            className="btn btn-primary shadow-sm"
            onClick={() => setShowRegister(true)}
          >
            + Add Member
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
            ← Back to Members
          </button>
          <Register />
        </>
      )}

      {/* ================= MEMBERS TABLE ================= */}
      {!showRegister && !selected && (
        <div className="card shadow-sm">
          <div className="card-body p-0">
            <table className="table table-hover align-middle mb-0">
              <thead className="table-dark">
                <tr>
                  <th>Username</th>
                  <th>Name</th>
                  <th>Contact</th>
                  <th>Join Date</th>
                  <th>Status</th>
                  <th className="text-center">Action</th>
                </tr>
              </thead>

              <tbody>
                {members.map((m) => (
                  <tr key={m.uid}>
                    <td className="fw-semibold">{m.uname}</td>
                    <td>{m.fname} {m.lname}</td>
                    <td>{m.contact}</td>
                    <td>{m.joinDate?.split("T")[0]}</td>
                    <td>
                      <span
                        className={`badge rounded-pill ${
                          m.status === "active"
                            ? "bg-success"
                            : "bg-secondary"
                        }`}
                      >
                        {m.status}
                      </span>
                    </td>
                    <td className="text-center">
                      <button
                        className="btn btn-sm btn-outline-primary"
                        onClick={() => viewDetails(m.uid)}
                      >
                        View Details
                      </button>
                    </td>
                  </tr>
                ))}

                {members.length === 0 && (
                  <tr>
                    <td colSpan="6" className="text-center text-muted py-4">
                      No members available
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      )}

      {/* ================= MEMBER DETAILS ================= */}
      {selected && (
        <>
          <button
            className="btn btn-outline-secondary mb-3"
            onClick={() => setSelected(null)}
          >
            ← Back to Members
          </button>

          <div className="card shadow-lg border-0">
            <div className="card-header bg-dark text-white fw-bold">
              Member Details
            </div>

            <div className="card-body row g-4">
              <div className="col-md-6"><b>Member ID:</b> {selected.mid}</div>
              <div className="col-md-6"><b>Email:</b> {selected.email}</div>
              <div className="col-md-6"><b>Gender:</b> {selected.gender}</div>
              <div className="col-md-6"><b>DOB:</b> {selected.dob?.split("T")[0]}</div>
              <div className="col-md-6"><b>Height:</b> {selected.height} cm</div>
              <div className="col-md-6"><b>Weight:</b> {selected.weight} kg</div>
              <div className="col-md-12"><b>Address:</b> {selected.address}</div>
            </div>
          </div>
        </>
      )}
    </>
  );
};

export default Members;
