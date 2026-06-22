import { TestBed } from '@angular/core/testing';
import { HttpInterceptorFn, HttpRequest, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { loadingInterceptor } from './loading.interceptor';
import { LoadingService } from '../services/loading.service';

describe('loadingInterceptor', () => {
  const interceptor: HttpInterceptorFn = (req, next) =>
    TestBed.runInInjectionContext(() => loadingInterceptor(req, next));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });

  it('should show the spinner during the request and hide it after', () => {
    const loadingService = TestBed.inject(LoadingService);
    const req = new HttpRequest('GET', 'api/courses');
    const next = () => of(new HttpResponse({ status: 200 }));

    let activeDuringCall = false;
    loadingService.isLoading$.subscribe(value => activeDuringCall = activeDuringCall || value);

    interceptor(req, next).subscribe();

    expect(activeDuringCall).toBeTrue();
  });
});
