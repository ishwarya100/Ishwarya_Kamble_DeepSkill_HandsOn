import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { CourseService } from '../../services/course.service';
import { AuthService } from '../../services/auth.service';
import { IconComponent } from '../../shared/icon/icon.component';
import { HeroIllustrationComponent } from '../../shared/hero-illustration/hero-illustration.component';
import { StudentProfile } from '../../models/course.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, IconComponent, HeroIllustrationComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit, OnDestroy {

  // static text rendered through interpolation
  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';
  coursesAvailable = 0;

  currentUser$: Observable<StudentProfile | null>;

  constructor(private courseService: CourseService, public authService: AuthService) {
    this.currentUser$ = this.authService.currentUser$;
  }

  ngOnInit(): void {
    // loads the live course count from the shared service
    this.courseService.getCourses().subscribe(courses => this.coursesAvailable = courses.length);
    console.log('HomeComponent initialised — courses loaded');
  }

  ngOnDestroy(): void {
    console.log('HomeComponent destroyed');
  }

  // triggered by the event binding on the enroll now button
  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }

  // maps gender to a graduation emoji, neutral as fallback
  genderEmoji(gender: StudentProfile['gender'] | undefined): string {
    if (gender === 'female') {
      return '👩🏻‍🎓';
    }
    if (gender === 'male') {
      return '👨🏻‍🎓';
    }
    return '🎓';
  }
}