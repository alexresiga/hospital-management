export class UserLoginInput {
  username: string;
  password: string;
}

export class UserSignupInput {
  fullName: string;
  email: string;
  password: string;
}

export default class User {
  id: number;
  username: string;
  fullName: string;
  email: string;
  roles: UserRole[];
}

export enum UserRole {
  ADMIN = 'admin',
  DOCTOR = 'doctor',
  ASSISTENT = 'assistent',
  PATIENT = 'patient',
  USER = 'user'
}
