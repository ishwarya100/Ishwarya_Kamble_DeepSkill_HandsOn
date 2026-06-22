import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Course, StudentProfile } from '../../models/course.model';
import { AuthService } from '../../services/auth.service';
import { IconComponent } from '../../shared/icon/icon.component';
import { loadCourses } from '../../store/course/course.actions';
import { selectEnrolledCourses } from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-student-profile',
  standalone: true,
  imports: [CommonModule, IconComponent],
  templateUrl: './student-profile.component.html',
  styleUrl: './student-profile.component.css'
})
export class StudentProfileComponent implements OnInit {

  // logged in student, route is guarded so this loads
  currentUser$: Observable<StudentProfile | null>;
  enrolledCourses$: Observable<Course[]>;

  constructor(private store: Store, private authService: AuthService) {
    this.currentUser$ = this.authService.currentUser$;
    this.enrolledCourses$ = this.store.select(selectEnrolledCourses);
  }

  ngOnInit(): void {
    // ensures the course list is loaded so enrolled names resolve
    this.store.dispatch(loadCourses());
  }
}
