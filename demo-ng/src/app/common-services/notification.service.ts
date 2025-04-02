import { Injectable, OnDestroy } from '@angular/core';
import { LoggerService } from '@my/core';
import { Subject } from 'rxjs';

export enum NotificationType { error = 'error', warn = 'warn', info = 'info', log = 'log' }

export class Notification {
  constructor(private id: number, private message: string,
    private type: NotificationType) { }
  public get Id() { return this.id; }
  public get Message() { return this.message; }
  public get Type() { return this.type; }
}

@Injectable({
  providedIn: 'root'
})
export class NotificationService implements OnDestroy {
  public readonly NotificationType = NotificationType;
  private notifications: Notification[] = [];
  private notification$ = new Subject<Notification>();

  constructor(private out: LoggerService) { }

  ngOnDestroy(): void {
    this.notification$.complete()
  }

  public get notificationList(): Notification[] { return Object.assign([], this.notifications); }
  public get areThereNotifications() { return this.notifications.length > 0; }
  public get Notification() { return this.notification$; }

  public add(msg: string, type: NotificationType = NotificationType.error) {
    if (!msg || msg === '') {
      this.out.error('Falta el mensaje de notificación.');
      return;
    }
    const id = this.areThereNotifications ?
      (this.notifications[this.notifications.length - 1].Id + 1) : 1;
    const n = new Notification(id, msg, type);
    this.notifications.push(n);
    this.notification$.next(n);
    // Redundancia: Los errores también se muestran en consola
    if (type === NotificationType.error) {
      this.out.error(`NOTIFICATION: ${msg}`);
    }
  }

  public remove(index: number) {
    if (index < 0 || index >= this.notifications.length) {
    this.out.error('Index out of range.');
    return;
    }
    this.notifications.splice(index, 1);
  }

  public clear() {
    if (this.areThereNotifications)
    this.notifications.splice(0);
  }
}