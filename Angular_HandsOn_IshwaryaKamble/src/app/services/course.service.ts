import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map, retry, tap } from 'rxjs/operators';
import { Course } from '../models/course.model';

// base url for the mock backend, served by angular-in-memory-web-api
const API_URL = 'api/courses';

@Injectable({ providedIn: 'root' })
export class CourseService {

  constructor(private http: HttpClient) { }

  // fetches all courses, logs the count, retries on failure
  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(API_URL).pipe(
      map(courses => courses.filter(c => c.credits > 0)),
      tap(courses => console.log('Courses loaded:', courses.length)),
      retry(2),
      catchError(this.handleError)
    );
  }

  // fetches a single course by its id
  getCourseById(id: number): Observable<Course> {
    return this.http.get<Course>(`${API_URL}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  // creates a new course record on the mock backend
  createCourse(course: Omit<Course, 'id'>): Observable<Course> {
    return this.http.post<Course>(API_URL, course).pipe(
      catchError(this.handleError)
    );
  }

  // updates an existing course record
  updateCourse(course: Course): Observable<Course> {
    return this.http.put<Course>(`${API_URL}/${course.id}`, course).pipe(
      catchError(this.handleError)
    );
  }

  // deletes a course record by id
  deleteCourse(id: number): Observable<Course> {
    return this.http.delete<Course>(`${API_URL}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  // central error handler reused by every http call above
  private handleError(err: any) {
    console.error(err);
    return throwError(() => new Error('Failed to load courses. Please try again.'));
  }
}
