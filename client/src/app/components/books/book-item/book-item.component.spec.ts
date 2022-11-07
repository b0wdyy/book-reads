import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookItemComponent } from './book-item.component';
import {Book} from "../../../models/book";
import {NO_ERRORS_SCHEMA} from "@angular/core";

describe('BookItemComponent', () => {
  let component: BookItemComponent;
  let fixture: ComponentFixture<BookItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [ BookItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookItemComponent);
    component = fixture.componentInstance;
    component.book = {

    } as Book
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
