import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'messenger';
  loginFormGroup: FormGroup;
  loggedIn = false;
  error;

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      username: this.formBuilder.control(''),
      password: this.formBuilder.control('')
    });
  }

  login(): void {
    this.authService.authenticate(this.loginFormGroup.get('username').value, this.loginFormGroup.get('password').value)
      .subscribe(data => {
        if (data.status === 200){
          this.loggedIn = true;
          this.error = null;
        }
      }, err => {
        this.error = err.error.error;
      });
  }

  logout(): void {
    this.loggedIn = false;
  }
}
