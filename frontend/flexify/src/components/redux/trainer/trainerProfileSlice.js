import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

export const fetchTrainerProfile = createAsyncThunk(
  "trainerProfile/fetch",
  async (tid) => {
    const res = await axios.get(
      `http://localhost:8083/flexify/trainer/profile/${tid}`
    );
    return res.data;
  }
);

const trainerProfileSlice = createSlice({
  name: "trainerProfile",
  initialState: {
    profile: null,
    loading: false,
  },
  reducers: {
    clearTrainerProfile: (state) => {
      state.profile = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchTrainerProfile.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchTrainerProfile.fulfilled, (state, action) => {
        state.loading = false;
        state.profile = action.payload;
      });
  },
});

export const { clearTrainerProfile } = trainerProfileSlice.actions;
export default trainerProfileSlice.reducer;
