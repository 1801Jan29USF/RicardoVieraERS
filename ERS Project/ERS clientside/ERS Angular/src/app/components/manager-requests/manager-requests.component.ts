import { Component, OnInit } from '@angular/core';
import { Requests } from '../../beans/requests';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-manager-requests',
  templateUrl: './manager-requests.component.html',
  styleUrls: ['./manager-requests.component.css']
})
export class ManagerRequestsComponent implements OnInit {
  updateRequest = {
    rId: 0,
    status_id: 0,
  };
  requests: Array<Requests>;
  constructor(private client: HttpClient) { }

  ngOnInit() {

    this.client.get('http://localhost:8080/ERS/update', { withCredentials: true })
      .subscribe(
        (succ: Array<Requests>) => {
          this.requests = succ;
        },
        (err) => {
          console.log('Failed to retrieve pending');
        }
      );
  }

  approveStatus(statusid: number, rId: number) {
    this.updateRequest.rId = rId;
    this.updateRequest.status_id = statusid;
    this.client.post('http://localhost:8080/ERS/update', this.updateRequest, { withCredentials: true })
      .subscribe(
        (succ) => {
          // alert('Approved');
          this.ngOnInit();
        },
        (err) => {
          console.log('Failed to update');
        }
      );
  }

  denyStatus(statusid: number, rId: number) {
    this.updateRequest.rId = rId;
    this.updateRequest.status_id = statusid;
    this.client.post('http://localhost:8080/ERS/update', this.updateRequest, { withCredentials: true })
      .subscribe(
        (succ) => {
          // alert('Denied');
          this.ngOnInit();
        },
        (err) => {
          console.log('Failed to update');
        }
      );
  }

  // updateStatus() {
  //   this.client.post('http://localhost:8080/ERS/update', this.updateRequest, { withCredentials: true })
  //     .subscribe(
  //       (succ) => {
  //         alert('Updated');
  //         this.ngOnInit();
  //       },
  //       (err) => {
  //         console.log('Failed to update');
  //       }
  //     );
  // }
}
