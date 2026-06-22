import { TestBed } from '@angular/core/testing';
import { CanActivateFn, provideRouter, Router } from '@angular/router';
import { authGuard } from './auth.guard';
import { AuthService } from '../services/auth.service';

describe('authGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
    TestBed.runInInjectionContext(() => authGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({ providers: [provideRouter([])] });
  });

  it('should allow access when the student is logged in', () => {
    const authService = TestBed.inject(AuthService);
    authService.register('Test Student', 'test@college.edu', 'password1', 'unspecified');
    const result = executeGuard({} as any, {} as any);
    expect(result).toBeTrue();
  });

  it('should redirect home and block access when not logged in', () => {
    const authService = TestBed.inject(AuthService);
    const router = TestBed.inject(Router);
    authService.logout();
    spyOn(router, 'navigate');
    const result = executeGuard({} as any, {} as any);
    expect(result).toBeFalse();
    expect(router.navigate).toHaveBeenCalledWith(['/']);
  });
});
