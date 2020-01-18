import {Component, ChangeDetectionStrategy, EventEmitter, Output, Input} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserLoginInput} from '../../shared/user.model';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginFormComponent {
  form: FormGroup;
  @Input() errorMessage: string;
  @Output() loginAction = new EventEmitter<UserLoginInput>();

  constructor() {
    this.form = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    const { username, password } = this.form.value;
    console.log(username, password);

    this.loginAction.emit({username, password});
  }

}
