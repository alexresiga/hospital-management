import {Component, OnInit} from '@angular/core';
import {Appointment} from '../../../shared/model/Appointment';
import {MatTableDataSource} from '@angular/material';
import {AppointmentsService} from "../../../shared/service/appointments.service";
import {UserService} from "../../auth/shared/user.service";
import {RoomsService} from "../../../shared/service/rooms.service";
import {DoctorsService} from "../../../shared/service/doctors.service";
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
    doctors: User[] =new Array<User>();
    displayedColumns: string[] = ['date', 'room', 'doctor', 'patient', 'status'];
    displayedColumnsDoctor: string[] = ['date', 'room', 'doctor', 'patient', 'status', 'change'];
    dataSource: MatTableDataSource<Appointment>;
    currentUser:User;
    isDoctor:boolean;
    appointment: Appointment = new Appointment();
    isModalShow:boolean = false;

    constructor(private appointmentsService: AppointmentsService, private userService:UserService,
                private roomService: RoomsService, private doctorsService:DoctorsService) {
    }

    hide(){
        // this.isModalShow = false;
        window.location.reload();
    }

    showModal(){
        this.isModalShow = true;
    }

    getAppoinments(): void {
        this.appointmentsService.getMyAppointments(this.currentUser)
            .subscribe(appointments => {
                console.log(appointments);
                this.appointments = appointments.sort((a,b) => a.id - b.id);
                this.dataSource = new MatTableDataSource(this.appointments);
            });
    }

    createAppointment(){
        this.appointment.patient=this.currentUser.id;
        this.appointment.room = this.rooms[0].id.toString();
        this.appointment.approved="pending";
        this.appointment.date = this.appointment.date;
        this.appointmentsService.addAppointment(this.appointment);
        // this.ngOnInit();
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

    getDoctors():void{
        this.doctorsService.getDoctors().subscribe(doctors=>
        {
            console.log(doctors);
            doctors.forEach(doctor =>{
                this.doctors.push(
                    this.users.find(user => user.id == doctor.id)
                );
                console.log(this.doctors);
            })
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
        this.getDoctors();
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
