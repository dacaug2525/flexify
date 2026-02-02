import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

/* ===== REDUX ===== */
import { fetchPlans } from "../redux/member/membershipSlice";

const PlanCatalog = () => {
  const dispatch = useDispatch();
  const { plans } = useSelector((state) => state.membership);

  /* ===== LOAD PLANS ===== */
  useEffect(() => {
    dispatch(fetchPlans());
  }, [dispatch]);

  /* ===== PRICE CALC ===== */
  const getFinalPrice = (plan) => {
    if (!plan.discount) return plan.fees;
    return plan.fees - (plan.fees * plan.discount.discount) / 100;
  };

  return (
    <div className="plan-page">
      {/* ===== HEADING ===== */}
      <h3 className="plan-heading">📋 Membership Plans</h3>

      {/* ===== PLAN GRID ===== */}
      <div className="plan-grid">
        {plans.map((plan) => {
          const finalPrice = getFinalPrice(plan);

          return (
            <div key={plan.planId} className="plan-card neon-border">
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
            </div>
          );
        })}
      </div>

      {/* ===== STYLES ===== */}
      <style>{`
        /* PAGE BACKGROUND (LOGIN PAGE MATCH) */
        .plan-page {
          min-height: calc(100vh - 70px);
          padding: 40px;
          background: linear-gradient(135deg, #020b1f, #04152f);
          color: #e5e7eb;
        }

        .plan-heading {
          color: #38bdf8;
          font-weight: 700;
          margin-bottom: 30px;
        }

        .plan-grid {
          display: grid;
          grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
          gap: 28px;
        }

        /* PLAN CARD */
        .plan-card {
          background: #29323e;
          padding: 26px;
          border-radius: 22px;
          position: relative;
          transition: all 0.35s ease;
        }

        /* NEON BORDER */
        .neon-border {
          border: 1.5px solid rgba(56,189,248,0.45);
          box-shadow:
            0 0 18px rgba(56,189,248,0.35),
            inset 0 0 12px rgba(56,189,248,0.15);
        }

        .plan-card:hover {
          transform: translateY(-6px);
          box-shadow:
            0 0 30px rgba(56,189,248,0.65),
            inset 0 0 14px rgba(56,189,248,0.25);
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

export default PlanCatalog;
