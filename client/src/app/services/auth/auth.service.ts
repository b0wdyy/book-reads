import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  isLoggedSubject = new BehaviorSubject(false);
  isLoggedIn$ = this.isLoggedSubject.asObservable();
  token = '';

  constructor(private http: HttpClient, private router: Router) { }

  login(username: string, password: string) {
    this.http
      .post<{ token: string; username: string; type: string }>(
        `${environment.baseUrl}/auth/login`,
        {
          username,
          password,
        }
      )
      .subscribe((data: { token: string; username: string; type: string }) => {
        this.isLoggedSubject.next(true);
        this.token = data.token;
        this.isLoggedSubject.next(true);
        this.router.navigateByUrl('/books');
        localStorage.setItem('TOKEN', data.token);
      });
  }
}
