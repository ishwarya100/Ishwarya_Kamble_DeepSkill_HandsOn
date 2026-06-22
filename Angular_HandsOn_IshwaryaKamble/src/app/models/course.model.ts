// shape of a single course record used across the app
export interface Course {
  id: number;
  name: string;
  code: string;
  credits: number;
  gradeStatus: 'passed' | 'failed' | 'pending';
  icon: string;
}

// shape of a student profile record
export interface Student {
  id: number;
  name: string;
  email: string;
  password: string;
  gender: 'female' | 'male' | 'unspecified';
  gpa: number;
}

// public view of a student, password left out on purpose
export type StudentProfile = Omit<Student, 'password'>;
