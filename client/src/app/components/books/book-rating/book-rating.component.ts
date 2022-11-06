import { Component, Input } from '@angular/core';

@Component({
  selector: 'bowdy-book-rating',
  templateUrl: './book-rating.component.html',
})
export class BookRatingComponent {
  @Input() rating!: number;

  starAmount = Array.from({ length: 5 }, (_, i) => i + 1);

  constructor() {}
}
