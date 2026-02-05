import React, { useEffect, useState } from "react";
import axios from "axios";
import { FaStar, FaSearch, FaCommentDots } from "react-icons/fa";

const Feedbacks = () => {
  const [feedbacks, setFeedbacks] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get("http://localhost:8080/admin/feedbacks/allfeedbacks")
      .then((res) => {
        setFeedbacks(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching feedback", err);
        setLoading(false);
      });
  }, []);

  const filteredFeedbacks = feedbacks.filter(
    (f) =>
      f.comment?.toLowerCase().includes(search.toLowerCase()) ||
      f.rating.toString().includes(search)
  );

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>📝 User Feedback</h2>

      {/* Search */}
      <div style={styles.searchBox}>
        <FaSearch />
        <input
          type="text"
          placeholder="Search by comment or rating"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          style={styles.searchInput}
        />
      </div>

      {loading ? (
        <p>Loading feedback...</p>
      ) : (
        <div style={styles.grid}>
          {filteredFeedbacks.map((f) => (
            <div key={f.feedbackId} style={styles.card}>
              <div style={styles.icon}>
                <FaCommentDots size={28} color="#3498db" />
              </div>

              <div style={{ width: "100%" }}>
                <p><b>Member ID:</b> {f.mid}</p>
                <p><b>Trainer ID:</b> {f.tid}</p>

                <p style={styles.comment}>
                  {f.comment || "No comment provided"}
                </p>

                <div style={styles.rating}>
                  {[...Array(5)].map((_, i) => (
                    <FaStar
                      key={i}
                      color={i < f.rating ? "#f1c40f" : "#ccc"}
                    />
                  ))}
                </div>

                <p style={styles.date}>
                  {new Date(f.date).toLocaleString()}
                </p>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

/* ================= STYLES ================= */

const styles = {
  container: {
    padding: "25px",
    background: "#f4f6f8",
    minHeight: "100vh",
    fontFamily: "Segoe UI, sans-serif",
  },
  heading: {
    marginBottom: "20px",
    color: "#333",
  },
  searchBox: {
    display: "flex",
    alignItems: "center",
    gap: "8px",
    background: "#fff",
    padding: "10px",
    width: "350px",
    borderRadius: "8px",
    marginBottom: "20px",
    boxShadow: "0 2px 6px rgba(0,0,0,0.1)",
  },
  searchInput: {
    border: "none",
    outline: "none",
    width: "100%",
  },
  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(320px, 1fr))",
    gap: "20px",
  },
  card: {
    display: "flex",
    gap: "15px",
    background: "#fff",
    padding: "18px",
    borderRadius: "12px",
    boxShadow: "0 4px 10px rgba(0,0,0,0.1)",
  },
  icon: {
    display: "flex",
    alignItems: "center",
  },
  comment: {
    margin: "10px 0",
    fontStyle: "italic",
    color: "#555",
  },
  rating: {
    display: "flex",
    gap: "4px",
    marginBottom: "8px",
  },
  date: {
    fontSize: "12px",
    color: "#777",
  },
};

export default Feedbacks;
