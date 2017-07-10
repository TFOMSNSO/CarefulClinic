import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProphylacticComponent } from './list-prophylactic.component';

describe('ListProphylacticComponent', () => {
  let component: ListProphylacticComponent;
  let fixture: ComponentFixture<ListProphylacticComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListProphylacticComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListProphylacticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
