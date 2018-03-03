import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credential = {
    username: '',
    password: '',
  };
  constructor(private client: HttpClient, private router: Router) { }

  ngOnInit() {

  }

  login() {
    this.client.post(`http://localhost:8080/ERS/login`, this.credential, { withCredentials: true })
      .subscribe(
        (succ) => {
          // alert('Logged in!');
          this.router.navigateByUrl('/employee-view');
          document.getElementById('log-out').style.display = 'inline';
          document.getElementById('menu').style.display = 'inline';
        },
        (err) => {
          // alert('failed to log in');
        }
      );

  }
}
