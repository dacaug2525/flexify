import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

/* ================= FETCH ALL PLANS ================= */
export const fetchPlans = createAsyncThunk(
  "membership/fetchPlans",
  async () => {
    const res = await axios.get(
      "http://localhost:8080/member/membership/plan",
    );
    return res.data;
  },
);

/* ================= FETCH MEMBER MEMBERSHIP ================= */
export const fetchMemberMembership = createAsyncThunk(
  "membership/fetchMemberMembership",
  async (memberId) => {
    const res = await axios.get(
      `http://localhost:8080/member/membership/${memberId}`,
    );
    return res.data;
  },
);

const membershipSlice = createSlice({
  name: "membership",
  initialState: {
    plans: [],
    activeMembership: null,
    selectedPlan: null, // { planId, planName, amount }
    loading: false,
  },
  reducers: {
    selectPlan: (state, action) => {
      state.selectedPlan = action.payload;
    },
    clearSelectedPlan: (state) => {
      state.selectedPlan = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchPlans.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchPlans.fulfilled, (state, action) => {
        state.plans = action.payload;
        state.loading = false;
      })
      .addCase(fetchMemberMembership.fulfilled, (state, action) => {
        state.activeMembership =
          action.payload.find((m) => m.status === "ACTIVE") || null;
      });
  },
});

export const { selectPlan, clearSelectedPlan } = membershipSlice.actions;

export default membershipSlice.reducer;
