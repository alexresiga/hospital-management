import { Component, OnInit, Input } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Drug } from 'src/app/shared/model/Drug';
import { MatTableDataSource } from '@angular/material';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
  withCredentials: true
};

@Component({
  selector: 'app-drugs',
  templateUrl: './drugs.component.html',
  styleUrls: ['./drugs.component.css', '../common.css']
})
export class DrugsComponent implements OnInit {
  drugs: Drug[];
  drug: Drug = new Drug();
  dataSource: MatTableDataSource<Drug>;
  displayedColumns: string[] = ['name', 'edit', 'delete'];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getDrugs();
  }

  getDrugs() {
    this.http.get<Drug[]>('http://localhost:8080/api/drugList', httpOptions).subscribe(
      response => {
        this.drugs = response;
      },
      () => {}
    );
  }

  addDrug() {
    this.http.post('http://localhost:8080/api/drug', this.drug, httpOptions).subscribe(
      () => {
        this.getDrugs();
      },
      () => {}
    );
  }

  deleteDrug(id: any) {
    this.http.delete('http://localhost:8080/api/drug/' + id, httpOptions).subscribe(
      () => {
        this.getDrugs();
      },
      () => {}
    );
  }

  editDrug() {
    this.http.put('http://localhost:8080/api/drug/' + this.drug.id, this.drug, httpOptions).subscribe(
      () => {
        this.getDrugs();
      },
      () => {}
    );
  }

  setDrugId(id: any) {
    this.drug.id = id;
  }
}
