import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../../../models/book';
import { BookService } from '../../../services/books/book.service';

@Component({
  selector: 'book-overview',
  templateUrl: './book-overview.component.html',
  styleUrls: ['./book-overview.component.scss'],
})
export class BookOverviewComponent implements OnInit {
  books$!: Observable<Book[]>;
  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.books$ = this.bookService.getBooks();
  }
}
