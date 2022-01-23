import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerFormGroup: FormGroup;
  message;
  error;

  constructor(private userService: UserService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.registerFormGroup = this.formBuilder.group({
      username: this.formBuilder.control(''),
      password: this.formBuilder.control('')
    })
  }

  register(): void {
    this.userService.register(this.registerFormGroup.get('username').value, this.registerFormGroup.get('password').value )
      .subscribe(data => {
        if(data.username){
          this.message = 'Registration is successful';
          this.error = null;
        }
      }, error => {
        this.error = error;
        this.message = null;
      })
  }

}
