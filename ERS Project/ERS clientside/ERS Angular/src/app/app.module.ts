import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { UIRouterModule } from '@uirouter/angular';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClient, HttpClientJsonpModule, HttpClientModule } from '@angular/common/http';
import {AppComponent} from './app.component';
import {NavComponent} from './nav/nav.component';
import { appRoutes } from './routes';
import { LoginComponent } from './components/login/login.component';
import { RequestsComponent } from './components/requests/requests.component';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { LoggedInGuard } from './guards/logged-in.guard';
import { EmployeeViewComponent } from './components/employee-view/employee-view.component';
import { NewrequestComponent } from './components/newrequest/newrequest.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { ManagerRequestsComponent } from './components/manager-requests/manager-requests.component';
import { FilterByStatusPipe } from './pipes/filter-by-status.pipe';


@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(appRoutes, { useHash: true }),
    FormsModule
  ],
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    RequestsComponent,
    EmployeeViewComponent,
    NewrequestComponent,
    HomeComponent,
    FooterComponent,
    ManagerRequestsComponent,
    FilterByStatusPipe
   ],
  providers: [
    CookieService,
    LoggedInGuard,
   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
