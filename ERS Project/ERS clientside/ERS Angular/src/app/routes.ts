import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RequestsComponent } from './components/requests/requests.component';
import { EmployeeViewComponent } from './components/employee-view/employee-view.component';
import { NewrequestComponent } from './components/newrequest/newrequest.component';
import { HomeComponent } from './components/home/home.component';
import { ManagerRequestsComponent } from './components/manager-requests/manager-requests.component';



export const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'requests',
    component: RequestsComponent
  },
  {
    path: 'manager-requests',
    component: ManagerRequestsComponent,

  },
  {
    path: 'employee-view',
    component: EmployeeViewComponent
  },
  {
    path: 'newrequest',
    component: NewrequestComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: 'home'
  }
];
