import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoSchedule5Component } from './mo-schedule5.component';

describe('MoSchedule5Component', () => {
  let component: MoSchedule5Component;
  let fixture: ComponentFixture<MoSchedule5Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoSchedule5Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoSchedule5Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
