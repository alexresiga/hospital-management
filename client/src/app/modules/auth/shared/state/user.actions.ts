import { Action } from '@ngrx/store';
import User, { UserLoginInput, UserSignupInput } from '../user.model';

export enum UserActionsTypes {
  GET_CURRENT_USER = '[USER] Get current',
  GET_CURRENT_USER_SUCCESS = '[USER] Get current success',
  SIGNUP_USER = '[USER] SIGNUP',
  LOGIN_USER = '[USER] LOGIN',
  LOGIN_USER_SUCCESS = '[USER] LOGIN SUCCESS',
  LOGOUT_USER = '[USER] LOGOUT',
  LOGOUT_USER_SUCCESS = '[USER] LOGOUT SUCCESS',
  USER_ERROR = '[USER] ERROR'
}

export class GetCurrentUser implements Action {
  public readonly type = UserActionsTypes.GET_CURRENT_USER;
}

export class GetCurrentUserSuccess implements Action {
  public readonly type = UserActionsTypes.GET_CURRENT_USER_SUCCESS;

  constructor(public payload: User) {
  }
}

export class LoginUser implements Action {
  public readonly type = UserActionsTypes.LOGIN_USER;

  constructor(public payload: UserLoginInput) {
  }
}

export class SignupUser implements Action {
  public readonly type = UserActionsTypes.SIGNUP_USER;

  constructor(public payload: UserSignupInput) {
  }
}

export class LoginUserSuccess implements Action {
  public readonly type = UserActionsTypes.LOGIN_USER_SUCCESS;

  constructor(public payload: User) {
  }
}

export class LogoutUser implements Action {
  public readonly type = UserActionsTypes.LOGOUT_USER;
}

export class LogoutUserSuccess implements Action {
  public readonly type = UserActionsTypes.LOGOUT_USER_SUCCESS;
}

export class UserError implements Action {
  public readonly type = UserActionsTypes.USER_ERROR;

  constructor(public payload: string) {}
}


export type UserActions =
  | GetCurrentUser
  | GetCurrentUserSuccess
  | LoginUser
  | LoginUserSuccess
  | LogoutUser
  | LogoutUserSuccess
  | UserError;
