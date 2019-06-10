import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiaglogZnoComponent } from './diaglog-zno.component';

describe('DiaglogZnoComponent', () => {
  let component: DiaglogZnoComponent;
  let fixture: ComponentFixture<DiaglogZnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiaglogZnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiaglogZnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
