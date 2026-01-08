package com.book.collection.controller;

import com.book.collection.dto.BookRequest;
import com.book.collection.model.Book;
import com.book.collection.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> all(@RequestParam(required=false) String search) {
        return service.getAll(search);
    }

    @GetMapping("/{id}")
    public Book one(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public Book create(@Valid @RequestBody BookRequest r) {
        return service.createBook(r);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @Valid @RequestBody BookRequest r) {
        return service.update(id, r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
