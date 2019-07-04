import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoSchedule6Component } from './mo-schedule6.component';

describe('MoSchedule6Component', () => {
  let component: MoSchedule6Component;
  let fixture: ComponentFixture<MoSchedule6Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoSchedule6Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoSchedule6Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
