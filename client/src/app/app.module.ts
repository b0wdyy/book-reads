import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { PageNotFoundComponent } from './pages/layouts/page-not-found/page-not-found.component';
import { BooksModule } from './pages/books/books.module';
import { LayoutModule } from './components/layouts/layout.module';

@NgModule({
  declarations: [AppComponent, PageNotFoundComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LayoutModule,
    BooksModule,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
