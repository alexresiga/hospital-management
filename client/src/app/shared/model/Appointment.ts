<<<<<<< HEAD
export class Appointment {
    id: number;
    doctor: number;
    patient: number;
    date : string ;
    room : string;
    approved : string;
=======
import {Room} from "./Room";

export class Appointment {
    id: number;
    patient: number;
    doctor: number;
    date: Date;
    room: Room;
>>>>>>> c0916c0ca88541004e345a0a92f0e70bb189f3ff
}