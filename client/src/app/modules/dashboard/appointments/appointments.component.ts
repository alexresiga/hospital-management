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
    displayedColumnsDoctor: string[] = ['date', 'room', 'doctor', 'patient', 'status', 'change'];
    dataSource: MatTableDataSource<Appointment>;
    currentUser:User;
    isDoctor:boolean;

    constructor(private appointmentsService: AppointmentsService, private userService:UserService,
                private roomService: RoomsService) {
    }

    getAppoinments(): void {
        this.appointmentsService.getMyAppointments(this.currentUser)
            .subscribe(appointments => {
                console.log(appointments);
                this.appointments = appointments.sort((a,b) => a.id - b.id);
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

    getCurrentUser(){
        this.userService.getCurrentUser().subscribe(user=>
                {   this.currentUser = user;
                    console.log(user);
                    if(this.currentUser.role == 1) {
                        this.isDoctor=true;
                    }
                    else{
                        this.isDoctor = false;
                    }
                    this.getAppoinments();
                })
    }

    loadData() {
        this.getCurrentUser();
        this.getUsers();
        this.getRooms();
    }

    changeStatus(appointment:Appointment){
        console.log(appointment);
        this.appointmentsService.changeStatus(appointment).subscribe(_ => this.ngOnInit());
    }

     getUserByID(id: number): string {
         return this.users.filter(d => d.id === id)[0].full_name;
    }

    getRoomByID(id: number): string {
        return this.rooms.filter(d => d.id === id)[0].name;
    }
}
