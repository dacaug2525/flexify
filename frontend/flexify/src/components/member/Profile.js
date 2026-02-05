import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

import {
  fetchMemberByUid,
  addMember,
  updateMember,
} from "../redux/member/memberSlice";

const Profile = () => {
  const dispatch = useDispatch();

  /* ================= AUTH + MEMBER ================= */
  const { user } = useSelector((state) => state.auth);
  const { member, loading } = useSelector((state) => state.member);
  const uid = user?.uid;

  /* ================= LOCAL STATE ================= */
  const [healthList, setHealthList] = useState([]);
  const [message, setMessage] = useState(null);
  const [error, setError] = useState(null);

  const [form, setForm] = useState({
    fname: user?.fname || "",
    lname: user?.lname || "",
    email: user?.email || "",
    contact: user?.contact || "",
    gender: user?.gender || "",
    dob: new Date(),
    height: "",
    weight: "",
    address: "",
    medicalInfo: [],
  });

  /* ================= UTILS ================= */
  const toLocalDateTime = (date) => {
    if (!date) return null;
    const yyyy = date.getFullYear();
    const mm = String(date.getMonth() + 1).padStart(2, "0");
    const dd = String(date.getDate()).padStart(2, "0");
    return `${yyyy}-${mm}-${dd}T00:00:00`;
  };

  /* ================= FETCH HEALTH CONDITIONS ================= */
  useEffect(() => {
    fetch("http://localhost:8080/member/health/all")
      .then((res) => res.json())
      .then(setHealthList)
      .catch(() => {});
  }, []);

  /* ================= FETCH MEMBER ================= */
  useEffect(() => {
    if (uid) dispatch(fetchMemberByUid(uid));
  }, [uid, dispatch]);

  /* ================= PREFILL FORM ================= */
  useEffect(() => {
    if (!member) return;

    setForm((prev) => ({
      ...prev,
      dob: member.dob ? new Date(member.dob) : new Date(),
      height: member.height || "",
      weight: member.weight || "",
      address: member.address || "",
      medicalInfo:
        member.healthConditions?.map((h) => ({
          healthId: "",
          remark: h,
        })) || [],
    }));
  }, [member]);

  /* ================= HANDLERS ================= */
  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const addMedical = () =>
    setForm({
      ...form,
      medicalInfo: [...form.medicalInfo, { healthId: "", remark: "" }],
    });

  const handleMedicalChange = (i, field, value) => {
    const updated = [...form.medicalInfo];
    updated[i][field] = value;
    setForm({ ...form, medicalInfo: updated });
  };

  const removeMedical = (index) => {
    const updated = [...form.medicalInfo];
    updated.splice(index, 1);
    setForm({ ...form, medicalInfo: updated });
  };

  /* ================= VALIDATION ================= */
  const validate = () => {
    if (!form.dob) return "Date of birth is required";
    if (!form.height || form.height <= 0) return "Valid height is required";
    if (!form.weight || form.weight <= 0) return "Valid weight is required";
    if (!form.address.trim()) return "Address is required";
    return null;
  };

  /* ================= SUBMIT ================= */
  const handleSubmit = async () => {
    setMessage(null);
    setError(null);

    const validationError = validate();
    if (validationError) {
      setError(validationError);
      return;
    }

    const payload = {
      uid,
      dob: toLocalDateTime(form.dob),
      height: Number(form.height),
      weight: Number(form.weight),
      address: form.address,
      medicalInfo: form.medicalInfo,
    };

    try {
      if (member?.mid) {
        await dispatch(updateMember({ mid: member.mid, payload })).unwrap();
        setMessage("Profile updated successfully ✅");
      } else {
        await dispatch(addMember(payload)).unwrap();
        setMessage("Profile created successfully ✅");
      }
    } catch {
      setError("Operation failed. Please try again.");
    }
  };

  /* ================= UI ================= */
  return (
    <div className="overview-card">
      {/* ===== PAGE HEADING ===== */}
      <h3
        className="mb-4"
        style={{
          color: "#000103",
          fontWeight: 600,
        }}
      >
        👤 {member?.mid ? "Update Profile" : "Create Profile"}
      </h3>

      {/* BASIC INFO */}
      <div className="row g-3 mb-4">
        <Field label="First Name" value={form.fname} />
        <Field label="Last Name" value={form.lname} />
        <Field label="Email" value={form.email} />
        <Field label="Contact" value={form.contact} />
        <Field label="Gender" value={form.gender} />
      </div>

      {/* MEMBER DETAILS */}
      <div className="row g-3 mb-4">
        <div className="col-md-4">
          <label className="form-label text-black">Date of Birth *</label>
          <DatePicker
            selected={form.dob}
            onChange={(date) => setForm({ ...form, dob: date })}
            className="form-control"
            maxDate={new Date()}
            dateFormat="dd/MM/yyyy"
            showMonthDropdown
            showYearDropdown
            dropdownMode="select"
            yearDropdownItemNumber={80}
            scrollableYearDropdown
          />
        </div>

        <Input
          label="Height (cm) *"
          name="height"
          value={form.height}
          onChange={handleChange}
        />

        <Input
          label="Weight (kg) *"
          name="weight"
          value={form.weight}
          onChange={handleChange}
        />

        <Input
          label="Address *"
          name="address"
          value={form.address}
          onChange={handleChange}
          col="12"
        />
      </div>

      {/* MEDICAL CONDITIONS */}
      <h5 className="mb-3" style={{ color: "#111827", fontWeight: 500 }}>
        🩺 Medical Conditions
      </h5>

      {form.medicalInfo.map((m, i) => (
        <div key={i} className="row g-2 mb-2 align-items-end">
          <div className="col-md-5">
            <label className="form-label text-black">Condition</label>
            <select
              className="form-select"
              value={m.healthId}
              onChange={(e) =>
                handleMedicalChange(i, "healthId", e.target.value)
              }
            >
              <option value="">Select Condition</option>
              {healthList.map((h) => (
                <option key={h.healthId} value={h.healthId}>
                  {h.name}
                </option>
              ))}
            </select>
          </div>

          <div className="col-md-5">
            <label className="form-label text-black">Remark</label>
            <input
              className="form-control"
              value={m.remark}
              onChange={(e) => handleMedicalChange(i, "remark", e.target.value)}
            />
          </div>

          <div className="col-md-2 text-end">
            <button
              type="button"
              className="btn btn-outline-danger"
              onClick={() => removeMedical(i)}
            >
              ❌
            </button>
          </div>
        </div>
      ))}

      <button className="btn btn-outline-primary mb-3" onClick={addMedical}>
        ➕ Add Medical Condition
      </button>

      {/* STATUS */}
      {error && <div className="text-danger mb-2">{error}</div>}
      {message && <div className="text-success mb-2">{message}</div>}

      {/* ACTION */}
      <div className="text-end">
        <button
          className="btn btn-primary px-4"
          onClick={handleSubmit}
          disabled={loading}
        >
          {member?.mid ? "Update Profile" : "Create Profile"}
        </button>
      </div>
    </div>
  );
};

/* ================= REUSABLE INPUTS ================= */

const Field = ({ label, value }) => (
  <div className="col-md-4">
    <label className="form-label text-black">{label}</label>
    <input className="form-control" value={value} readOnly />
  </div>
);

const Input = ({ label, name, value, onChange, col = "4" }) => (
  <div className={`col-md-${col}`}>
    <label className="form-label text-black">{label}</label>
    <input
      name={name}
      className="form-control"
      value={value}
      onChange={onChange}
    />
  </div>
);

export default Profile;
