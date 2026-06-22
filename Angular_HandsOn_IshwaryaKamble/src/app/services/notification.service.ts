import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

// represents a single toast style notification
export interface Notification {
  id: number;
  message: string;
  type: 'info' | 'success' | 'error';
}

@Injectable()
export class NotificationService {

  // holds the active list of notifications, components subscribe to this
  private notifications$ = new BehaviorSubject<Notification[]>([]);
  private nextId = 1;

  getNotifications() {
    return this.notifications$.asObservable();
  }

  push(message: string, type: Notification['type'] = 'info'): void {
    const current = this.notifications$.value;
    const note: Notification = { id: this.nextId++, message, type };
    this.notifications$.next([...current, note]);
  }

  dismiss(id: number): void {
    this.notifications$.next(this.notifications$.value.filter(n => n.id !== id));
  }
}
