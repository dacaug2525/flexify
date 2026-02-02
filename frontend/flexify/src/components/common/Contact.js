import React from "react";

const Contact = () => {
  return (
    <div className="contact-page">
      <div className="contact-card">
        <h2 className="contact-heading">📞 Contact Flexify</h2>

        <p className="contact-subtitle">
          We’re here to help you with memberships, workouts, and support.
        </p>

        <div className="contact-info">
          <div className="contact-item">
            <span className="label">📧 Email</span>
            <span className="value">support@flexify.com</span>
          </div>

          <div className="contact-item">
            <span className="label">📞 Phone</span>
            <span className="value">+91 98765 43210</span>
          </div>

          <div className="contact-item">
            <span className="label">📍 Address</span>
            <span className="value">
              Flexify Fitness Pvt. Ltd.
              <br />
              Pune, Maharashtra, India
            </span>
          </div>

          <div className="contact-item">
            <span className="label">🕒 Support Hours</span>
            <span className="value">Mon – Sat | 6:00 AM – 10:00 PM</span>
          </div>
        </div>

        <div className="contact-footer">
          Stay consistent. Train smart. Results will follow 💪
        </div>
      </div>

      {/* ================= STYLES ================= */}
      <style>{`
        /* PAGE BACKGROUND (SAME AS LOGIN) */
        .contact-page {
          min-height: calc(100vh - 70px);
          background: linear-gradient(135deg, #020b1f, #04152f);
          display: flex;
          align-items: center;
          justify-content: center;
          padding: 40px;
        }

        /* CARD */
        .contact-card {
          width: 420px;
          background: #020b1f;
          padding: 35px;
          border-radius: 20px;
          border: 1.5px solid rgba(56,189,248,0.45);
          box-shadow: 0 0 30px rgba(56,189,248,0.35);
          color: #e5e7eb;
          transition: all 0.35s ease;
        }

        .contact-card:hover {
          transform: translateY(-6px);
          box-shadow: 0 0 45px rgba(56,189,248,0.6);
        }

        /* HEADING */
        .contact-heading {
          text-align: center;
          color: #38bdf8;
          font-weight: 700;
          margin-bottom: 10px;
        }

        /* SUBTITLE */
        .contact-subtitle {
          text-align: center;
          font-size: 0.95rem;
          color: #cbd5e1;
          margin-bottom: 25px;
        }

        /* INFO */
        .contact-info {
          display: flex;
          flex-direction: column;
          gap: 18px;
        }

        .contact-item {
          display: flex;
          flex-direction: column;
          background: rgba(255,255,255,0.05);
          padding: 14px 16px;
          border-radius: 12px;
        }

        .label {
          font-size: 0.8rem;
          color: #94a3b8;
          margin-bottom: 4px;
        }

        .value {
          font-size: 0.95rem;
          color: #e5e7eb;
          font-weight: 500;
          line-height: 1.4;
        }

        /* FOOTER */
        .contact-footer {
          margin-top: 25px;
          text-align: center;
          font-size: 0.85rem;
          color: #9ca3af;
        }

        /* RESPONSIVE */
        @media (max-width: 480px) {
          .contact-card {
            width: 100%;
          }
        }
      `}</style>
    </div>
  );
};

export default Contact;
