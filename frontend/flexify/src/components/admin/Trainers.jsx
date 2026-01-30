import { useEffect, useState } from "react";
import axios from "axios";

const Trainers = () => {
  const [trainers, setTrainers] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/flexify/admin/trainers")
      .then(res => setTrainers(res.data));
  }, []);

  return (
    <>
      <h3>Trainers</h3>

      <table className="table table-striped mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Specialization</th>
          </tr>
        </thead>
        <tbody>
          {trainers.map(t => (
            <tr key={t.id}>
              <td>{t.id}</td>
              <td>{t.name}</td>
              <td>{t.specialization}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

export default Trainers;
