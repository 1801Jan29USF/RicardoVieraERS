import { Component, } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {
  credential = {
    username: '',
    password: '',
  };
  constructor(private client: HttpClient, private router: Router) {

  }

  logOut() {
    this.client.post(`http://localhost:8080/ERS/login`, this.credential, { withCredentials: true })
      .subscribe(
        (succ) => {

        },
        (err) => {
          // alert('Logging out');
          this.router.navigateByUrl('/home');
          document.getElementById('log-out').style.display = 'none';
          document.getElementById('menu').style.display = 'none';
        }
      );

  }

}
