import { TestBed } from '@angular/core/testing';

import { PrescriptionsService } from './prescriptions.service';

describe('PrescriptionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PrescriptionsService = TestBed.get(PrescriptionsService);
    expect(service).toBeTruthy();
  });
});
