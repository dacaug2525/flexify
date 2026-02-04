import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
  trainer: null,
  loading: false,
  error: null,
};

/* ================= FETCH TRAINER BY UID ================= */
export const fetchTrainerByUid = createAsyncThunk(
  "trainer/fetchByUid",
  async (uid) => {
    const res = await axios.get(
      `http://localhost:8083/flexify/trainer/by-user/${uid}`
    );
    return res.data;
  }
);

/* ================= UPDATE TRAINER ================= */
export const updateTrainer = createAsyncThunk(
  "trainer/update",
  async ({ tid, payload }) => {
    const res = await axios.put(
      `http://localhost:8083/flexify/trainer/update/${tid}`,
      payload
    );
    return res.data;
  }
);

const trainerSlice = createSlice({
  name: "trainer",
  initialState,
  reducers: {
    clearTrainer: (state) => {
      state.trainer = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchTrainerByUid.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchTrainerByUid.fulfilled, (state, action) => {
        state.loading = false;
        state.trainer = action.payload;
      })
      .addCase(fetchTrainerByUid.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message;
      })
      .addCase(updateTrainer.fulfilled, (state, action) => {
        state.trainer = action.payload;
      });
  },
});

export const { clearTrainer } = trainerSlice.actions;
export default trainerSlice.reducer;
