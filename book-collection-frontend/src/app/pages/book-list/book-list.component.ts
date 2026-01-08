import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { debounceTime } from 'rxjs/operators';
import { BookService } from '../../service/book.service';
import { Book } from '../../model/book.model';

@Component({
  standalone: true,
  selector: 'app-book-list',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './book-list.component.html'
})
export class BookListComponent implements OnInit {

  books: Book[] = [];
  searchForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private service: BookService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      search: ['']
    });

    this.loadBooks();

    this.searchForm.get('search')!
      .valueChanges
      .pipe(debounceTime(300))
      .subscribe(value => {
        this.loadBooks(value || '');
      });
  }

  loadBooks(search?: string) {
    this.service.getBooks(search)
      .subscribe(data => this.books = data);
  }

  add() {
    this.router.navigate(['/add']);
  }

  edit(id?: number) {
    this.router.navigate(['/edit', id]);
  }

  delete(id?: number) {
    if (id && confirm('Delete this book?')) {
      this.service.delete(id).subscribe(() => this.loadBooks());
    }
  }
}
