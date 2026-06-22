import { TestBed } from '@angular/core/testing';
import { HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { of } from 'rxjs';
import { authInterceptor } from './auth.interceptor';

describe('authInterceptor', () => {
  const interceptor: HttpInterceptorFn = (req, next) =>
    TestBed.runInInjectionContext(() => authInterceptor(req, next));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });

  it('should attach the mock bearer token to outgoing requests', () => {
    const req = new HttpRequest('GET', 'api/courses');
    const next = jasmine.createSpy('next').and.returnValue(of({}));

    interceptor(req, next).subscribe();

    const sentRequest = next.calls.mostRecent().args[0];
    expect(sentRequest.headers.get('Authorization')).toBe('Bearer mock-token-12345');
  });
});
