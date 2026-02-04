import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

export const fetchTrainerDashboard = createAsyncThunk(
  "trainerDashboard/fetch",
  async (trainerId, { rejectWithValue }) => {
    try {
      const res = await axios.get(
        `http://localhost:5259/api/trainer/dashboard/${trainerId}`
      );
      return res.data;
    } catch (err) {
      return rejectWithValue("Failed to load dashboard data");
    }
  }
);

const trainerDashboardSlice = createSlice({
  name: "trainerDashboard",
  initialState: {
    data: null,
    loading: false,
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchTrainerDashboard.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchTrainerDashboard.fulfilled, (state, action) => {
        state.loading = false;
        state.data = action.payload;
      })
      .addCase(fetchTrainerDashboard.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export default trainerDashboardSlice.reducer;
