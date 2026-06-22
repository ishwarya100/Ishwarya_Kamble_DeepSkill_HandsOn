import { createReducer, on } from '@ngrx/store';
import { Course } from '../../models/course.model';
import { loadCourses, loadCoursesFailure, loadCoursesSuccess } from './course.actions';

// shape of the course feature slice held in the store
export interface CourseState {
  courses: Course[];
  loading: boolean;
  error: string | null;
}

export const initialCourseState: CourseState = {
  courses: [],
  loading: false,
  error: null
};

// pure function mapping actions to new immutable state
export const courseReducer = createReducer(
  initialCourseState,
  on(loadCourses, state => ({ ...state, loading: true, error: null })),
  on(loadCoursesSuccess, (state, { courses }) => ({ ...state, courses, loading: false })),
  on(loadCoursesFailure, (state, { error }) => ({ ...state, loading: false, error }))
);
