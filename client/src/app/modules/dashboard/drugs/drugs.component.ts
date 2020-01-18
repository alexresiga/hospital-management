import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Drug } from 'src/app/shared/model/Drug';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-drugs',
  templateUrl: './drugs.component.html',
  styleUrls: ['./drugs.component.css', '../common.css']
})
export class DrugsComponent implements OnInit {
  drugs: Observable<Drug[]>;
  drug: Drug = new Drug();

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.drugs = this.getDrugs();
  }

  getDrugs(): Observable<Drug[]> {
    return this.http.get<Drug[]>('http://localhost:8080/api/drugList');
  }

  addDrug() {
    this.http.post('http://localhost:8080/api/drug', this.drug).subscribe(
      () => {
        this.drugs = this.getDrugs();
      },
      () => {}
    );
  }

  deleteDrug(id: any) {
    this.http.delete('http://localhost:8080/api/drug/' + id).subscribe(
      () => {
        this.drugs = this.getDrugs();
      },
      () => {}
    );
  }

  editDrug() {
    this.http.put('http://localhost:8080/api/drug/' + this.drug.id, this.drug).subscribe(
      () => {
        this.drugs = this.getDrugs();
      },
      () => {}
    );
  }

  setDrugId(id: any) {
    this.drug.id = id;
  }
}
