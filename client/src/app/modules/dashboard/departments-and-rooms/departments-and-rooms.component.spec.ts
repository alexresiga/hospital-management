import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentsAndRoomsComponent } from './departments-and-rooms.component';

describe('DepartmentsAndRoomsComponent', () => {
  let component: DepartmentsAndRoomsComponent;
  let fixture: ComponentFixture<DepartmentsAndRoomsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DepartmentsAndRoomsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DepartmentsAndRoomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
