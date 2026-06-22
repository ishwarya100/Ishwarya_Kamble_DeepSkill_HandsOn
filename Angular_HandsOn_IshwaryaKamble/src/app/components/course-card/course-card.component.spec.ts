import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { CourseCardComponent } from './course-card.component';
import { Course } from '../../models/course.model';

describe('CourseCardComponent', () => {
  let component: CourseCardComponent;
  let fixture: ComponentFixture<CourseCardComponent>;

  const mockCourse: Course = { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed', icon: 'cpu' };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseCardComponent],
      providers: [
        provideMockStore({ initialState: { enrollment: { enrolledCourseIds: [] } } }),
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseCardComponent);
    component = fixture.componentInstance;
    component.course = mockCourse;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the course name from the input', () => {
    const heading = fixture.debugElement.query(By.css('h3')).nativeElement;
    expect(heading.textContent).toContain('Data Structures');
  });

  it('should emit enrollRequested with the course id on enroll click', () => {
    spyOn(component.enrollRequested, 'emit');
    const button = fixture.debugElement.query(By.css('button'));
    button.nativeElement.click();
    fixture.detectChanges();
    expect(component.enrollRequested.emit).toHaveBeenCalledWith(1);
  });

  it('should log the previous and current course on ngOnChanges', () => {
    spyOn(console, 'log');
    component.ngOnChanges({ course: { previousValue: null, currentValue: mockCourse, firstChange: true, isFirstChange: () => true } });
    expect(console.log).toHaveBeenCalled();
  });

  it('should toggle the expanded state on showDetails click', () => {
    expect(component.isExpanded).toBeFalse();
    component.toggleDetails();
    expect(component.isExpanded).toBeTrue();
  });
});
