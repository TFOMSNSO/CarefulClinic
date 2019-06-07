import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidenavExportExcelZnoComponent } from './sidenav-export-excel-zno.component';

describe('SidenavExportExcelZnoComponent', () => {
  let component: SidenavExportExcelZnoComponent;
  let fixture: ComponentFixture<SidenavExportExcelZnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidenavExportExcelZnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidenavExportExcelZnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
