import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutModule } from './components/layouts/layout.module';
import { HttpClientModule } from '@angular/common/http';
import { BookOverviewComponent } from './pages/books/book-overview/book-overview.component';

@NgModule({
  declarations: [AppComponent, BookOverviewComponent],
  imports: [BrowserModule, AppRoutingModule, LayoutModule, HttpClientModule],
  bootstrap: [AppComponent],
})
export class AppModule {}
