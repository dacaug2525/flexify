import React, { useEffect, useState } from "react";
import axios from "axios";
import { FaMoneyBillWave, FaSearch } from "react-icons/fa";

const Payments = () => {
  const [payments, setPayments] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get("http://localhost:8081/flexify/admin/payments")
      .then((res) => {
        setPayments(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching payments", err);
        setLoading(false);
      });
  }, []);

  const filteredPayments = payments.filter(
    (p) =>
      p.transactionId.toLowerCase().includes(search.toLowerCase()) ||
      p.paymentMethod.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>💳 Payment Records</h2>

      {/* Search */}
      <div style={styles.searchBox}>
        <FaSearch style={{ marginRight: 8 }} />
        <input
          type="text"
          placeholder="Search by Transaction ID or Method"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          style={styles.searchInput}
        />
      </div>

      {loading ? (
        <p>Loading payments...</p>
      ) : (
        <div style={styles.grid}>
          {filteredPayments.map((p) => (
            <div key={p.paymentId} style={styles.card}>
              <div style={styles.icon}>
                <FaMoneyBillWave size={28} color="#2ecc71" />
              </div>

              <div>
                <p><b>Transaction:</b> {p.transactionId}</p>
                <p><b>Member ID:</b> {p.mid}</p>
                <p><b>Amount:</b> ₹{p.amount}</p>
                <p><b>Method:</b> {p.paymentMethod}</p>
                <p><b>Date:</b> {new Date(p.paymentDate).toLocaleString()}</p>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

/* ================== STYLES ================== */

const styles = {
  container: {
    padding: "25px",
    fontFamily: "Segoe UI, sans-serif",
    background: "#f4f6f8",
    minHeight: "100vh",
  },
  heading: {
    marginBottom: "20px",
    color: "#333",
  },
  searchBox: {
    display: "flex",
    alignItems: "center",
    background: "#fff",
    padding: "10px",
    borderRadius: "8px",
    width: "350px",
    boxShadow: "0 2px 6px rgba(0,0,0,0.1)",
    marginBottom: "20px",
  },
  searchInput: {
    border: "none",
    outline: "none",
    width: "100%",
    fontSize: "14px",
  },
  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(280px, 1fr))",
    gap: "20px",
  },
  card: {
    display: "flex",
    gap: "15px",
    background: "#ffffff",
    padding: "18px",
    borderRadius: "12px",
    boxShadow: "0 4px 10px rgba(0,0,0,0.1)",
    transition: "transform 0.2s ease",
  },
  icon: {
    display: "flex",
    alignItems: "center",
  },
};

export default Payments;
