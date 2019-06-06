import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidenavSearchZnoComponent } from './sidenav-search-zno.component';

describe('SidenavSearchZnoComponent', () => {
  let component: SidenavSearchZnoComponent;
  let fixture: ComponentFixture<SidenavSearchZnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidenavSearchZnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidenavSearchZnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
