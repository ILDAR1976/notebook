import {
  HttpClient
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
export class AdminService {

  constructor(
    private httpClient: HttpClient
  ) {}

  // interesting API request
  private getAdminRole() {
      this.httpClient.get(`${environment.host}/admin/get`);
  }
}
