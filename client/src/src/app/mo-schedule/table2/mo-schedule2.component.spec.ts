import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoSchedule2Component } from './mo-schedule2.component';

describe('MoSchedule2Component', () => {
  let component: MoSchedule2Component;
  let fixture: ComponentFixture<MoSchedule2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoSchedule2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoSchedule2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
