import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoSchedule4Component } from './mo-schedule4.component';

describe('MoSchedule4Component', () => {
  let component: MoSchedule4Component;
  let fixture: ComponentFixture<MoSchedule4Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoSchedule4Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoSchedule4Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
