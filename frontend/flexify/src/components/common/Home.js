import React, { useEffect, useState } from "react";
import "./Home.css";

const images = [
  "/images/gym1.jpg",
  "/images/gym2.jpg",
  "/images/gym9.jpg",
  "/images/gym3.jpg",
  "/images/gym4.jpg",
  "/images/gym7.jpeg",
  "/images/gym6.jpg",
  "/images/yoga2.jpg",
];

function Home() {
  const [index, setIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setIndex((prev) => (prev + 1) % images.length);
    }, 5000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div
      className="home-hero"
      style={{
        backgroundImage: `linear-gradient(
          rgba(15,23,42,0.75),
          rgba(2,6,23,0.9)
        ), url(${images[index]})`,
      }}
    >
      <div className="home-content">
        <h1 className="home-brand">Flexify</h1>

        <p className="home-tagline">
          Your all-in-one gym management & workout tracking platform.
        </p>

        <ul className="home-features">
          <li>🏋️ Smart workout & training plans</li>
          <li>📊 Track BMI & performance</li>
          <li>💳 Membership & payments</li>
          <li>📅 Attendance & renewals</li>
          <li>⭐ Feedback & trainer ratings</li>
        </ul>

        <p className="home-footer-text">
          Built for members, trainers, and gym owners.
        </p>
      </div>
    </div>
  );
}

export default Home;
