import { TestBed, inject } from '@angular/core/testing';

import { SidenaveSearchService } from './sidenave-search.service';

describe('SidenaveSearchService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SidenaveSearchService]
    });
  });

  it('should be created', inject([SidenaveSearchService], (service: SidenaveSearchService) => {
    expect(service).toBeTruthy();
  }));
});
