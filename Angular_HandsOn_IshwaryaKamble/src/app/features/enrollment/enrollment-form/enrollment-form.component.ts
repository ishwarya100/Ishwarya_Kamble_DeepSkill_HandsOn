import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { Store } from '@ngrx/store';
import { CourseService } from '../../../services/course.service';
import { EnrollmentService } from '../../../services/enrollment.service';
import { IconComponent } from '../../../shared/icon/icon.component';
import { enrollInCourse } from '../../../store/enrollment/enrollment.actions';

@Component({
  selector: 'app-enrollment-form',
  standalone: true,
  imports: [CommonModule, FormsModule, IconComponent],
  templateUrl: './enrollment-form.component.html',
  styleUrl: './enrollment-form.component.css'
})
export class EnrollmentFormComponent {

  submitted = false;
  courseNotFound = false;

  constructor(
    private courseService: CourseService,
    private enrollmentService: EnrollmentService,
    private store: Store
  ) { }

  // enrolls in the typed course id, creates no fake course
  onSubmit(form: NgForm): void {
    const courseId = Number(form.value.courseId);
    this.courseNotFound = false;

    this.courseService.getCourseById(courseId).subscribe({
      next: () => {
        this.enrollmentService.enroll(courseId);
        this.store.dispatch(enrollInCourse({ courseId }));
        this.submitted = true;
      },
      error: () => {
        this.courseNotFound = true;
      }
    });
  }

  // clears the success banner and resets all validation states
  onReset(form: NgForm): void {
    form.resetForm();
    this.submitted = false;
    this.courseNotFound = false;
  }
}
