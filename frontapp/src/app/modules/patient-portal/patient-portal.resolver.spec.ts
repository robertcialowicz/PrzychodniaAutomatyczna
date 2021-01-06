import { TestBed } from '@angular/core/testing';

import { PatientPortalResolver } from './patient-portal.resolver';

describe('PatientPortalResolversResolver', () => {
  let resolver: PatientPortalResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(PatientPortalResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
