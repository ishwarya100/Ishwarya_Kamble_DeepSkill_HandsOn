import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

// catches http errors globally and reacts based on status code
export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError(err => {
      if (err.status === 401) {
        router.navigate(['/']);
      }
      if (err.status === 500) {
        console.error('Server error, please try again later.');
      }
      return throwError(() => err);
    })
  );
};
