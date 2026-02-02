import axios from "axios";

const BASE_URL = "http://localhost:5259/api/progress";

// GET member progress (trainer only)
export const getMemberProgress = (tid, mid) => {
  return axios.get(`${BASE_URL}/trainer/${tid}/member/${mid}`);
};

// ADD progress
export const addMemberProgress = (tid, progressData) => {
  return axios.post(`${BASE_URL}/trainer/${tid}/add`, progressData);
};

// UPDATE progress
export const updateMemberProgress = (tid, progressId, progressData) => {
  return axios.put(`${BASE_URL}/trainer/${tid}/${progressId}`, progressData);
};
