import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {UserService} from '../user.service';
import {
  GetCurrentUser,
  GetCurrentUserSuccess, UserError,
  LoginUser,
  LoginUserSuccess,
  LogoutUser, LogoutUserSuccess,
  UserActionsTypes
} from './user.actions';
import {catchError, map, switchMap} from 'rxjs/operators';
import {of} from 'rxjs';
import {Router} from '@angular/router';

@Injectable()
export class UserEffects {
  constructor(
    private actions$: Actions,
    private userService: UserService,
    private router: Router
  ) {}

  @Effect()
  getCurrentUser$ = this.actions$.pipe(
    ofType<GetCurrentUser>(UserActionsTypes.GET_CURRENT_USER),
    switchMap(() => this.userService.getCurrentUser()),
    map(action => new GetCurrentUserSuccess(action)),
    catchError(error => of(new UserError(error)))
  );

  @Effect()
  loginUser$ = this.actions$.pipe(
    ofType<LoginUser>(UserActionsTypes.LOGIN_USER),
    switchMap(action => this.userService.login(action.payload)),
    map(result => new LoginUserSuccess(result)),
    catchError(error => of(new UserError(error)))
  );

  @Effect()
  loginUserSuccess$ = this.actions$.pipe(
    ofType<LoginUserSuccess>(UserActionsTypes.LOGIN_USER_SUCCESS),
    switchMap(() => this.router.navigate([''])),
  );

  @Effect()
  logoutUser$ = this.actions$.pipe(
    ofType<LogoutUser>(UserActionsTypes.LOGOUT_USER),
    switchMap(() => this.userService.logout()),
    map(() => new LogoutUserSuccess()),
    catchError(error => of(new UserError(error)))
  );

  @Effect()
  logoutUserSuccess$ = this.actions$.pipe(
    ofType<LogoutUserSuccess>(UserActionsTypes.LOGOUT_USER_SUCCESS),
    switchMap(() => this.router.navigate([''])),
  );
}