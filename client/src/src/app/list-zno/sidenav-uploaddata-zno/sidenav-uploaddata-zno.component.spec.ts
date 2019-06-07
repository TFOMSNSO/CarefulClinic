import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidenavUploaddataZnoComponent } from './sidenav-uploaddata-zno.component';

describe('SidenavUploaddataZnoComponent', () => {
  let component: SidenavUploaddataZnoComponent;
  let fixture: ComponentFixture<SidenavUploaddataZnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidenavUploaddataZnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidenavUploaddataZnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
