import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
  member: null,
  loading: false,
  error: null,
};

// Fetch member by UID
export const fetchMemberByUid = createAsyncThunk(
  "member/fetchByUid",
  async (uid) => {
    const res = await axios.get(
      `http://localhost:8080/member/by-user/${uid}`,
    );
    return res.data;
  },
);

// Add member
export const addMember = createAsyncThunk("member/add", async (payload) => {
  const res = await axios.post(
    "http://localhost:8080/member/add-full",
    payload,
  );
  return res.data;
});

// Update member
export const updateMember = createAsyncThunk(
  "member/update",
  async ({ mid, payload }) => {
    const res = await axios.put(
      `http://localhost:8080/member/update/${mid}`,
      payload,
    );
    return res.data;
  },
);

const memberSlice = createSlice({
  name: "member",
  initialState,
  reducers: {
    clearMember: (state) => {
      state.member = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchMemberByUid.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchMemberByUid.fulfilled, (state, action) => {
        state.loading = false;
        state.member = action.payload;
      })
      .addCase(fetchMemberByUid.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message;
      })
      .addCase(addMember.fulfilled, (state, action) => {
        state.member = action.payload;
      })
      .addCase(updateMember.fulfilled, (state, action) => {
        state.member = action.payload;
      });
  },
});

export const { clearMember } = memberSlice.actions;
export default memberSlice.reducer;
