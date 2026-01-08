package com.book.collection.service;

import com.book.collection.dto.BookRequest;
import com.book.collection.model.Book;
import com.book.collection.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Test
    public void createBook_duplicateIsbn_shouldFail() {
        BookRepository repository = Mockito.mock(BookRepository.class);
        BookService service = new BookService(repository);

        BookRequest request = new BookRequest();
        request.setTitle("Title");
        request.setAuthor("Author");
        request.setIsbn("ISBN");

        Mockito.when(repository.findByIsbn(request.getIsbn())).thenReturn(Optional.of(new com.book.collection.model.Book()));

        assertThrows(IllegalArgumentException.class,
                () -> service.createBook(request));
    }

    @Test
    public void getAll_noSearch_returnsAll() {
        BookRepository repository = mock(BookRepository.class);
        BookService service = new BookService(repository);

        List<Book> all = List.of(new Book(1L, "Title1", "Author1", "123", 2000, "Genre1", "Desc1"));
        when(repository.findAll()).thenReturn(all);

        List<Book> result = service.getAll(null);

        assertEquals(all, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getAll_withSearch_returnsFiltered() {
        BookRepository repository = mock(BookRepository.class);
        BookService service = new BookService(repository);

        String search = "thor";
        List<Book> filtered = List.of(new Book(2L, "Another Title", "AuthorX", "456", 2010, "Genre2", "Desc2"));
        when(repository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(search, search)).thenReturn(filtered);

        List<Book> result = service.getAll(search);

        assertEquals(filtered, result);
        verify(repository, times(1)).findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(search, search);
    }

    @Test
    public void get_existingId_returnsBook() {
        BookRepository repository = mock(BookRepository.class);
        BookService service = new BookService(repository);

        Book book = new Book(3L, "T", "A", "789", 1999, "G", "D");
        when(repository.findById(3L)).thenReturn(Optional.of(book));

        Book result = service.get(3L);

        assertSame(book, result);
        verify(repository).findById(3L);
    }

    @Test
    public void get_nonExisting_throws() {
        BookRepository repository = mock(BookRepository.class);
        BookService service = new BookService(repository);

        when(repository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.get(4L));
    }

    @Test
    public void createBook_success_savesAndReturns() {
        BookRepository repository = mock(BookRepository.class);
        BookService service = new BookService(repository);

        BookRequest req = new BookRequest();
        req.setTitle("New");
        req.setAuthor("Auth");
        req.setIsbn("111222");
        req.setPublicationYear(2021);
        req.setGenre("SciFi");
        req.setDescription("Desc");

        when(repository.findByIsbn(req.getIsbn())).thenReturn(Optional.empty());
        when(repository.save(any(Book.class))).thenAnswer(invocation -> {
            Book b = invocation.getArgument(0);
            b.setId(99L);
            return b;
        });

        Book saved = service.createBook(req);

        assertNotNull(saved);
        assertEquals(99L, saved.getId());
        assertEquals(req.getTitle(), saved.getTitle());
        assertEquals(req.getAuthor(), saved.getAuthor());
        assertEquals(req.getIsbn(), saved.getIsbn());
        verify(repository).findByIsbn(req.getIsbn());
        verify(repository).save(any(Book.class));
    }

    @Test
    public void update_existing_updatesAndSaves() {
        BookRepository repository = mock(BookRepository.class);
        BookService service = new BookService(repository);

        Book existing = new Book(5L, "Old", "OldA", "333", 1990, "OldG", "OldD");
        when(repository.findById(5L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookRequest req = new BookRequest();
        req.setTitle("Updated");
        req.setAuthor("UpdatedA");
        req.setIsbn("333");
        req.setPublicationYear(2022);
        req.setGenre("NewG");
        req.setDescription("NewD");

        Book updated = service.update(5L, req);

        assertEquals(5L, updated.getId());
        assertEquals("Updated", updated.getTitle());
        assertEquals("UpdatedA", updated.getAuthor());
        assertEquals(Integer.valueOf(2022), updated.getPublicationYear());
        verify(repository).findById(5L);
        verify(repository).save(existing);
    }

    @Test
    public void delete_callsRepositoryDeleteById() {
        BookRepository repository = mock(BookRepository.class);
        BookService service = new BookService(repository);

        service.delete(6L);

        verify(repository).deleteById(6L);
    }

    @Test
    public void getAll_blankOrWhitespace_returnsAll() {
        BookRepository repository = mock(BookRepository.class);
        BookService service = new BookService(repository);

        List<Book> all = List.of(new Book(10L, "T1", "A1", "100", 2001, "G1", "D1"));
        when(repository.findAll()).thenReturn(all);

        List<Book> resultEmpty = service.getAll("");
        List<Book> resultSpaces = service.getAll("   ");

        assertEquals(all, resultEmpty);
        assertEquals(all, resultSpaces);
        verify(repository, times(2)).findAll();
    }
}
