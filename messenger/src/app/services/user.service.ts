import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'server/api/v1';

  constructor(private http: HttpClient) { }

  register(username: string, password: string) {
    return this.http.post(`${this.baseUrl}/users`, { username, password});
  }
}
