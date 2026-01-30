import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/common/Navbar";
import Home from "./components/common/Home";
import Login from "./components/common/Login";
import Register from "./components/common/Register";

import MemberDashboard from "./components/member/MemberDashboard";
import TrainerDashboard from "./components/trainer/TrainerDashboard";

import AdminLayout from "./components/admin/AdminLayout";
import Dashboard from "./components/admin/Dashboard";
import Members from "./components/admin/Members";
import Trainers from "./components/admin/Trainers";
import Plans from "./components/admin/Plans";
import Payments from "./components/admin/Payments";
import Feedback from "./components/admin/Feedback";

function App() {
  return (
    <BrowserRouter>
      {/* ✅ NAVBAR ALWAYS VISIBLE */}
       <Navbar />
        <Routes>

        {/* ---------- PUBLIC ---------- */}
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* ---------- ADMIN ROUTES ---------- */}
        <Route path="/admin" element={<AdminLayout />}>
          <Route index element={<Dashboard />} />
          <Route path="dashboard" element={<Dashboard />} />
          <Route path="members" element={<Members />} />
          <Route path="trainers" element={<Trainers />} />
          <Route path="plans" element={<Plans />} />
          <Route path="payments" element={<Payments />} />
          <Route path="feedback" element={<Feedback />} />
        </Route>

        {/* ---------- TRAINER ---------- */}
        <Route path="/trainer/trainer-dashboard" element={<TrainerDashboard />} />

        {/* ---------- MEMBER ---------- */}
        <Route path="/member/member-dashboard" element={<MemberDashboard />} />

      </Routes>
    </BrowserRouter>
  );
}

export default App;
