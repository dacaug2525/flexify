import React from "react";
import { Link } from "react-router-dom";

function Home() {
  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100 bg-light">
      <div className="text-center">
        <h1 className="mb-3 text-primary">Welcome to Flexify</h1>
        <p className="mb-4 text-muted">
          Gym Management & Workout Tracking System
        </p>
      </div>
    </div>
  );
}

export default Home;
