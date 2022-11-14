import { TestBed } from '@angular/core/testing';
import { BookService } from './book.service';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { Book } from '../../models/book';
import { environment } from '../../../environments/environment';

describe('BookServiceService', () => {
  let service: BookService;
  let httpMock: HttpTestingController;

  const MOCK_DATA: Book[] = [
    {
      author: 'author',
      description: 'description',
      genre: 'genre',
      isbn: '42434',
      pages: 422,
      publisher: 'publisher',
      rating: 4,
      title: 'title',
      uuid: '3344-44422-4444',
      year: 'year',
      image: 'image',
    },
    {
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
    },
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(BookService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getBooks()', () => {
    it('should get all books', () => {
      service.getBooks().subscribe(books => {
        expect(books.length).toHaveLength(MOCK_DATA.length);
        expect(books.length).not.toHaveLength(3);
      });

      const request = httpMock.expectOne(`${environment.baseUrl}/books`);
      request.flush(MOCK_DATA);
      expect(request.request.method).toEqual('GET');
    });
  });

  describe('getBookByUuid()', () => {
    it('should get book by uuid', () => {
      const [book, bookTwo] = MOCK_DATA;

      service.getBookByUuid(book.uuid).subscribe(fetchedBook => {
        expect(fetchedBook.uuid).toBe(book.uuid);
        expect(fetchedBook.title).toEqual(book.title);
        expect(fetchedBook.title).not.toEqual(bookTwo.title);
      });

      const request = httpMock.expectOne(
        `${environment.baseUrl}/books/${book.uuid}`
      );
      request.flush(book);
      expect(request.request.method).toEqual('GET');
    });
  });
});
