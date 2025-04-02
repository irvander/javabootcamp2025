import { Component, forwardRef, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { ContactsViewModelService } from './services.service';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ErrorMessagePipe, TypeValidator } from 'lib/my-core';
import { ActivatedRoute, ParamMap, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-contacts',
  templateUrl: './tmpl-host.component.html',
  styleUrls: ['./component.component.css'],
  imports: [
    forwardRef(() => ContactsListComponent),
    forwardRef(() => ContactsAddComponent),
    forwardRef(() => ContactsEditComponent),
    forwardRef(() => ContactsViewComponent),
  ],
})
export class ContactsComponent implements OnInit, OnDestroy {
  constructor(protected vm: ContactsViewModelService) { }
  public get VM(): ContactsViewModelService { return this.vm; }
  ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
}

@Component({
  selector: 'app-contacts-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./component.component.css'],
  imports: [RouterLink]
})
export class ContactsListComponent implements OnInit, OnDestroy {
  constructor(protected vm: ContactsViewModelService) { }
  public get VM(): ContactsViewModelService { return this.vm; }
  ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
}

@Component({
  selector: 'app-contacts-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./component.component.css'],
  imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class ContactsAddComponent implements OnInit {
  constructor(protected vm: ContactsViewModelService) { }
  public get VM(): ContactsViewModelService { return this.vm; }
  ngOnInit(): void { }
}

@Component({
  selector: 'app-contacts-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./component.component.css'],
  imports: [FormsModule, TypeValidator, ErrorMessagePipe],
})
export class ContactsEditComponent implements OnInit, OnDestroy {
  private obs$?: Subscription;
  constructor(protected vm: ContactsViewModelService,
    protected route: ActivatedRoute, protected router: Router) { }
  public get VM(): ContactsViewModelService { return this.vm; }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe(
      (params: ParamMap) => {
        const id = parseInt(params?.get('id') ?? '');
        if (id) {
          this.vm.edit(id);
        } else {
          this.router.navigate(['/404.html']);
        }
      });
  }
  ngOnDestroy(): void {
    this.obs$!.unsubscribe();
  }
}

@Component({
  selector: 'app-contacts-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./component.component.css'],
  imports: [DatePipe],
})
export class ContactsViewComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: ContactsViewModelService, protected router: Router) { }
  public get VM(): ContactsViewModelService { return this.vm; }
  ngOnChanges(changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

export const CONTACTS_COMPONENTS = [
  ContactsComponent, ContactsListComponent, ContactsAddComponent,
  ContactsEditComponent, ContactsViewComponent,
];
