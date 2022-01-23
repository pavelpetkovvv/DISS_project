import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'messenger';
  formGroup: FormGroup;
  user; 

  constructor(private formBuilder: FormBuilder, private router: Router) {
    
  }
  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      username: this.formBuilder.control(''),
      password: this.formBuilder.control('')
    })
  }

  login() {
    sessionStorage.setItem('user', this.formGroup.get('username').value);
    this.router.navigateByUrl('/messenger');
  }

  logout() {
    sessionStorage.removeItem('user');
  }

  isLoggedIn() {
    return sessionStorage.getItem('user');
  }
}
