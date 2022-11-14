import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookItemComponent } from './book-item.component';
import { Book } from '../../../models/book';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('BookItemComponent', () => {
  let component: BookItemComponent;
  let fixture: ComponentFixture<BookItemComponent>;
  let nativeEl: any;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [BookItemComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(BookItemComponent);
    component = fixture.componentInstance;
    component.book = {
      author: 'author',
      description: 'description',
      genre: 'genre',
      isbn: '432423424',
      pages: 422,
      publisher: 'publisher',
      rating: 4,
      title: 'title',
      uuid: '999432ds-124j24nv-dafa432hhlh',
      year: 'year',
      image: 'image',
    } as Book;

    fixture.detectChanges();
    nativeEl = fixture.debugElement.nativeElement;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have img with correct src', () => {
    const img = nativeEl.querySelector('img');
    expect(img.src).toEqual(component.bookImageUrl);
  });

  it('should render an a tag with correct routerLink', () => {
    const a = nativeEl.querySelector('a');
    expect(a.routerLink).toContain(component.book.uuid);
  });

  it('should render correct heading with classes', () => {
    const heading = nativeEl.querySelector('h3');
    expect(heading.textContent).toEqual(component.book.title);
    expect(heading.classList).toContain('font-bold');
  });

  it('should render correct author', () => {
    const author = nativeEl.querySelector('p');
    expect(author.textContent).toEqual(component.book.author);
  });
});
