import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-newrequest',
  templateUrl: './newrequest.component.html',
  styleUrls: ['./newrequest.component.css']
})
export class NewrequestComponent implements OnInit {
  newRequest = {
    amount: null,
    type_id: null,
    description: ''
  };
  constructor(private client: HttpClient, private router: Router) { }

  ngOnInit() {
  }

  submitRequest() {
    this.client.post(`http://localhost:8080/ERS/requests`, this.newRequest, { withCredentials: true })
      .subscribe(
        (succ) => {
          // alert('Submitted!');
          this.router.navigateByUrl('/requests');
        },
        (err) => {
          // alert('Failed to submit. You are now logged out');
          this.router.navigateByUrl('/login');
        }
      );

  }
}
