import User from '../user.model';
import { UserActions, UserActionsTypes } from './user.actions';
import { createSelector} from '@ngrx/store';
import { State } from '../../../../shared/store';

export interface UserState {
  data: User;
  error: string;
  loading: boolean;
}

export const initialUserState: UserState = {
  data: null,
  error: null,
  loading: false
};

export function reducer(state: UserState = initialUserState, action: UserActions) {
  switch (action.type) {
    case UserActionsTypes.GET_CURRENT_USER:
      return {
        ...state,
        error: null,
        loading: true
      };
    case UserActionsTypes.GET_CURRENT_USER_SUCCESS:
      return {
        ...state,
        loading: false
      };
    case UserActionsTypes.LOGIN_USER:
      return {
        ...state,
        error: null,
        loading: true
      };
    case UserActionsTypes.LOGIN_USER_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.payload
      };
    case UserActionsTypes.LOGOUT_USER:
      return {
        ...state,
        error: null,
        loading: true
      };
    case UserActionsTypes.LOGOUT_USER_SUCCESS:
      return {
        ...state,
        loading: false,
        data: null
      };
    case UserActionsTypes.USER_ERROR:
      return {
        ...state,
        loading: false,
        error: action.payload,
        data: null
      };
    case UserActionsTypes.SIGNUP_USER:
      return {
        ...state,
        loading: true,
      };
    case UserActionsTypes.SIGNUP_USER_SUCCESS:
      return {
        ...state,
        loading: false,
        data: null
      };
    default:
      return state;
  }
}

const selectUserState = (state: State) => state.userState;

export const selectCurrentUser = createSelector(
  selectUserState,
  (state: UserState) => state.data
);

export const selectUserError = createSelector(
  selectUserState,
  (state: UserState) => state.error
);

