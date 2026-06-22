import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  AbstractControl, FormArray, FormBuilder, FormControl, FormGroup,
  ReactiveFormsModule, ValidationErrors, Validators
} from '@angular/forms';
import { Observable, of } from 'rxjs';
import { delay } from 'rxjs/operators';
import { Store } from '@ngrx/store';
import { UnsavedChangesComponent } from '../../../guards/unsaved-changes.guard';
import { IconComponent } from '../../../shared/icon/icon.component';
import { CourseService } from '../../../services/course.service';
import { EnrollmentService } from '../../../services/enrollment.service';
import { enrollInCourse } from '../../../store/enrollment/enrollment.actions';

// custom synchronous validator, rejects course codes starting with XX
function noCourseCode(control: AbstractControl): ValidationErrors | null {
  if (control.value && String(control.value).toUpperCase().startsWith('XX')) {
    return { noCourseCode: true };
  }
  return null;
}

// custom async validator, simulates a server side email availability check
function simulateEmailCheck(control: AbstractControl): Observable<ValidationErrors | null> {
  const taken = String(control.value || '').includes('test@');
  return of(taken ? { emailTaken: true } : null).pipe(delay(800));
}

@Component({
  selector: 'app-reactive-enrollment-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, IconComponent],
  templateUrl: './reactive-enrollment-form.component.html',
  styleUrl: './reactive-enrollment-form.component.css'
})
export class ReactiveEnrollmentFormComponent implements OnInit, UnsavedChangesComponent {

  enrollForm!: FormGroup;
  submitted = false;
  courseNotFound = false;

  constructor(
    private fb: FormBuilder,
    private courseService: CourseService,
    private enrollmentService: EnrollmentService,
    private store: Store
  ) { }

  ngOnInit(): void {
    // form model lives entirely in the component, fully unit-testable
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: this.fb.control('', [Validators.required, Validators.email], [simulateEmailCheck]),
      courseId: [null, [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', Validators.required],
      agreeToTerms: [false, Validators.requiredTrue],
      additionalCourses: this.fb.array([])
    });
  }

  // typed getter, cleaner than casting inside the template every time
  get additionalCourses(): FormArray {
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  addCourse(): void {
    this.additionalCourses.push(new FormControl('', Validators.required));
  }

  removeCourse(index: number): void {
    this.additionalCourses.removeAt(index);
  }

  // used by the unsaved changes guard before leaving this route
  hasUnsavedChanges(): boolean {
    return this.enrollForm.dirty && !this.submitted;
  }

  onSubmit(): void {
    // value excludes disabled controls, getRawValue includes every control
    console.log(this.enrollForm.value);
    console.log(this.enrollForm.getRawValue());

    const courseId = Number(this.enrollForm.value.courseId);
    this.courseNotFound = false;

    this.courseService.getCourseById(courseId).subscribe({
      next: () => {
        this.enrollmentService.enroll(courseId);
        this.store.dispatch(enrollInCourse({ courseId }));
        this.submitted = true;
        this.enrollForm.markAsPristine();
      },
      error: () => {
        this.courseNotFound = true;
      }
    });
  }
}

