import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/books/book.service';

@Component({
  selector: 'bowdy-book-detail',
  templateUrl: './book-detail.component.html',
})
export class BookDetailComponent implements OnInit {
  book$!: Observable<Book>;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(p => {
      this.book$ = this.bookService.getBookByUuid(p.get('bookId') as string);
    });
  }
}
