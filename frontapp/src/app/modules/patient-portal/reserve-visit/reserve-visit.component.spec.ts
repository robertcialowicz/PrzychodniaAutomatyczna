import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveVisitComponent } from './reserve-visit.component';

describe('ReserveVisitComponent', () => {
  let component: ReserveVisitComponent;
  let fixture: ComponentFixture<ReserveVisitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReserveVisitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReserveVisitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
