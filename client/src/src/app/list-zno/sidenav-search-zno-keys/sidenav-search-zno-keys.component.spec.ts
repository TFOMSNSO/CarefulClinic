import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidenavSearchZnoKeysComponent } from './sidenav-search-zno-keys.component';

describe('SidenavSearchZnoKeysComponent', () => {
  let component: SidenavSearchZnoKeysComponent;
  let fixture: ComponentFixture<SidenavSearchZnoKeysComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidenavSearchZnoKeysComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidenavSearchZnoKeysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
