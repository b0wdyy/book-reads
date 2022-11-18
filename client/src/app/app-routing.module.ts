import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookDetailComponent } from './pages/books/book-detail/book-detail.component';
import { BookOverviewComponent } from './pages/books/book-overview/book-overview.component';
import { PageNotFoundComponent } from './pages/layouts/page-not-found/page-not-found.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'books',
    pathMatch: 'full',
  },
  {
    path: 'books',
    component: BookOverviewComponent,
    children: [
      {
        path: 'books/:bookId',
        component: BookDetailComponent,
      },
    ],
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./pages/auth/auth.module').then(module => module.AuthModule),
  },
  {
    path: 'admin',
    loadChildren: () =>
      import('./pages/admin/admin.module').then(module => module.AdminModule),
  },
  {
    path: '**',
    pathMatch: 'full',
    component: PageNotFoundComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
