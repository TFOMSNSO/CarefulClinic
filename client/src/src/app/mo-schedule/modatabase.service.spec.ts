import { TestBed, inject } from '@angular/core/testing';

import { ModatabaseService } from './modatabase.service';

describe('ModatabaseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModatabaseService]
    });
  });

  it('should be created', inject([ModatabaseService], (service: ModatabaseService) => {
    expect(service).toBeTruthy();
  }));
});
