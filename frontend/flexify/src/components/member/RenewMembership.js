import React, { useEffect, useState } from "react";
import axios from "axios";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

const RenewMembership = () => {
  const navigate = useNavigate();
  const { member } = useSelector((state) => state.member);

  const [activePlan, setActivePlan] = useState(null);
  const [error, setError] = useState("");

  /* ================= FETCH ACTIVE MEMBERSHIP ================= */
  useEffect(() => {
    if (!member?.mid) return;

    axios
      .get(`http://localhost:8083/flexify/member/membership/${member.mid}`)
      .then((res) => {
        const active = res.data.find((m) => m.status === "ACTIVE");
        setActivePlan(active || null);
      })
      .catch(() => setError("Unable to load membership info"));
  }, [member]);

  /* ================= PRICE CALC ================= */
  const getFinalPrice = () => {
    if (!activePlan?.plan?.discount) return activePlan.plan.fees;

    return (
      activePlan.plan.fees -
      (activePlan.plan.fees * activePlan.plan.discount.discount) / 100
    );
  };

  /* ================= RENEW ================= */
  const handleRenew = () => {
    if (!activePlan) return;

    const finalAmount = getFinalPrice();

    localStorage.setItem("selectedPlanId", activePlan.plan.planId);
    localStorage.setItem("selectedPlanName", activePlan.plan.planName);
    localStorage.setItem("payAmount", finalAmount);

    navigate("/member/member-dashboard", {
      state: { open: "Make Payment" },
    });
  };

  /* ================= NO ACTIVE PLAN ================= */
  if (!activePlan) {
    return (
      <div className="renew-wrapper">
        <div className="plan-card">
          <h4 className="renew-heading">No Active Membership</h4>
          <p className="plan-sub">You don’t have an active plan to renew.</p>
        </div>
      </div>
    );
  }

  const finalPrice = getFinalPrice();

  return (
    <div className="renew-wrapper">
      <div className="plan-card" style={{ width: 420 }}>
        <h3 className="renew-heading">🔄 Renew Membership</h3>

        {error && <div className="alert alert-danger">{error}</div>}

        {/* ===== PLAN INFO ===== */}
        <div style={{ marginBottom: 12 }}>
          <h4 className="plan-title">{activePlan.plan.planName}</h4>

          <p className="plan-sub">
            Duration: {activePlan.plan.planDuration} Months
          </p>

          <p className="plan-sub">
            Current Expiry:{" "}
            {new Date(activePlan.endDate).toLocaleDateString("en-IN")}
          </p>
        </div>

        {/* ===== FEES ===== */}
        <p className="plan-price">
          {activePlan.plan.discount ? (
            <>
              <span className="price-cut">₹{activePlan.plan.fees}</span>
              <br />
              <span className="price-final">₹{finalPrice}</span>
            </>
          ) : (
            `₹${activePlan.plan.fees}`
          )}
        </p>

        {activePlan.plan.discount && (
          <span className="plan-discount">
            🎉 {activePlan.plan.discount.discount}% renewal discount applied
          </span>
        )}

        {/* ===== TRAININGS ===== */}
        <div style={{ marginTop: 14 }}>
          <strong className="plan-sub">Included Trainings</strong>
          <ul style={{ paddingLeft: 18, marginTop: 6 }}>
            {activePlan.plan.planTrainings?.map((pt) => (
              <li key={pt.ptId} className="plan-sub">
                {pt.training.trName}
              </li>
            ))}
          </ul>
        </div>

        {/* ===== ACTION ===== */}
        <button className="btn btn-primary w-100 mt-3" onClick={handleRenew}>
          Renew Now
        </button>
      </div>

      {/* ===== STYLES (SAME AS PURCHASE PLAN) ===== */}
      <style>{`
        .renew-wrapper {
          display: flex;
          justify-content: center;
          margin-top: 40px;
        }

        .renew-heading {
          text-align: center;
          margin-bottom: 20px;
          color: #2563eb;
          font-weight: 600;
        }

        .plan-card {
          background: rgba(51, 53, 63, 0.86);
          padding: 26px;
          border-radius: 20px;
          box-shadow: 0 8px 20px rgba(0,0,0,0.25);
          transition: all 0.3s ease;
        }

        .plan-card:hover {
          transform: translateY(-4px);s
          box-shadow: 0 12px 30px rgba(0,0,0,0.35);
        }

        .plan-title {
          color: #38bdf8;
          font-weight: 600;
          margin-bottom: 6px;
        }

        .plan-price {
          font-size: 1.25rem;
          font-weight: 700;
          color: #38bdf8;
          margin-bottom: 6px;
        }

        .price-cut {
          text-decoration: line-through;
          font-size: 0.85rem;
          color: #9ca3af;
        }

        .price-final {
          color: #22c55e;
        }

        .plan-sub {
          font-size: 0.85rem;
          color: #e5e7eb;
        }

        .plan-discount {
          font-size: 0.8rem;
          color: #facc15;
        }
      `}</style>
    </div>
  );
};

export default RenewMembership;
