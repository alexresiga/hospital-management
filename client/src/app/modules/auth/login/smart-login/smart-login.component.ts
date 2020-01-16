import { Component, ChangeDetectionStrategy } from '@angular/core';
import {UserLoginInput} from '../../shared/user.model';
import {Store} from '@ngrx/store';
import {LoginUser} from '../../shared/state/user.actions';
import {State} from '../../../../shared/store';
import {selectUserError} from '../../shared/state/user.reducer';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-smart-login',
  template: `
    <app-login-form (loginAction)="loginAction($event)" [errorMessage]="errorMessage$ | async"></app-login-form>
  `,
  styles: [],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SmartLoginComponent {
  errorMessage$: Observable<string>;

  constructor(private store: Store<State>) {
    this.errorMessage$ = this.store.select(selectUserError);
  }

  loginAction(userInput: UserLoginInput) {
    this.store.dispatch(new LoginUser(userInput));
  }

}
