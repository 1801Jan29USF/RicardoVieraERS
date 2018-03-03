import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-view',
  templateUrl: './employee-view.component.html',
  styleUrls: ['./employee-view.component.css']
})
export class EmployeeViewComponent implements OnInit {
  manager = {
    roleId: ''
  };
  constructor(private client: HttpClient, private router: Router) { }

  ngOnInit() {
    this.isManager();
  }

  isManager() {
    this.client.get('http://localhost:8080/ERS/login', { withCredentials: true })
      .subscribe(
        (succ: string) => {
          this.manager.roleId = succ.valueOf();
          console.log('Manager');
        },
        (err) => {
          console.log('Failed to retrieve history');
        }
      );
  }
}
