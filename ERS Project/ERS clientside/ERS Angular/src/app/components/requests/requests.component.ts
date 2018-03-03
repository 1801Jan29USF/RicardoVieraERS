import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Requests } from '../../beans/requests';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {
  requests: Array<Requests> = [];
  status: 0;
  constructor(private client: HttpClient) { }

  ngOnInit() {
    this.client.get('http://localhost:8080/ERS/requests', { withCredentials: true })
      .subscribe(
        (succ: Array<Requests>) => {
          this.requests = succ;
        },
        (err) => {
          console.log('Failed to retrieve history');
        }
      );
  }
}
