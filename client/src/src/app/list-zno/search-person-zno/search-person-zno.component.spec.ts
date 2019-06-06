import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPersonZnoComponent } from './search-person-zno.component';

describe('SearchPersonZnoComponent', () => {
  let component: SearchPersonZnoComponent;
  let fixture: ComponentFixture<SearchPersonZnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchPersonZnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchPersonZnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
