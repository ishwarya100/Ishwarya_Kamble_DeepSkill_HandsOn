import { TestBed } from '@angular/core/testing';
import { AuthService } from './auth.service';

describe('AuthService', () => {
  let service: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should log the student in right after registering', () => {
    const result = service.register('Priya Verma', 'priya@college.edu', 'password1', 'female');
    expect(result.success).toBeTrue();
    expect(service.isLoggedIn).toBeTrue();
    expect(service.currentUser?.name).toBe('Priya Verma');
  });

  it('should reject registering the same email twice', () => {
    service.register('Priya Verma', 'priya@college.edu', 'password1', 'female');
    const result = service.register('Priya Two', 'priya@college.edu', 'password2', 'female');
    expect(result.success).toBeFalse();
  });

  it('should log in with correct credentials and reject wrong ones', () => {
    service.register('Rahul Mehta', 'rahul@college.edu', 'secret123', 'male');
    service.logout();

    const badLogin = service.login('rahul@college.edu', 'wrongpass');
    expect(badLogin.success).toBeFalse();
    expect(service.isLoggedIn).toBeFalse();

    const goodLogin = service.login('rahul@college.edu', 'secret123');
    expect(goodLogin.success).toBeTrue();
    expect(service.isLoggedIn).toBeTrue();
  });

  it('should clear the current user on logout', () => {
    service.register('Test Student', 'test@college.edu', 'password1', 'unspecified');
    service.logout();
    expect(service.isLoggedIn).toBeFalse();
    expect(service.currentUser).toBeNull();
  });
});
