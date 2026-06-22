import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';

// acts as a mock backend, replaces the need for json-server
@Injectable({ providedIn: 'root' })
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const courses = [
      { id: 1, name: 'Java', code: 'CS101', credits: 4, gradeStatus: 'passed', icon: 'code' },
      { id: 2, name: 'Python', code: 'CS102', credits: 4, gradeStatus: 'passed', icon: 'code' },
      { id: 3, name: 'Full Stack Development', code: 'CS210', credits: 4, gradeStatus: 'pending', icon: 'layers' },
      { id: 4, name: 'Data Structures and Algorithms', code: 'CS150', credits: 4, gradeStatus: 'passed', icon: 'cpu' },
      { id: 5, name: 'Operating Systems', code: 'CS204', credits: 3, gradeStatus: 'pending', icon: 'cpu' },
      { id: 6, name: 'Database Management Systems', code: 'CS305', credits: 3, gradeStatus: 'passed', icon: 'database' },
      { id: 7, name: 'Angular', code: 'CS220', credits: 3, gradeStatus: 'pending', icon: 'layers' },
      { id: 8, name: 'Communication Skills', code: 'HS101', credits: 2, gradeStatus: 'passed', icon: 'chat' },
      { id: 9, name: 'Artificial Intelligence', code: 'CS401', credits: 4, gradeStatus: 'failed', icon: 'brain' },
      { id: 10, name: 'Generative AI', code: 'CS402', credits: 3, gradeStatus: 'pending', icon: 'sparkle' },
      { id: 11, name: 'Computer Networks', code: 'CS230', credits: 3, gradeStatus: 'pending', icon: 'database' },
      { id: 12, name: 'Cyber Security', code: 'CS403', credits: 3, gradeStatus: 'pending', icon: 'flask' }
    ];

    const enrollments: any[] = [];

    return { courses, enrollments };
  }
}
