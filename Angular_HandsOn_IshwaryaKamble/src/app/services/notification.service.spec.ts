import { TestBed } from '@angular/core/testing';
import { NotificationService } from './notification.service';

describe('NotificationService', () => {
  let service: NotificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({ providers: [NotificationService] });
    service = TestBed.inject(NotificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should push and dismiss a notification', () => {
    let latest: any[] = [];
    service.getNotifications().subscribe(list => latest = list);

    service.push('Test message', 'info');
    expect(latest.length).toBe(1);

    service.dismiss(latest[0].id);
    expect(latest.length).toBe(0);
  });
});
