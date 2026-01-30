import { useEffect, useState } from "react";
import axios from "axios";

const Members = () => {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/flexify/admin/members")
      .then(res => setMembers(res.data));
  }, []);

  return (
    <>
      <h3>Members</h3>

      <table className="table table-bordered mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
          </tr>
        </thead>
        <tbody>
          {members.map(m => (
            <tr key={m.id}>
              <td>{m.id}</td>
              <td>{m.name}</td>
              <td>{m.email}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

export default Members;
