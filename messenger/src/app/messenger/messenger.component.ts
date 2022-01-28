import { Component, Input, OnInit } from '@angular/core';
import { interval, Subscription } from 'rxjs';
import { MessageService } from '../services/message.service';

@Component({
  selector: 'app-messenger',
  templateUrl: './messenger.component.html',
  styleUrls: ['./messenger.component.scss']
})
export class MessengerComponent implements OnInit {
  @Input()
  recipient = '';
  content = '';
  messages: any[] = new Array<any>();
  contacts = [];
  username: string;
  password: string;
  error;
  selectedChat = 'Select chat';
  subscription: Subscription;

  constructor(private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('user');
    this.password = sessionStorage.getItem('password');
    this.loadContacts();
    const source = interval(5000);
    this.subscription = source.subscribe(_arg => {
        this.loadMessages();
        this.loadContacts();
      });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  submit(): void {
    this.messageService.sendMessage(this.username, this.password, this.recipient, this.content)
      .subscribe(_data => {
        this.messages.push({
          sender: this.username,
          content: this.content
        })
        this.content = '';
        this.error = null;
      }
      , err => this.error = err);
  }

  selectChat(contact: string) {
    this.selectedChat = contact;
    this.recipient = contact;
    this.loadMessages();
  }

  loadMessages() {
    this.messageService.getMessagesFromUser(this.username, this.password, this.recipient).subscribe((data: any[]) => {
      if(data){
        this.messages = data;
      } else {
        this.messages = [];
      }
    });
  }

  loadContacts() {
    this.messageService.getContacts(this.username, this.password).subscribe(data => {
      this.contacts = data;
    });  
  }
}
