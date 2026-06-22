import { HttpInterceptorFn } from '@angular/common/http';

// attaches a mock bearer token to every outgoing request
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const cloned = req.clone({
    setHeaders: { Authorization: 'Bearer mock-token-12345' }
  });
  return next(cloned);
};
