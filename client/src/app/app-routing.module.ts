import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookOverviewComponent } from './pages/books/book-overview/book-overview.component';

const routes: Routes = [
  {
    path: '',
    component: BookOverviewComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
