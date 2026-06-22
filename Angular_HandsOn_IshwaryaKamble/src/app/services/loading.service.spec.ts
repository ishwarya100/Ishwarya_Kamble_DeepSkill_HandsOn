import { TestBed } from '@angular/core/testing';
import { LoadingService } from './loading.service';

describe('LoadingService', () => {
  let service: LoadingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoadingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should toggle the loading state on show and hide', () => {
    let current = false;
    service.isLoading$.subscribe(value => current = value);
    service.show();
    expect(current).toBeTrue();
    service.hide();
    expect(current).toBeFalse();
  });
});
