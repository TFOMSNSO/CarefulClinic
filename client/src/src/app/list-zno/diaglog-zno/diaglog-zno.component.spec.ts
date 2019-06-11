import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogZnoComponent } from './dialog-zno.component';

describe('DialogZnoComponent', () => {
  let component: DialogZnoComponent;
  let fixture: ComponentFixture<DialogZnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogZnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogZnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
