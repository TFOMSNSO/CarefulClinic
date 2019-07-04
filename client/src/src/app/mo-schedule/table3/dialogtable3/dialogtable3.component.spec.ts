import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Dialogtable3Component } from './dialogtable3.component';

describe('Dialogtable3Component', () => {
  let component: Dialogtable3Component;
  let fixture: ComponentFixture<Dialogtable3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Dialogtable3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Dialogtable3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
