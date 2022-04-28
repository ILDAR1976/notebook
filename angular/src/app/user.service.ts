import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './user';
     
@Injectable()
export class UserService{
     
    private url = "http://localhost:8080/base/rest/admin/users";
    constructor(private http: HttpClient){ }
        
    getUsers(){
        return this.http.get<Array<User>>(this.url);
    }
    
    createUser(user: User){
        const myHeaders = new HttpHeaders().set("Content-Type", "application/json");
        return this.http.post<User>(this.url, JSON.stringify(user), {headers: myHeaders}); 
    }

    updateUser(user: User) {
        const myHeaders = new HttpHeaders().set("Content-Type", "application/json");
        return this.http.put<User>(this.url + "/" + user.id, JSON.stringify(user), {headers:myHeaders});
    }

    deleteUser(id: number|null = null){
     
        return this.http.delete<User>(this.url + '/' + id);
    }
}