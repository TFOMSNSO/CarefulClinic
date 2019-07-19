import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Historydialog1Component } from './historydialog1.component';

describe('Historydialog1Component', () => {
  let component: Historydialog1Component;
  let fixture: ComponentFixture<Historydialog1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Historydialog1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Historydialog1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
