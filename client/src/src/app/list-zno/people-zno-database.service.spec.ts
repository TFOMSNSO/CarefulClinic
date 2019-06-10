import { TestBed, inject } from '@angular/core/testing';

import { PeopleZnoDatabaseService } from './people-zno-database.service';

describe('PeopleZnoDatabaseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PeopleZnoDatabaseService]
    });
  });

  it('should be created', inject([PeopleZnoDatabaseService], (service: PeopleZnoDatabaseService) => {
    expect(service).toBeTruthy();
  }));
});
