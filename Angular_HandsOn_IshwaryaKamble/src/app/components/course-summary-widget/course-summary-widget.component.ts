import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseService } from '../../services/course.service';
import { IconComponent } from '../../shared/icon/icon.component';

@Component({
  selector: 'app-course-summary-widget',
  standalone: true,
  imports: [CommonModule, IconComponent],
  templateUrl: './course-summary-widget.component.html',
  styleUrl: './course-summary-widget.component.css'
})
export class CourseSummaryWidgetComponent implements OnInit {

  totalCourses = 0;

  // injects the same singleton course service used elsewhere
  constructor(private courseService: CourseService) { }

  ngOnInit(): void {
    this.courseService.getCourses().subscribe(courses => this.totalCourses = courses.length);
  }
}
