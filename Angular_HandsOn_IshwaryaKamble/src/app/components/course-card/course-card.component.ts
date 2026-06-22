import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Store } from '@ngrx/store';
import { Course } from '../../models/course.model';
import { EnrollmentService } from '../../services/enrollment.service';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';
import { HighlightDirective } from '../../directives/highlight.directive';
import { IconComponent } from '../../shared/icon/icon.component';
import { enrollInCourse, unenrollFromCourse } from '../../store/enrollment/enrollment.actions';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [CommonModule, CreditLabelPipe, HighlightDirective, IconComponent],
  templateUrl: './course-card.component.html',
  styleUrl: './course-card.component.css'
})
export class CourseCardComponent implements OnInit, OnChanges {

  // course data passed down from the parent list component
  @Input() course!: Course;

  // emits the selected course id when enroll is clicked
  @Output() enrollRequested = new EventEmitter<number>();

  isExpanded = false;
  enrolledIds: number[] = [];

  constructor(private enrollmentService: EnrollmentService, private store: Store) { }

  ngOnInit(): void {
    // keeps the local enrolled ids in sync with the store
    this.store.select(selectEnrolledIds).subscribe(ids => this.enrolledIds = ids);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course']) {
      console.log('Course changed from', changes['course'].previousValue, 'to', changes['course'].currentValue);
    }
  }

  // computed classes, kept in a getter for a clean template
  get cardClasses() {
    return {
      'card--enrolled': this.isEnrolled,
      'card--full': this.course.credits >= 4,
      expanded: this.isExpanded
    };
  }

  // border colour driven by the grade status of the course
  get borderStyle() {
    const colorMap: Record<string, string> = { passed: '#4f7a52', failed: '#c2447a', pending: '#5b4b8a' };
    return { borderLeftColor: colorMap[this.course.gradeStatus] };
  }

  get isEnrolled(): boolean {
    return this.enrolledIds.includes(this.course.id);
  }

  toggleDetails(): void {
    this.isExpanded = !this.isExpanded;
  }

  // toggles enrollment in both the service and the store
  onEnrollClick(): void {
    if (this.isEnrolled) {
      this.enrollmentService.unenroll(this.course.id);
      this.store.dispatch(unenrollFromCourse({ courseId: this.course.id }));
    } else {
      this.enrollmentService.enroll(this.course.id);
      this.store.dispatch(enrollInCourse({ courseId: this.course.id }));
      this.enrollRequested.emit(this.course.id);
    }
  }
}
