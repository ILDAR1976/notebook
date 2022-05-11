import {
  HttpClient,  HttpHeaders,
} from '@angular/common/http';
import {
  Injectable
} from '@angular/core';
import {
  Observable
} from 'rxjs';
import {
  environment
} from 'src/environments/environment';
import {
  tap
} from 'rxjs/operators';
import {
  User
} from '../secret/model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService  {
  private url = "http://localhost:8080/base/rest/admin/users";

  constructor(
    private httpClient: HttpClient
  ) {}

  isAuth(): boolean {
    return true;
  }


  login(user: {
    username: string,
    password: string
  }) : Observable<any> {
    const headers = {'Content-Type':'application/x-www-form-urlencoded'};
    const body = 'username=' + user.username.replace('@','%40') + '&password=' + user.password;
    return this.httpClient.post( `${environment.host}/login`, body, {
      responseType: 'text',
      'headers': headers
    });

  }


  createUser(user: User){
    const myHeaders = new HttpHeaders().set("Content-Type", "application/json");
    return this.httpClient.post<User>(`${environment.host}/user/register`,
        JSON.stringify(user), {headers: myHeaders});
  }


  whoami() : Observable<any> {
    return this.httpClient.post( `${environment.host}/user/whoami`, "", {
      responseType: 'text',
    });

  }

  logout() {}
}
