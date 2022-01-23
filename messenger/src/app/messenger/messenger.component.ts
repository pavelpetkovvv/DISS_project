import { Component, OnInit } from '@angular/core';
import Pusher from 'pusher-js';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-messenger',
  templateUrl: './messenger.component.html',
  styleUrls: ['./messenger.component.scss']
})
export class MessengerComponent implements OnInit {
  username = 'username';
  message = '';
  messages = [];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    Pusher.logToConsole = true;

    const pusher = new Pusher('25291c0752d6089a660c', {
      cluster: 'eu'
    });

    const channel = pusher.subscribe('chat');
    channel.bind('message', data => {
      this.messages.push(data);
    });
  }

  submit(): void {
    this.http.post('http://localhost:8080/api/v1/message', {
      
    })
  }
}
