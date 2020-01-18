import {Component, OnInit} from '@angular/core';
import {Appointment} from '../../../shared/model/Appointment';
import {MatTableDataSource} from '@angular/material';
import {AppointmentsService} from "../../../shared/service/appointments.service";
import {UserService} from "../../auth/shared/user.service";
import {RoomsService} from "../../../shared/service/rooms.service";
import {DepartmentsService} from "../../../shared/service/departments.service";
import User from "../../auth/shared/user.model";
import {Room} from "../../../shared/model/Room";


@Component({
    selector: 'appointments',
    templateUrl: './appointments.component.html',
    styleUrls: ['./appointments.component.css', '../common.css']
})
export class AppointmentsComponent implements OnInit {


    appointments: Appointment[];
    users : User[];
    rooms:Room[];
    displayedColumns: string[] = ['date', 'room', 'doctor', 'patient', 'status'];
    dataSource: MatTableDataSource<Appointment>;
    aux:User;
    isDoctor:boolean;

    constructor(private appointmentsService: AppointmentsService, private userService:UserService,
                private roomService: RoomsService) {
    }

    getAppoinments(): void {
        this.appointmentsService.getMyAppointments()
            .subscribe(appointments => {
                console.log(appointments);
                this.appointments = appointments;
                this.dataSource = new MatTableDataSource(this.appointments);
            });
    }

    getUsers(): void {
        this.userService.getUsers()
            .subscribe(users => {
                this.users = users;
                console.log(users);
            });
    }

    getRooms(): void {
        this.roomService.getRooms()
            .subscribe(rooms => {
                this.rooms = rooms;
                console.log(rooms);
            });
    }

    ngOnInit() {
        this.isDoctor=true;
        this.loadData();
    }

    loadData() {
        this.getUsers();
        this.getRooms();
        this.getAppoinments();
    }

     getUserByID(id: number): string {
         return this.users.filter(d => d.id === id)[0].full_name;
    }

    getRoomByID(id: number): string {
        return this.rooms.filter(d => d.id === id)[0].name;
    }
}
