import { Component, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Book } from '../../../models/book';

@Component({
  selector: 'bowdy-book-item',
  templateUrl: './book-item.component.html',
})
export class BookItemComponent {
  @Input() book!: Book;

  constructor() { }

  get bookImageUrl() {
    return `${environment.baseUrl}/books/image/${this.book.image}`;
  }
}
