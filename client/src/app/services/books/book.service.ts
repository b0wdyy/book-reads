import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Book } from '../../models/book';

@Injectable({
  providedIn: 'root',
})
export class BookService {
  constructor(private http: HttpClient) { }

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${environment.baseUrl}/books`);
  }

  getBookByUuid(uuid: string) {
    return this.http.get<Book>(`${environment.baseUrl}/books/${uuid}`);
  }
}
