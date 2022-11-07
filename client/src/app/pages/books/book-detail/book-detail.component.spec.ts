import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BookDetailComponent} from './book-detail.component';
import {RouterTestingModule} from "@angular/router/testing";
import {BookService} from "../../../services/books/book.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('BookDetailComponent', () => {
  let component: BookDetailComponent;
  let fixture: ComponentFixture<BookDetailComponent>;
  let service: BookService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BookDetailComponent],
      imports: [RouterTestingModule, HttpClientTestingModule]
    })
      .compileComponents();

    fixture = TestBed.createComponent(BookDetailComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(BookService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
