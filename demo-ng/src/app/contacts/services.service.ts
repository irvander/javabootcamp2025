import { HttpContextToken, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { LoggerService } from '@my/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NotificationService } from '../common-services';
import { Router } from '@angular/router';

export type OperationType = 'list' | 'add' | 'edit' | 'view' | 'delete';
export const AUTH_REQUIRED = new HttpContextToken<boolean>(() => false);

export abstract class RESTDAOService<T, K> {
  protected baseUrl = environment.apiURL;
  protected http: HttpClient = inject(HttpClient)

  constructor(entity: string, protected option = {}) {
    this.baseUrl += entity;
  }
  query(): Observable<Array<T>> {
    return this.http.get<Array<T>>(this.baseUrl, this.option);
  }
  get(id: K): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}/${id}`, this.option);
  }
  add(item: T): Observable<T> {
    return this.http.post<T>(this.baseUrl, item, this.option);
  }
  change(id: K, item: T): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${id}`, item, this.option);
  }
  remove(id: K): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}/${id}`, this.option);
  }
}

@Injectable({
  providedIn: 'root'
})
export class ContactsDAOService extends RESTDAOService<any, any> {
  constructor() {
    super('contactos');
  }
}

@Injectable({
  providedIn: 'root'
})
export class ContactsViewModelService {
  protected operationType: OperationType = 'list';
  protected contentList: Array<any> = [];
  protected element: any = {};
  protected idOriginal: any = null;
  protected listURL = '/contactos';

  constructor(
    protected notify: NotificationService,
    protected out: LoggerService,
    protected dao: ContactsDAOService,
    protected router: Router) { }

  public get OperationType(): OperationType { return this.operationType; }
  public get ContentList(): Array<any> { return this.contentList; }
  public get Element(): any { return this.idOriginal; }

  public list(): void {
    this.dao.query().subscribe({
      next: data => {
        this.contentList = data;
        this.operationType = 'list';
      },
      error: err => this.handleError(err)
    });
  }

  public add(): void {
    this.element = {};
    this.operationType = 'add';
  }

  public edit(key: any): void {
    this.dao.get(key).subscribe({
      next: data => {
        this.element = data;
        this.idOriginal = key;
        this.operationType = 'edit';
      },
      error: err => this.handleError(err)
    });
  }

  public view(key: any): void {
    this.dao.get(key).subscribe({
      next: data => {
        this.element = data;
        this.operationType = 'view';
      },
      error: err => this.handleError(err)
    });
  }

  public delete(key: any): void {
    if (!window.confirm('¿Seguro?')) { return; }
    this.dao.remove(key).subscribe({
      next: () => this.list(),
      error: err => this.handleError(err)
    });
  }

  public cancel(): void {
    this.clear()
    // this.list();
    this.router.navigateByUrl(this.listURL);
  }

  public send(): void {
    switch (this.operationType) {
      case 'add':
        this.dao.add(this.element).subscribe({
          next: () => this.cancel(),
          error: err => this.handleError(err)
        });
        break;
      case 'edit':
        this.dao.change(this.idOriginal, this.element).subscribe({
          next: () => this.cancel(),
          error: err => this.handleError(err)
        });
        break;
      case 'view':
        this.cancel();
        break;
    }
  }

  clear() {
    this.element = {};
    this.idOriginal = undefined;
    this.contentList = [];
  }

  handleError(err: HttpErrorResponse) {
    let msg = ''
    switch (err.status) {
      case 0: msg = err.message; break;
      case 404: this.router.navigateByUrl('/404.html'); return;
      default:
        msg = `ERROR ${err.status}: ${err.error?.['title'] ??
          err.statusText}.${err.error?.['detail'] ? ` Detalles: ${err.error['detail']}` : ''}`
        break;
    }
    this.notify.add(msg)
  }
}
