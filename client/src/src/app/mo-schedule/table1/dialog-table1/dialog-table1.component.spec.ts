import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogTable1Component } from './dialog-table1.component';

describe('DialogTable1Component', () => {
  let component: DialogTable1Component;
  let fixture: ComponentFixture<DialogTable1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogTable1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogTable1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
