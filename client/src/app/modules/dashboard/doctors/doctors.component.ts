import {Component, OnInit} from '@angular/core';
import {User} from '../../../shared/model/User';
import {DoctorInformation} from '../../../shared/model/DoctorInformation';
import {MatTableDataSource} from '@angular/material';
import {UsersService} from '../../../shared/service/users.service';
import {DoctorsService} from '../../../shared/service/doctors.service';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {
  doctors: DoctorInformation[];
  users: User[];
  displayedColumns: string[] = ['name', 'phone_number', 'working_schedule'];
  dataSource: MatTableDataSource<DoctorInformation>;

  getDoctors(): void {
    this.doctorsService.getDoctors()
      .subscribe(doctors => {
        this.doctors = doctors;
        this.dataSource = new MatTableDataSource(this.doctors);
      });
  }

  getDoctorName(doctor_id: number): string {
    for (let user of this.users) {
      if (user.id == doctor_id) return user.full_name;
    }
    return "";
  }

  getDoctorPhoneNumber(doctor_id: number): string {
    for (let user of this.users) {
      if (user.id == doctor_id) return user.phone_number;
    }
    return "";
  }

  getUsers(): void {
    this.usersService.getUsers()
      .subscribe(users => { this.users = users; });
  }

  constructor(private doctorsService: DoctorsService,
              private usersService: UsersService) {}


  ngOnInit() {
    this.getUsers();
    this.getDoctors();
  }
}
