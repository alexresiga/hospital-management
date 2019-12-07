import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { State } from 'src/app/shared/store';
import { SignupUser } from '../../auth/shared/state/user.actions';
import { Observable } from 'rxjs';
import { selectUserError } from '../../auth/shared/state/user.reducer';

@Component({
    selector: 'app-signup-form',
    templateUrl: './signup-form.component.html',
    styleUrls: ['./signup-form.component.css'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class SignupFormComponent implements OnInit {
    errorMessage$: Observable<string>;
    form: FormGroup;
    isEmailValid = true;
    isPasswordValid = true;
    isPasswordConfirmationValid = true;

    constructor(
        private store: Store<State>
    ) {
        this.errorMessage$ = this.store.select(selectUserError);

        this.form = new FormGroup({
            fullName: new FormControl('', Validators.required),
            email: new FormControl('', [Validators.required, Validators.email]),
            password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(16)]),
            passwordConfirmation: new FormControl('')
        });
    }

    ngOnInit(): void {

        this.form.valueChanges.subscribe(() => {

            const emailControl = this.form.get('email');
            const passwordConfirmationControl = this.form.get('passwordConfirmation');
            const passwordControl = this.form.get('password');

            if (emailControl.value.length > 0) {
                this.isEmailValid = emailControl.valid;
            }

            if (passwordControl.value.length > 0) {
                this.isPasswordValid = passwordControl.valid;
            }

            if (this.isPasswordValid && passwordConfirmationControl.value.length > 0) {
                this.isPasswordConfirmationValid = this.passwordConfirmationValidator();
            }
        });
    }

    passwordConfirmationValidator(): boolean {
        const passwordConfirmationValue = this.form.get('passwordConfirmation').value;
        const currentPasswordValue = this.form.get('password').value;

        if (passwordConfirmationValue === currentPasswordValue) {
           return true;
        }

        return false;
    }


    isFormValid(): boolean {
        return this.form.valid && this.isPasswordConfirmationValid;
    }

    onSubmit(event) {
        event.preventDefault();

        if (this.isFormValid()) {
            this.store.dispatch(new SignupUser({
                fullName: this.form.get('fullName').value,
                email: this.form.get('email').value,
                password: this.form.get('password').value
            }));
        }
    }
}
