import { createFeatureSelector, createSelector } from '@ngrx/store';
import { CourseState } from './course.reducer';

// selects the whole course feature slice from the store
export const selectCourseState = createFeatureSelector<CourseState>('course');

// memoised selectors derived from the feature slice
export const selectAllCourses = createSelector(selectCourseState, state => state.courses);
export const selectCoursesLoading = createSelector(selectCourseState, state => state.loading);
export const selectCoursesError = createSelector(selectCourseState, state => state.error);
