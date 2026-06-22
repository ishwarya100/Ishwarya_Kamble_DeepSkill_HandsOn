import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CourseListComponent } from './pages/course-list/course-list.component';
import { CourseDetailComponent } from './pages/course-detail/course-detail.component';
import { CoursesLayoutComponent } from './pages/courses-layout/courses-layout.component';
import { StudentProfileComponent } from './pages/student-profile/student-profile.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', component: HomeComponent },

  // public auth routes, registering or logging in
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },

  // nested routes, list and detail share the courses layout
  {
    path: 'courses',
    component: CoursesLayoutComponent,
    children: [
      { path: '', component: CourseListComponent },
      { path: ':id', component: CourseDetailComponent }
    ]
  },

  // protected route, only reachable when the auth guard allows it
  { path: 'profile', component: StudentProfileComponent, canActivate: [authGuard] },

  // lazy loaded feature, bundle downloads only when visited
  {
    path: 'enroll',
    canActivate: [authGuard],
    loadChildren: () => import('./features/enrollment/enrollment.routes').then(m => m.enrollmentRoutes)
  },

  // wildcard route, must always stay last in the list
  { path: '**', component: NotFoundComponent }
];
