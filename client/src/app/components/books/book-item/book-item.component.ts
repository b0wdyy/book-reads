import { Component, Input } from '@angular/core';
import { Book } from 'src/app/models/book';

@Component({
  selector: 'book-item',
  templateUrl: './book-item.component.html',
})
export class BookItemComponent {
  @Input() book!: Book;

  constructor() {}

  get bookImageUrl() {
    return `http://localhost:8088/api/books/image/${this.book.image}`;
  }
}
