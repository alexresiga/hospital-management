import {Component, OnInit} from '@angular/core';
import {Room} from "../../../shared/model/Room";
import {Department} from "../../../shared/model/Department";
import {MatTableDataSource} from "@angular/material";
import {RoomsService} from "../../../shared/service/rooms.service";
import {DepartmentsService} from "../../../shared/service/departments.service";


@Component({
    selector: 'app-departments-and-rooms',
    templateUrl: './departments-and-rooms.component.html',
    styleUrls: ['./departments-and-rooms.component.css', '../common.css']
})
export class DepartmentsAndRoomsComponent implements OnInit {

    rooms: Room[];
    departments: Department[];
    displayedColumns: string[] = ["name", "level", "department", "update"];
    displayedColumnsDept: string[] = ["nameDept", "updateDept"];
    dataSource: MatTableDataSource<Room>;
    dataSourceDept: MatTableDataSource<Department>;
    toUpdate: Room;
    toUpdateDept: Department;

    getRooms(): void {
        this.roomsService.getRooms()
            .subscribe(clients => {
                this.rooms = clients;
                this.dataSource = new MatTableDataSource(this.rooms);
            });

    }

    getDepartments(): void {
        this.departmentsService.getDepartments()
            .subscribe(depts => {
                this.departments = depts;
                this.dataSourceDept = new MatTableDataSource(this.departments);
            });

    }

    constructor(private roomsService: RoomsService,
                private departmentsService: DepartmentsService) {
    }

    ngOnInit() {
        this.getDepartments();
        this.getRooms();
    }

    addRoom(name: string, department: number, level: string): void {
        name = name.trim();
        level = level.trim();
        if (!name || !level || !department) {
            return;
        }
        // @ts-ignore
        this.rooms.push({department, name, level} as Room);
        // @ts-ignore
        this.roomsService.addRoom({department, name, level} as Room).subscribe(_ => this.ngOnInit());
    }

    addDept(name: string): void {
        name = name.trim();
        if (!name) {
            return;
        }
        // @ts-ignore
        this.departments.push({name} as Department);
        // @ts-ignore
        this.departmentsService.add({name} as Department).subscribe(_ => this.ngOnInit());
    }

    delete(room: Room): void {
        this.rooms = this.rooms.filter(c => c !== room);
        this.roomsService.deleteRoom(room).subscribe(_ => {
            this.getRooms();
        });
    }

    deleteDept(dept: Department) {
        this.departments = this.departments.filter(d => d !== dept);
        this.departmentsService.delete(dept).subscribe(_ => {
            this.getDepartments();
        });
    }

    update(room: Room): void {
        this.roomsService.getRoomByID(room.id).subscribe(room => this.toUpdate = room);
    }

    updateTableDept(dept: Department): void {
        this.departmentsService.getDepartmentByID(dept.id).subscribe(dept => this.toUpdateDept = dept);
    }

    updateRoom(id: number, name: string, department: number, level: string): void {
        name = name.trim();
        level = level.trim();
        if (!name || !level) {
            return;
        }
        // @ts-ignore
        this.roomsService.updateRoom({id, department, name, level} as Room).subscribe(_ => {
            this.ngOnInit();
            this.toUpdate = null;
        });
    }
    updateDept(id: number, name: string): void {
        name = name.trim();

        if (!name) {
            return;
        }
        // @ts-ignore
        this.departmentsService.updateDepartment({id, name} as Department).subscribe(_ => {
            this.ngOnInit();
            this.toUpdateDept = null;
        });
    }

    getDeptByID(id: number): string {
        return this.departments.filter(d => d.id === id)[0].name;
    }

}
