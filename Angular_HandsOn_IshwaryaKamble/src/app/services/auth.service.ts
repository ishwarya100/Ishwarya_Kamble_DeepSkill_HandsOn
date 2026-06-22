import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Student, StudentProfile } from '../models/course.model';

// result of register or login, success flag plus message
export interface AuthResult {
  success: boolean;
  message?: string;
}

// in-memory only, students list resets on every page refresh
@Injectable({ providedIn: 'root' })
export class AuthService {

  private students: Student[] = [];
  private nextId = 1;

  private currentUserSubject = new BehaviorSubject<StudentProfile | null>(null);
  currentUser$: Observable<StudentProfile | null> = this.currentUserSubject.asObservable();

  get isLoggedIn(): boolean {
    return this.currentUserSubject.value !== null;
  }

  get currentUser(): StudentProfile | null {
    return this.currentUserSubject.value;
  }

  // creates a new student record, logs them in right away
  register(name: string, email: string, password: string, gender: Student['gender']): AuthResult {
    const emailTaken = this.students.some(s => s.email.toLowerCase() === email.toLowerCase());
    if (emailTaken) {
      return { success: false, message: 'An account with this email already exists.' };
    }

    const newStudent: Student = { id: this.nextId++, name, email, password, gender, gpa: 0 };
    this.students.push(newStudent);
    this.setCurrentUser(newStudent);
    return { success: true };
  }

  // checks credentials against the in-memory student list
  login(email: string, password: string): AuthResult {
    const match = this.students.find(s => s.email.toLowerCase() === email.toLowerCase() && s.password === password);
    if (!match) {
      return { success: false, message: 'Invalid email or password.' };
    }

    this.setCurrentUser(match);
    return { success: true };
  }

  logout(): void {
    this.currentUserSubject.next(null);
  }

  // strips the password before exposing the student profile
  private setCurrentUser(student: Student): void {
    const { password, ...profile } = student;
    this.currentUserSubject.next(profile);
  }
}
