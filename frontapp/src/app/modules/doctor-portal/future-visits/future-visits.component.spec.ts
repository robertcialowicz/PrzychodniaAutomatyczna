import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FutureVisitsComponent } from './future-visits.component';

describe('FutureVisitsComponent', () => {
  let component: FutureVisitsComponent;
  let fixture: ComponentFixture<FutureVisitsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FutureVisitsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FutureVisitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
