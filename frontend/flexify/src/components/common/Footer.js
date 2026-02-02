import React from "react";
import "./Footer.css";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-content"></div>

      <div className="footer-bottom">
        © {new Date().getFullYear()} Flexify. All rights reserved.
      </div>
    </footer>
  );
};

export default Footer;
