import { TestBed } from '@angular/core/testing';
import { HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { provideRouter, Router } from '@angular/router';
import { throwError } from 'rxjs';
import { errorHandlerInterceptor } from './error-handler.interceptor';

describe('errorHandlerInterceptor', () => {
  const interceptor: HttpInterceptorFn = (req, next) =>
    TestBed.runInInjectionContext(() => errorHandlerInterceptor(req, next));

  beforeEach(() => {
    TestBed.configureTestingModule({ providers: [provideRouter([])] });
  });

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });

  it('should redirect to home on a 401 response', () => {
    const router = TestBed.inject(Router);
    spyOn(router, 'navigate');
    const req = new HttpRequest('GET', 'api/courses');
    const next = () => throwError(() => ({ status: 401 }));

    interceptor(req, next).subscribe({ error: () => {} });

    expect(router.navigate).toHaveBeenCalledWith(['/']);
  });
});
