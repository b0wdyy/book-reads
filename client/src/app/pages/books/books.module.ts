import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BookItemComponent } from 'src/app/components/books/book-item/book-item.component';
import { BookRatingComponent } from 'src/app/components/books/book-rating/book-rating.component';
import { LayoutModule } from 'src/app/components/layouts/layout.module';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { BookOverviewComponent } from './book-overview/book-overview.component';

@NgModule({
  imports: [CommonModule, RouterModule, LayoutModule],
  declarations: [
    BookItemComponent,
    BookDetailComponent,
    BookOverviewComponent,
    BookRatingComponent,
  ],
  exports: [
    BookItemComponent,
    BookDetailComponent,
    BookOverviewComponent,
    BookRatingComponent,
  ],
})
export class BooksModule {}
