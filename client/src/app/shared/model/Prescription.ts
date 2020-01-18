import {User} from "./User";
import {Drug} from "./Drug";

export class Prescription {
    id: number;
    doctor: number;
    patient: number;
    note: string;
    drugs: Array<number>;
}