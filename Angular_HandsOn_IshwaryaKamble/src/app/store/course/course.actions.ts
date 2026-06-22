import { createAction, props } from '@ngrx/store';
import { Course } from '../../models/course.model';

// dispatched to trigger the loading of all courses
export const loadCourses = createAction('[Course] Load Courses');

// dispatched by the effect once courses are fetched successfully
export const loadCoursesSuccess = createAction(
  '[Course] Load Courses Success',
  props<{ courses: Course[] }>()
);

// dispatched by the effect when the http call fails
export const loadCoursesFailure = createAction(
  '[Course] Load Courses Failure',
  props<{ error: string }>()
);
