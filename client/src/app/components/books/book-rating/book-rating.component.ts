import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'bowdy-book-rating',
  templateUrl: './book-rating.component.html',
})
export class BookRatingComponent implements OnInit {
  @Input() rating!: number;

  constructor() { }

  ngOnInit(): void { }
}
