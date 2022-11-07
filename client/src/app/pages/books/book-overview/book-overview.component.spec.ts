import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookOverviewComponent } from './book-overview.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('BookOverviewComponent', () => {
  let component: BookOverviewComponent;
  let fixture: ComponentFixture<BookOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [BookOverviewComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(BookOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
