import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
  members: [],
  loading: false,
  error: null,
};

/* ================= FETCH ASSIGNED MEMBERS ================= */
export const fetchAssignedMembers = createAsyncThunk(
  "trainerMembers/fetchAssigned",
  async (trainerId, { rejectWithValue }) => {
    try {
      const res = await axios.get(
        `http://localhost:5259/api/trainer/members/${trainerId}`
      );
      return res.data;
    } catch (err) {
      return rejectWithValue("Unable to load assigned members");
    }
  }
);

const trainerMembersSlice = createSlice({
  name: "trainerMembers",
  initialState,
  reducers: {
    clearTrainerMembers: (state) => {
      state.members = [];
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchAssignedMembers.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchAssignedMembers.fulfilled, (state, action) => {
        state.loading = false;
        state.members = action.payload;
      })
      .addCase(fetchAssignedMembers.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export const { clearTrainerMembers } = trainerMembersSlice.actions;
export default trainerMembersSlice.reducer;
