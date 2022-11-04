import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from 'src/app/models/book';

@Injectable({
  providedIn: 'root',
})
export class BookService {
  constructor(private http: HttpClient) {}

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>('http://localhost:8088/api/books');
  }

  getBookByUuid(uuid: string) {
    return this.http.get<Book>(`http://localhost:8088/api/books/${uuid}`);
  }
}
