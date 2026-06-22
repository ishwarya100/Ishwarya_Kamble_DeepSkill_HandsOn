import { createReducer, on } from '@ngrx/store';
import { enrollInCourse, setEnrolledCourses, unenrollFromCourse } from './enrollment.actions';

// shape of the enrollment feature slice held in the store
export interface EnrollmentState {
  enrolledCourseIds: number[];
}

export const initialEnrollmentState: EnrollmentState = {
  enrolledCourseIds: []
};

export const enrollmentReducer = createReducer(
  initialEnrollmentState,
  on(enrollInCourse, (state, { courseId }) => ({
    enrolledCourseIds: [...new Set([...state.enrolledCourseIds, courseId])]
  })),
  on(unenrollFromCourse, (state, { courseId }) => ({
    enrolledCourseIds: state.enrolledCourseIds.filter(id => id !== courseId)
  })),
  on(setEnrolledCourses, (state, { courseIds }) => ({ enrolledCourseIds: courseIds }))
);
