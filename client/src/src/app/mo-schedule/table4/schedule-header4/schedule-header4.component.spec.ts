import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleHeader4Component } from './schedule-header4.component';

describe('ScheduleHeader4Component', () => {
  let component: ScheduleHeader4Component;
  let fixture: ComponentFixture<ScheduleHeader4Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScheduleHeader4Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScheduleHeader4Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
