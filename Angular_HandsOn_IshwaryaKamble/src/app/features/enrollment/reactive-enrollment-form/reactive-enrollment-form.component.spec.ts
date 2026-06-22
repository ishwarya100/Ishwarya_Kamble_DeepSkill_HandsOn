import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { provideMockStore } from '@ngrx/store/testing';

import { ReactiveEnrollmentFormComponent } from './reactive-enrollment-form.component';

describe('ReactiveEnrollmentFormComponent', () => {
  let component: ReactiveEnrollmentFormComponent;
  let fixture: ComponentFixture<ReactiveEnrollmentFormComponent>;

  const initialState = {
    course: { courses: [], loading: false, error: null },
    enrollment: { enrolledCourseIds: [] }
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveEnrollmentFormComponent],
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        provideMockStore({ initialState })
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReactiveEnrollmentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should mark courseId invalid when it starts with XX', () => {
    const courseIdCtrl = component.enrollForm.get('courseId');
    courseIdCtrl?.setValue('XX101');
    expect(courseIdCtrl?.errors?.['noCourseCode']).toBeTrue();
  });

  it('should add and remove controls from the additionalCourses array', () => {
    expect(component.additionalCourses.length).toBe(0);
    component.addCourse();
    expect(component.additionalCourses.length).toBe(1);
    component.removeCourse(0);
    expect(component.additionalCourses.length).toBe(0);
  });
});
