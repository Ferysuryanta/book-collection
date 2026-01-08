import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../../service/book.service';

@Component({
  standalone: true,
  selector: 'app-book-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './book-form.component.html'
})
export class BookFormComponent implements OnInit {

  form!: FormGroup;     // <-- declare only
  isEdit = false;
  bookId?: number;

  constructor(
    private fb: FormBuilder,
    private service: BookService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      title: ['', Validators.required],
      author: ['', Validators.required],
      isbn: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
      publicationYear: [],
      genre: [''],
      description: ['']
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.bookId = +id;
      this.loadBook();
    }
  }

  private loadBook(): void {
    this.service.getBook(this.bookId!)
      .subscribe(book => this.form.patchValue(book));
  }

  submit(): void {
    if (this.form.invalid) return;

    const request = this.isEdit
      ? this.service.update(this.bookId!, this.form.value)
      : this.service.create(this.form.value);

    request.subscribe(() => this.router.navigate(['/']));
  }
}
