import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./authSlice";
import memberReducer from "./member/memberSlice";
import membershipReducer from "./member/membershipSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    member: memberReducer,
    membership: membershipReducer,
  },
});
