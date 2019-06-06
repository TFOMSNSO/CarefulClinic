import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListZnoComponent } from './list-zno.component';

describe('ListZnoComponent', () => {
  let component: ListZnoComponent;
  let fixture: ComponentFixture<ListZnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListZnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListZnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
