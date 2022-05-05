import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { UserService } from 'src/app/service/user.service'

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  flag: any;
  

  constructor(
    private httpClient: HttpClient,
    private serv: UserService
  ) { }

  ngOnInit(): void {
    this.httpClient.get(`${environment.host}/secret`, {responseType: 'text'})
    .subscribe(res => {
      res = String(res);
      console.log(res);
      this.flag = this.serv.getUsers();
    })
  }

}
