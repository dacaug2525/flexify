import { useEffect, useState } from "react";
import axios from "axios";

const Plans = () => {
  const [plans, setPlans] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/flexify/admin/plans")
      .then(res => setPlans(res.data));
  }, []);

  return (
    <>
      <h3>Membership Plans</h3>

      <table className="table table-hover mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Duration</th>
            <th>Fees</th>
          </tr>
        </thead>
        <tbody>
          {plans.map(p => (
            <tr key={p.planId}>
              <td>{p.planId}</td>
              <td>{p.planName}</td>
              <td>{p.planDuration} months</td>
              <td>₹{p.fees}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

export default Plans;
