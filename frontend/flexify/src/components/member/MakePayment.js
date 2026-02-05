import React, { useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";

const MakePayment = () => {
  const { member } = useSelector((state) => state.member);

  const planName = localStorage.getItem("selectedPlanName") || "";
  const planId = localStorage.getItem("selectedPlanId") || "";
  const amount = localStorage.getItem("payAmount") || "";

  const [paymentMethod, setPaymentMethod] = useState("CARD");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const [payments, setPayments] = useState([]);

  /* ================= FETCH TRANSACTION HISTORY ================= */
  const fetchPaymentHistory = async () => {
    if (!member?.mid) return;

    try {
      const res = await axios.get(
        `http://localhost:8080/member/payment/${member.mid}`,
      );
      setPayments(res.data);
    } catch {
      setError("Unable to load transaction history");
    }
  };

  /* ================= MAKE PAYMENT ================= */
  const handlePayment = async (e) => {
    e.preventDefault();
    setError("");
    setMessage("");

    try {
      const res = await axios.post(
        "http://localhost:8080/member/payment/pay",
        {
          memberId: member.mid,
          planId,
          amount,
          paymentDate: new Date().toISOString(),
          paymentMethod,
        },
      );

      setMessage(`Payment successful • TXN: ${res.data.transactionId}`);

      localStorage.removeItem("selectedPlanId");
      localStorage.removeItem("selectedPlanName");
      localStorage.removeItem("payAmount");

      fetchPaymentHistory();
    } catch {
      setError("Payment failed. Please try again.");
    }
  };

  return (
    <div
      style={{
        display: "grid",
        gridTemplateColumns: "1fr 1.2fr",
        gap: "30px",
      }}
    >
      {/* ================= PAYMENT FORM ================= */}
      <div className="white-card">
        <h4 className="card-heading">💳 Make Payment</h4>

        {error && <div className="alert alert-danger py-2">{error}</div>}
        {message && <div className="alert alert-success py-2">{message}</div>}

        <form onSubmit={handlePayment}>
          <div className="mb-3">
            <label className="form-label field-label">Plan</label>
            <input className="form-control" value={planName} readOnly />
          </div>

          <div className="mb-3">
            <label className="form-label field-label">Amount (₹)</label>
            <input className="form-control" value={amount} readOnly />
          </div>

          <div className="mb-4">
            <label className="form-label field-label">Payment Method</label>
            <select
              className="form-control"
              value={paymentMethod}
              onChange={(e) => setPaymentMethod(e.target.value)}
            >
              <option value="CARD">Card</option>
              <option value="UPI">UPI</option>
              <option value="CASH">Cash</option>
            </select>
          </div>

          <button type="submit" className="btn btn-success w-100">
            Pay Now
          </button>
        </form>
      </div>

      {/* ================= TRANSACTION HISTORY ================= */}
      <div className="white-card">
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            marginBottom: "16px",
          }}
        >
          <h4 className="card-heading">📜 Transaction History</h4>
          <button
            className="btn btn-outline-primary btn-sm"
            onClick={fetchPaymentHistory}
          >
            Load
          </button>
        </div>

        {payments.length === 0 && (
          <p style={{ color: "#6b7280" }}>No transactions found</p>
        )}

        <div style={{ maxHeight: "420px", overflowY: "auto" }}>
          {payments.map((p) => (
            <div key={p.paymentId} className="history-item">
              <strong className="amount">₹{p.amount}</strong> •{" "}
              {p.paymentMethod}
              <div className="muted">
                {new Date(p.paymentDate).toLocaleString("en-IN")}
              </div>
              <div className="txn">TXN: {p.transactionId}</div>
            </div>
          ))}
        </div>
      </div>

      {/* ================= STYLES ================= */}
      <style>{`
        .white-card {
          background: #ffffff;
          padding: 26px;
          border-radius: 18px;
          box-shadow: 0 8px 24px rgba(0,0,0,0.08);
        }

        .card-heading {
          color:#000103;
          font-weight: 600;
          margin-bottom: 20px;
        }

        .field-label {
          color: #000;
          font-weight: 500;
        }

        .history-item {
          padding: 14px;
          margin-bottom: 12px;
          border-radius: 12px;
          background: #f9fafb;
          box-shadow: inset 0 0 0 1px #e5e7eb;
        }

        .amount {
          color: #16a34a;
          font-weight: 600;
        }

        .txn {
          font-size: 0.8rem;
          color: #2563eb;
        }

        .muted {
          font-size: 0.8rem;
          color: #6b7280;
        }
      `}</style>
    </div>
  );
};

export default MakePayment;
