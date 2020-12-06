import { TestBed } from '@angular/core/testing';

import { PlannedVisitsService } from './resolvers.service';

describe('ResolversService', () => {
  let service: PlannedVisitsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlannedVisitsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
