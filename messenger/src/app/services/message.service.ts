import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  
  baseUrl = '/server/api/v1';

  constructor(private http: HttpClient) { }

  sendMessage(username: string, password: string, recipient: string, content: string) {
    return this.http.post(`${this.baseUrl}/messages?username=${username}&password=${password}`, {recipient, content})
  }

  getMessagesFromUser(username: string, password: string, recipient: string) {
    return this.http.get(`${this.baseUrl}/messages/conversation/${recipient}?username=${username}&password=${password}`)
  }

  getContacts(username: string, password: string): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/messages/contacts?username=${username}&password=${password}`);
  }
}
