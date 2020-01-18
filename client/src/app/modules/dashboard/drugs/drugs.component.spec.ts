/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { DrugsComponent } from './drugs.component';

describe('DrugsComponent', () => {
  let component: DrugsComponent;
  let fixture: ComponentFixture<DrugsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DrugsComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DrugsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
