import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useLocation } from "react-router-dom";
import axios from "axios";

/* ===== REDUX ===== */
import { fetchMemberByUid } from "../redux/member/memberSlice";

/* ===== COMPONENTS ===== */
import Profile from "./Profile";
import ProgressForm from "./ProgressForm";
import PurchasePlan from "./PurchasePlan";
import MakePayment from "./MakePayment";
import WorkoutPlan from "./WorkoutPlan";
import Attendance from "./Attendance";
import RenewMembership from "./RenewMembership";
import Feedback from "./Feedback";
import BMICalculator from "./BMICalculator";

/* ===== ICONS ===== */
import {
  FaHome,
  FaUser,
  FaChartLine,
  FaShoppingCart,
  FaCreditCard,
  FaDumbbell,
  FaCalendarCheck,
  FaRedo,
  FaStar,
} from "react-icons/fa";

/* ===== CSS ===== */
import "../member/memberDashboard.css";

/* ================= DASHBOARD HOME ================= */
const DashboardHome = ({ setActive }) => {
  const { user } = useSelector((state) => state.auth);
  const { member } = useSelector((state) => state.member);

  const [activeMembership, setActiveMembership] = useState(null);
  const [latestWeight, setLatestWeight] = useState(null);
  const [latestBmi, setLatestBmi] = useState(null);

  /* ===== ATTENDANCE COUNTS ===== */
  const [presentCount, setPresentCount] = useState(0);
  const [absentCount, setAbsentCount] = useState(0);

  useEffect(() => {
    if (!member?.mid) return;

    axios
      .get(`http://localhost:8083/flexify/member/membership/${member.mid}`)
      .then((res) =>
        setActiveMembership(res.data.find((m) => m.status === "ACTIVE")),
      );
  }, [member]);

  useEffect(() => {
    if (!member?.mid) return;

    axios
      .get(`http://localhost:8083/flexify/member/progress/${member.mid}`)
      .then((res) => {
        if (res.data.length > 0) {
          const last = res.data[res.data.length - 1];
          setLatestWeight(last.weight);
          setLatestBmi(last.bmi);
        }
      });
  }, [member]);

  /* ===== FETCH ATTENDANCE COUNTS ===== */
  useEffect(() => {
    if (!member?.mid) return;

    Promise.all([
      axios.get(
        `http://localhost:8083/flexify/member/attendence/count/${member.mid}/PRESENT`,
      ),
      axios.get(
        `http://localhost:8083/flexify/member/attendence/count/${member.mid}/ABSENT`,
      ),
    ]).then(([p, a]) => {
      setPresentCount(p.data);
      setAbsentCount(a.data);
    });
  }, [member]);

  const remainingDays = activeMembership
    ? Math.max(
        Math.ceil(
          (new Date(activeMembership.endDate) - new Date()) /
            (1000 * 60 * 60 * 24),
        ),
        0,
      )
    : 0;

  return (
    <div>
      <h2 className="welcome-text">
        👋 Welcome, <span>{user?.fname || "Member"}</span>
      </h2>
      <p className="welcome-sub">
        Stay consistent. Train smart. Results will follow 💪
      </p>

      <div className="dashboard-grid">
        {/* MEMBERSHIP */}
        <div className="card-ui">
          <h4>Membership & Plan</h4>
          <p className="card-value">
            {activeMembership?.plan?.planName || "No Active Plan"}
          </p>
          {activeMembership && (
            <>
              <p>Status: ACTIVE</p>
              <p>⏳ {remainingDays} days remaining</p>
            </>
          )}
        </div>

        {/* ATTENDANCE */}
        <div
          className="card-ui clickable"
          onClick={() => setActive("Attendance History")}
        >
          <h4>Attendance</h4>
          <p className="card-value">{presentCount} days Present</p>
          <p>{absentCount} days Absent</p>
        </div>

        {/* BMI */}
        <div
          className="card-ui clickable"
          onClick={() => setActive("Progress")}
        >
          <h4>BMI Tracker</h4>
          {latestBmi ? (
            <>
              <p className="card-value">{latestBmi}</p>
              <p>Weight: {latestWeight} kg</p>
            </>
          ) : (
            <p>No BMI recorded</p>
          )}
        </div>
      </div>

      <div className="dashboard-bottom">
        <BMICalculator />
      </div>
    </div>
  );
};

/* ================= MEMBER DASHBOARD ================= */
const MemberDashboard = () => {
  const dispatch = useDispatch();
  const location = useLocation();

  const { user } = useSelector((state) => state.auth);
  const { member, loading } = useSelector((state) => state.member);

  const [active, setActive] = useState("Dashboard");

  useEffect(() => {
    if (user?.uid) dispatch(fetchMemberByUid(user.uid));
  }, [user, dispatch]);

  useEffect(() => {
    if (!loading && !member) setActive("Profile");
  }, [member, loading]);

  useEffect(() => {
    if (location.state?.open) {
      setActive(location.state.open);
      window.history.replaceState({}, document.title);
    }
  }, [location.state]);

  const menuItems = [
    { name: "Dashboard", icon: <FaHome /> },
    { name: "Profile", icon: <FaUser /> },
    { name: "Progress", icon: <FaChartLine /> },
    { name: "Purchase Plan", icon: <FaShoppingCart /> },
    { name: "Make Payment", icon: <FaCreditCard /> },
    { name: "Workout Schedule", icon: <FaDumbbell /> },
    { name: "Attendance History", icon: <FaCalendarCheck /> },
    { name: "Renew Membership", icon: <FaRedo /> },
    { name: "Feedback & Rating", icon: <FaStar /> },
  ];

  const renderComponent = () => {
    switch (active) {
      case "Dashboard":
        return <DashboardHome setActive={setActive} />;
      case "Profile":
        return <Profile />;
      case "Progress":
        return <ProgressForm />;
      case "Purchase Plan":
        return <PurchasePlan setActive={setActive} />;
      case "Make Payment":
        return <MakePayment />;
      case "Workout Schedule":
        return <WorkoutPlan />;
      case "Attendance History":
        return <Attendance />;
      case "Renew Membership":
        return <RenewMembership />;
      case "Feedback & Rating":
        return <Feedback />;
      default:
        return null;
    }
  };

  return (
    <div className="dashboard-bg">
      {/* SIDEBAR */}
      <aside className="sidebar-black">
        {menuItems.map((item) => (
          <button
            key={item.name}
            className={`side-btn ${active === item.name ? "active" : ""}`}
            onClick={() => setActive(item.name)}
          >
            {item.icon}
            {item.name}
          </button>
        ))}
      </aside>

      {/* CONTENT */}
      <main className="content-area">{renderComponent()}</main>
    </div>
  );
};

export default MemberDashboard;
