package com.book.collection.service;

import com.book.collection.dto.BookRequest;
import com.book.collection.model.Book;
import com.book.collection.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getAll(String search) {
        if (search == null || search.isBlank()) {
            return repo.findAll();
        }
        return repo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(search, search);
    }

    public Book get(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Book createBook(BookRequest r) {
        repo.findByIsbn(r.isbn).ifPresent(b -> {
            throw new IllegalArgumentException("ISBN already exists");
        });
        Book b = new Book();
        b.setTitle(r.title);
        b.setAuthor(r.author);
        b.setIsbn(r.isbn);
        b.setPublicationYear(r.publicationYear);
        b.setGenre(r.genre);
        b.setDescription(r.description);
        return repo.save(b);
    }

    public Book update(Long id, BookRequest r) {
        Book b = get(id);
        b.setTitle(r.title);
        b.setAuthor(r.author);
        b.setIsbn(r.isbn);
        b.setPublicationYear(r.publicationYear);
        b.setGenre(r.genre);
        b.setDescription(r.description);
        return repo.save(b);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
