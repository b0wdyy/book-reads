import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthInterceptorService implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    try {
      if (req.url.startsWith(environment.baseUrl)) {
        const token = localStorage.getItem('TOKEN');
        req = req.clone({
          headers: req.headers.set('Authorization', `Bearer ${token}`),
        });
      }

      return next.handle(req);
    } catch (error) {
      return next.handle(req);
    }
  }
}
