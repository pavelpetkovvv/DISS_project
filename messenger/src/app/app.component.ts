import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'messenger';
  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    
  }
  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      username: this.formBuilder.control(''),
      password: this.formBuilder.control('')
    })
  }

  login() {
    sessionStorage.setItem('user', this.formGroup.get('username').value);
  }

  logout() {
    sessionStorage.removeItem('user');
  }

  isLoggedIn() {
    return sessionStorage.getItem('user');
  }
}
