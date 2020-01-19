export class UserLoginInput {
  username: string;
  password: string;
}

export class UserSignupInput {
  full_name: string;
  email: string;
  password: string;
}

export default class User {
  id: number;
  username: string;
  full_name: string;
  email: string;
  role: number;
}

export enum UserRole {
  ADMIN = 'admin',
  DOCTOR = 'doctor',
  ASSISTENT = 'assistent',
  PATIENT = 'patient',
  USER = 'user'
}
