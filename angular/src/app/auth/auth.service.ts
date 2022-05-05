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

@Injectable({
  providedIn: 'root'
})
export class AuthService  {

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
    //let head = new HttpHeaders();
    //head.set('Content-Type','application/x-www-form-urlencoded');
    const headers = {'Content-Type':'application/x-www-form-urlencoded'};
    const body = 'username=' + user.username.replace('@','%40') + '&password=' + user.password;
    return this.httpClient.post( `${environment.host}/login`, body, {
      responseType: 'text',
      'headers': headers
    });

  }
//return this.httpClient.post( `${environment.host}/login`, body, {
  logout() {}
}
