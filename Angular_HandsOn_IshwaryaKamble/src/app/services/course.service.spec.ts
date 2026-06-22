import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CourseService } from './course.service';
import { Course } from '../models/course.model';

describe('CourseService', () => {
  let service: CourseService;
  let httpMock: HttpTestingController;

  const mockCourses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed', icon: 'cpu' },
    { id: 2, name: 'Operating Systems', code: 'CS204', credits: 3, gradeStatus: 'pending', icon: 'cpu' }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CourseService]
    });
    service = TestBed.inject(CourseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    // confirms no unexpected outstanding http requests after each test
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch courses from the correct url', () => {
    service.getCourses().subscribe(courses => {
      expect(courses.length).toBe(2);
    });

    const req = httpMock.expectOne('api/courses');
    expect(req.request.method).toBe('GET');
    req.flush(mockCourses);
  });

  it('should propagate an error when the server responds with a 500', () => {
    service.getCourses().subscribe({
      next: () => fail('expected an error, not courses'),
      error: (err) => expect(err.message).toContain('Failed to load courses')
    });

    // retry(2) means the request fires up to 3 times before the error propagates
    for (let attempt = 0; attempt < 3; attempt++) {
      httpMock.expectOne('api/courses').flush('server error', { status: 500, statusText: 'Server Error' });
    }
  });
});
