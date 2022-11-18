import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PageNotFoundComponent } from './pages/layouts/page-not-found/page-not-found.component';
import { BooksModule } from './pages/books/books.module';
import { LayoutModule } from './components/layouts/layout.module';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptorService } from './services/auth/auth-interceptor.service';

@NgModule({
  declarations: [AppComponent, PageNotFoundComponent],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LayoutModule,
    BooksModule,
  ],
  bootstrap: [AppComponent],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true,
    },
  ],
})
export class AppModule { }
