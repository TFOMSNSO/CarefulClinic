import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoScheduleComponent } from './mo-schedule.component';

describe('MoScheduleComponent', () => {
  let component: MoScheduleComponent;
  let fixture: ComponentFixture<MoScheduleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoScheduleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
