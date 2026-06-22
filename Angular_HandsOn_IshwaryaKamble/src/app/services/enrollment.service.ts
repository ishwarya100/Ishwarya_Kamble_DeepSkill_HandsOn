import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { CourseService } from './course.service';
import { Course } from '../models/course.model';

@Injectable({ providedIn: 'root' })
export class EnrollmentService {

  // tracks ids of courses the student has enrolled into
  private enrolledCourseIds: number[] = [];

  // injects course service to resolve ids into course objects
  constructor(private courseService: CourseService) { }

  enroll(courseId: number): void {
    if (!this.enrolledCourseIds.includes(courseId)) {
      this.enrolledCourseIds.push(courseId);
    }
  }

  unenroll(courseId: number): void {
    this.enrolledCourseIds = this.enrolledCourseIds.filter(id => id !== courseId);
  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCourseIds.includes(courseId);
  }

  // resolves enrolled ids to full course objects via course service
  getEnrolledCourses(): Observable<Course[]> {
    return this.courseService.getCourses().pipe(
      switchMap(courses =>
        [courses.filter(c => this.enrolledCourseIds.includes(c.id))]
      )
    );
  }

  // simulates fetching students enrolled in a given course
  getStudentsByCourse(courseId: number): Observable<string[]> {
    return new Observable(observer => {
      observer.next(['Aarav Sharma', 'Priya Verma']);
      observer.complete();
    });
  }
}
