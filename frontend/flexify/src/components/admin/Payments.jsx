import { useEffect, useState } from "react";
import axios from "axios";

const Payments = () => {
  const [payments, setPayments] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/flexify/admin/payments")
      .then(res => setPayments(res.data));
  }, []);

  return (
    <>
      <h3>Payments</h3>

      <table className="table table-bordered mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Member</th>
            <th>Amount</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          {payments.map(p => (
            <tr key={p.paymentId}>
              <td>{p.paymentId}</td>
              <td>{p.mid}</td>
              <td>₹{p.amount}</td>
              <td>{p.paymentDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

export default Payments;
