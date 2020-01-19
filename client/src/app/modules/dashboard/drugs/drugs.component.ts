import { Component, OnInit, Input, ViewChild } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Drug } from 'src/app/shared/model/Drug';
import { MatTableDataSource } from '@angular/material';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import * as $ from 'jquery';

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
  @ViewChild('newDrugName', {static: false}) newDrugNameInput;

  constructor(
    private http: HttpClient,
    private modalService: NgbModal
  ) {

  }

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

  addDrug(event) {
    event.preventDefault();

    const drugName: string = this.newDrugNameInput.nativeElement.value;
    console.log(drugName);

    if (drugName.trim()) {
      this.http.post('http://localhost:8080/api/drug', JSON.stringify({name: drugName}), httpOptions).subscribe(
      () => {
        this.getDrugs();
      },
      () => {}
    );
    }

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
        this.drug = new Drug();
        this.getDrugs();
      },
      () => {}
    );
  }

  setDrug(drug: Drug) {
    this.drug = drug;
  }
}
