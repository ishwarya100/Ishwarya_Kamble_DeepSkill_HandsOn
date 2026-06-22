import { createAction, props } from '@ngrx/store';

// dispatched when the student clicks enroll on a course card
export const enrollInCourse = createAction('[Enrollment] Enroll In Course', props<{ courseId: number }>());

// dispatched when the student clicks unenroll on a course card
export const unenrollFromCourse = createAction('[Enrollment] Unenroll From Course', props<{ courseId: number }>());

// dispatched to replace the whole enrolled id list at once
export const setEnrolledCourses = createAction('[Enrollment] Set Enrolled Courses', props<{ courseIds: number[] }>());
