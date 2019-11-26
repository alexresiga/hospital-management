export class UserLoginInput {
  username: string;
  password: string;
}

export default class User {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  roles: UserRole[];
}

export enum UserRole {
  ADMIN = 'admin',
  DOCTOR = 'doctor',
  ASSISTENT = 'assistent',
  PATIENT = 'patient',
  USER = 'user'
}
