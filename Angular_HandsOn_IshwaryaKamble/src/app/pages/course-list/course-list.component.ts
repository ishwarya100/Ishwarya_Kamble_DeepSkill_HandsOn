import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { BehaviorSubject, combineLatest, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CourseCardComponent } from '../../components/course-card/course-card.component';
import { CourseSummaryWidgetComponent } from '../../components/course-summary-widget/course-summary-widget.component';
import { IconComponent } from '../../shared/icon/icon.component';
import { Course } from '../../models/course.model';
import { loadCourses } from '../../store/course/course.actions';
import { selectAllCourses, selectCoursesError, selectCoursesLoading } from '../../store/course/course.selectors';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [CommonModule, FormsModule, CourseCardComponent, CourseSummaryWidgetComponent, IconComponent],
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css'
})
export class CourseListComponent implements OnInit {

  // observables sourced from the store, rendered via async pipe
  courses$: Observable<Course[]>;
  isLoading$: Observable<boolean>;
  error$: Observable<string | null>;

  // filtered view of courses$, recomputed whenever the search term changes
  filteredCourses$: Observable<Course[]>;
  private searchTerm$: BehaviorSubject<string>;

  searchTerm = '';
  selectedCourseId: number | null = null;

  constructor(private store: Store, private router: Router, private route: ActivatedRoute) {
    this.searchTerm = this.route.snapshot.queryParamMap.get('search') ?? '';
    this.searchTerm$ = new BehaviorSubject<string>(this.searchTerm);

    this.courses$ = this.store.select(selectAllCourses);
    this.isLoading$ = this.store.select(selectCoursesLoading);
    this.error$ = this.store.select(selectCoursesError);

    // combines the live course list with the live search term
    this.filteredCourses$ = combineLatest([this.courses$, this.searchTerm$]).pipe(
      map(([courses, term]) => {
        const query = term.trim().toLowerCase();
        if (!query) {
          return courses;
        }
        return courses.filter(course =>
          course.name.toLowerCase().includes(query) || course.code.toLowerCase().includes(query)
        );
      })
    );
  }

  ngOnInit(): void {
    // dispatches the action, the effect resolves the http call
    this.store.dispatch(loadCourses());
  }

  // trackBy avoids re-rendering every card when the array reference changes
  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onEnroll(courseId: number): void {
    console.log('Enrolling in course:', courseId);
    this.selectedCourseId = courseId;
  }

  // navigates to the course detail page for the clicked card
  goToDetail(courseId: number): void {
    this.router.navigate(['courses', courseId]);
  }

  // pushes the term into the url, re-filters the visible list
  onSearch(): void {
    this.searchTerm$.next(this.searchTerm);
    this.router.navigate(['courses'], { queryParams: { search: this.searchTerm || null } });
  }
}
