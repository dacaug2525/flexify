import { useEffect, useState } from "react";
import axios from "axios";

const Feedback = () => {
  const [feedbacks, setFeedbacks] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/flexify/admin/feedback")
      .then(res => setFeedbacks(res.data));
  }, []);

  return (
    <>
      <h3>Feedback & Ratings</h3>

      <table className="table table-striped mt-3">
        <thead>
          <tr>
            <th>Member</th>
            <th>Trainer</th>
            <th>Rating</th>
            <th>Comment</th>
          </tr>
        </thead>
        <tbody>
          {feedbacks.map(f => (
            <tr key={f.feedbackId}>
              <td>{f.mid}</td>
              <td>{f.tid}</td>
              <td>{f.rating} ⭐</td>
              <td>{f.comment}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

export default Feedback;
