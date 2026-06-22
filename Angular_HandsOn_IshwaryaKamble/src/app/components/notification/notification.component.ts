import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationService } from '../../services/notification.service';
import { LoadingService } from '../../services/loading.service';
import { IconComponent } from '../../shared/icon/icon.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [CommonModule, IconComponent],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css',
  // component scoped provider, creates a fresh isolated instance per usage
  providers: [NotificationService]
})
export class NotificationComponent implements OnInit {

  notifications$: ReturnType<NotificationService['getNotifications']>;
  isLoading$: Observable<boolean>;

  constructor(private notificationService: NotificationService, private loadingService: LoadingService) {
    this.notifications$ = this.notificationService.getNotifications();
    this.isLoading$ = this.loadingService.isLoading$;
  }

  ngOnInit(): void {
    this.notificationService.push('Welcome back to the Student Course Portal!', 'info');
  }

  dismiss(id: number): void {
    this.notificationService.dismiss(id);
  }

  // maps a notification type to the icon shown beside it
  iconFor(type: string): string {
    const map: Record<string, string> = { info: 'bell', success: 'check', error: 'warning' };
    return map[type] ?? 'bell';
  }
}
