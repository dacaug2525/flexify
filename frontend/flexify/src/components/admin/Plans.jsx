import React, { useEffect, useState } from "react";
import axios from "axios";
import {
  FaPlus, FaEdit, FaTrash, FaEye, FaTimes
} from "react-icons/fa";
import Swal from "sweetalert2";
import "../css/plans.css";

const Plans = () => {
  const [plans, setPlans] = useState([]);
  const [discounts, setDiscounts] = useState([]);
  const [selectedPlan, setSelectedPlan] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [isEdit, setIsEdit] = useState(false);

  const [formData, setFormData] = useState({
    planName: "",
    planDuration: "",
    fees: "",
    description: "",
    disId: ""
  });

  /* ================= FETCH PLANS ================= */
  const fetchPlans = async () => {
    try {
      const res = await axios.get("http://localhost:8081/flexify/admin/plans");
      setPlans(res.data);
    } catch (err) {
      console.error("Error fetching plans:", err);
    }
  };

  /* ================= FETCH DISCOUNTS ================= */
  const fetchDiscounts = async () => {
    try {
      const res = await axios.get(
        "http://localhost:8081/flexify/admin/discount/getAllDiscount"
      );
      setDiscounts(res.data);
    } catch (err) {
      console.error("Error fetching discounts:", err);
    }
  };

  useEffect(() => {
    fetchPlans();
    fetchDiscounts();
  }, []);

  /* ================= CREATE / EDIT ================= */
  const openCreateModal = () => {
    setIsEdit(false);
    setFormData({
      planName: "",
      planDuration: "",
      fees: "",
      description: "",
      disId: ""
    });
    setShowModal(true);
  };

  const openEditModal = (plan) => {
    setIsEdit(true);
    setFormData({
      planName: plan.planName,
      planDuration: plan.planDuration,
      fees: plan.fees,
      description: plan.description,
      disId: plan.discount ? plan.discount.disId.toString() : "",
      planId: plan.planId
    });
    setShowModal(true);
  };

  const submitPlan = async () => {
    try {
      if (!formData.disId) {
        Swal.fire({
          icon: "warning",
          title: "Discount Required",
          text: "Please select a discount before submitting the plan.",
          confirmButtonColor: "#ffc107"
        });
        return;
      }

      const payload = {
        planName: formData.planName,
        planDuration: Number(formData.planDuration),
        fees: Number(formData.fees),
        description: formData.description,
        disId: Number(formData.disId)
      };

      if (isEdit) {
        await axios.put(
          `http://localhost:8081/flexify/admin/plans/${formData.planId}`,
          payload
        );

        Swal.fire({
          icon: "success",
          title: "Plan Updated",
          text: "The membership plan has been updated successfully.",
          confirmButtonColor: "#0d6efd"
        });
      } else {
        await axios.post(
          "http://localhost:8081/flexify/admin/plans/create",
          payload
        );

        Swal.fire({
          icon: "success",
          title: "Plan Created",
          text: "The membership plan has been created successfully.",
          confirmButtonColor: "#0d6efd"
        });
      }

      setShowModal(false);
      fetchPlans();
    } catch (err) {
      Swal.fire({
        icon: "error",
        title: "Action Failed",
        text: err.response?.data?.message || "Something went wrong. Please try again.",
        confirmButtonColor: "#dc3545"
      });
    }
  };

  /* ================= DELETE ================= */
  const deletePlan = async (planId) => {
    try {
      await axios.delete(
        `http://localhost:8081/flexify/admin/plans/delete/${planId}`
      );

      Swal.fire({
        icon: "success",
        title: "Plan Deleted",
        text: "The plan has been deleted successfully.",
        confirmButtonColor: "#dc3545"
      });

      fetchPlans();
    } catch (err) {
      Swal.fire({
        icon: "error",
        title: "Delete Failed",
        text:
          err.response?.data?.message ||
          "Cannot delete this plan because it is linked to trainings.",
        confirmButtonColor: "#dc3545"
      });
    }
  };

  return (
    <div className="container-fluid p-4" style={{ minHeight: "100vh", background: "#f8f9fa" }}>
      {/* HEADER */}
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2 className="fw-bold">Membership Plans</h2>
        <button className="btn btn-primary d-flex align-items-center" onClick={openCreateModal}>
          <FaPlus className="me-2" /> Create Plan
        </button>
      </div>

      {/* PLANS TABLE */}
      <div className="card shadow-sm">
        <div className="card-body">
          <table className="table table-hover align-middle">
            <thead className="table-light">
              <tr>
                <th>ID</th>
                <th>Plan Name</th>
                <th>Duration</th>
                <th>Fees</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {plans.length === 0 ? (
                <tr>
                  <td colSpan="5" className="text-center py-4 text-muted">
                    No plans available
                  </td>
                </tr>
              ) : (
                plans.map((plan) => (
                  <tr key={plan.planId}>
                    <td>{plan.planId}</td>
                    <td>{plan.planName}</td>
                    <td>{plan.planDuration} Months</td>
                    <td>₹{plan.fees}</td>
                    <td>
                      <button className="btn btn-sm btn-outline-primary me-2" onClick={() => setSelectedPlan(plan)}>
                        <FaEye className="me-1" /> View
                      </button>
                      <button className="btn btn-sm btn-outline-warning me-2" onClick={() => openEditModal(plan)}>
                        <FaEdit className="me-1" /> Edit
                      </button>
                      <button className="btn btn-sm btn-outline-danger" onClick={() => deletePlan(plan.planId)}>
                        <FaTrash className="me-1" /> Delete
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>

      {/* SIDE VIEW */}
      {selectedPlan && (
        <div style={{
          position: "fixed",
          right: 30,
          top: 100,
          width: 300,
          background: "#fff",
          borderRadius: 10,
          padding: 20,
          boxShadow: "0 8px 20px rgba(0,0,0,0.2)",
          zIndex: 100
        }}>
          <div className="d-flex justify-content-between align-items-center mb-3">
            <h5>{selectedPlan.planName}</h5>
            <FaTimes style={{ cursor: "pointer" }} onClick={() => setSelectedPlan(null)} />
          </div>
          <p><b>Duration:</b> {selectedPlan.planDuration} Months</p>
          <p><b>Fees:</b> ₹{selectedPlan.fees}</p>
          <p><b>Description:</b> {selectedPlan.description || "N/A"}</p>
          <p><b>Discount:</b> {selectedPlan.discount?.discount || "N/A"}%</p>
        </div>
      )}

      {/* MODAL */}
      {showModal && (
        <div style={{
          position: "fixed",
          inset: 0,
          background: "rgba(0,0,0,0.5)",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          zIndex: 2000
        }}>
          <div style={{
            width: 450,
            background: "#fff",
            borderRadius: 10,
            padding: 25,
            boxShadow: "0 8px 20px rgba(0,0,0,0.25)"
          }}>
            <div className="d-flex justify-content-between align-items-center mb-3">
              <h5>{isEdit ? "Edit Plan" : "Create Plan"}</h5>
              <FaTimes style={{ cursor: "pointer" }} onClick={() => setShowModal(false)} />
            </div>

            <div className="mb-3">
              <label className="form-label">Plan Name</label>
              <input className="form-control" value={formData.planName}
                onChange={(e) => setFormData({ ...formData, planName: e.target.value })} />
            </div>

            <div className="mb-3">
              <label className="form-label">Duration (Months)</label>
              <input type="number" className="form-control" value={formData.planDuration}
                onChange={(e) => setFormData({ ...formData, planDuration: e.target.value })} />
            </div>

            <div className="mb-3">
              <label className="form-label">Fees (₹)</label>
              <input type="number" className="form-control" value={formData.fees}
                onChange={(e) => setFormData({ ...formData, fees: e.target.value })} />
            </div>

            <div className="mb-3">
              <label className="form-label">Description</label>
              <textarea className="form-control" rows="3" value={formData.description}
                onChange={(e) => setFormData({ ...formData, description: e.target.value })} />
            </div>

            <div className="mb-3">
              <label className="form-label">Discount</label>
              <select className="form-select" value={formData.disId}
                onChange={(e) => setFormData({ ...formData, disId: e.target.value })}>
                <option value="">Select Discount</option>
                {discounts.map((d) => (
                  <option key={d.disId} value={d.disId.toString()}>
                    {d.discount}% off for {d.duration} months
                  </option>
                ))}
              </select>
            </div>

            <div className="text-end">
              <button className="btn btn-secondary me-2" onClick={() => setShowModal(false)}>Cancel</button>
              <button className="btn btn-primary" onClick={submitPlan}>
                {isEdit ? "Update" : "Create"}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Plans;
