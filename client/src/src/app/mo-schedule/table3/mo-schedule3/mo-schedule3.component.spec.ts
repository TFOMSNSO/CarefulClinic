import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoSchedule3Component } from './mo-schedule3.component';

describe('MoSchedule3Component', () => {
  let component: MoSchedule3Component;
  let fixture: ComponentFixture<MoSchedule3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoSchedule3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoSchedule3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
