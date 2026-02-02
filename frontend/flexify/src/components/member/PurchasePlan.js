import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

/* ===== REDUX ===== */
import {
  fetchPlans,
  fetchMemberMembership,
  selectPlan,
} from "../redux/member/membershipSlice";

const PurchasePlan = ({ setActive }) => {
  const dispatch = useDispatch();
  const { member } = useSelector((state) => state.member);
  const { plans, activeMembership } = useSelector((state) => state.membership);

  /* ===== LOAD PLANS & MEMBERSHIP ===== */
  useEffect(() => {
    dispatch(fetchPlans());
    if (member?.mid) dispatch(fetchMemberMembership(member.mid));
  }, [dispatch, member]);

  /* ===== PRICE CALC ===== */
  const getFinalPrice = (plan) => {
    if (!plan.discount) return plan.fees;
    return plan.fees - (plan.fees * plan.discount.discount) / 100;
  };

  /* ===== PURCHASE ===== */
  const handlePurchase = (plan) => {
    if (activeMembership) return;

    dispatch(
      selectPlan({
        planId: plan.planId,
        planName: plan.planName,
        amount: getFinalPrice(plan),
      }),
    );

    if (typeof setActive === "function") {
      setActive("Make Payment");
    }
  };

  return (
    <div>
      {/* ===== HEADING ===== */}
      <h3
        className="mb-4"
        style={{
          color: "#2563eb", // same as Profile heading
          fontWeight: 600,
        }}
      >
        💳 Choose Membership Plan
      </h3>

      {activeMembership && (
        <div className="alert alert-warning">
          You already have an active plan. You can renew it instead.
        </div>
      )}

      {/* ===== PLAN GRID ===== */}
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fill, minmax(260px, 1fr))",
          gap: "25px",
        }}
      >
        {plans.map((plan) => {
          const finalPrice = getFinalPrice(plan);

          return (
            <div key={plan.planId} className="plan-card">
              <h4 className="plan-title">{plan.planName}</h4>

              <p className="plan-sub">Duration: {plan.planDuration} Months</p>

              <p className="plan-price">
                {plan.discount ? (
                  <>
                    <span className="price-cut">₹{plan.fees}</span>
                    <br />
                    <span className="price-final">₹{finalPrice}</span>
                  </>
                ) : (
                  `₹${plan.fees}`
                )}
              </p>

              {plan.discount && (
                <span className="plan-discount">
                  🎉 {plan.discount.discount}% OFF
                </span>
              )}

              <p className="plan-sub" style={{ marginTop: 10 }}>
                {plan.description}
              </p>

              {/* ===== TRAININGS ===== */}
              {plan.planTrainings?.length > 0 && (
                <div style={{ marginTop: 10 }}>
                  <strong className="plan-sub">Included Trainings</strong>
                  <ul style={{ paddingLeft: 18, marginTop: 6 }}>
                    {plan.planTrainings.map((pt) => (
                      <li key={pt.ptId} className="plan-sub">
                        {pt.training.trName}
                      </li>
                    ))}
                  </ul>
                </div>
              )}

              <button
                className="btn btn-primary w-100 mt-3"
                disabled={!!activeMembership}
                onClick={() => handlePurchase(plan)}
              >
                {activeMembership ? "Already Active" : "Purchase Plan"}
              </button>
            </div>
          );
        })}
      </div>

      {/* ===== STYLES ===== */}
      <style>{`
        .plan-card {
          background: #29323e; /* lighter dark grey */
          padding: 26px;
          border-radius: 20px;
          box-shadow: 0 8px 20px rgba(0,0,0,0.25);
          transition: all 0.3s ease;
        }

        .plan-card:hover {
          transform: translateY(-4px);
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

export default PurchasePlan;
