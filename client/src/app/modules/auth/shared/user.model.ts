export class UserLoginInput {
  username: string;
  password: string;
}

export default class User {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  permissions: UserPermissions[];
}

export enum UserPermissions {
  ADMIN = 'admin',
  DOCTOR = 'doctor',
  ASSISTENT = 'assistent',
  PATIENT = 'patient',
  USER = 'user'
}
