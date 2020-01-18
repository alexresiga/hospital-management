import {Room} from "./Room";

export class Appointment {
    id: number;
    patient: number;
    doctor: number;
    date: Date;
    room: Room;
}