import { Routes } from '@angular/router';
import { ContactsListComponent, ContactsAddComponent, ContactsEditComponent, ContactsViewComponent, ContactsComponent } from './contacts';

export const routes: Routes = [{
    path: '', children: [
        { path: '', component: ContactsComponent },
        { path: 'add', component: ContactsAddComponent },
        { path: ':id/edit', component: ContactsEditComponent },
        { path: ':id', component: ContactsViewComponent },
        { path: ':id/:kk', component: ContactsViewComponent },
    ]
},];
