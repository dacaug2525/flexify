import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./authSlice";
import memberReducer from "./member/memberSlice";
import membershipReducer from "./member/membershipSlice";
import trainerReducer from "./trainer/trainerSlice";
import trainerProfileReducer from "./trainer/trainerProfileSlice";
import trainerMembersReducer from "./trainer/trainerMemberSlice";
import trainerDashboardReducer from "./trainer/TrainerDashboardSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    member: memberReducer,
    membership: membershipReducer,
    trainer: trainerReducer,
    trainerProfile: trainerProfileReducer,
    trainerMembers: trainerMembersReducer, 
    trainerDashboard: trainerDashboardReducer,
  },
});
