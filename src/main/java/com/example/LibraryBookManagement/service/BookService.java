package com.example.LibraryBookManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LibraryBookManagement.entity.Book;
import com.example.LibraryBookManagement.exception.BookNotFoundException;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    private Long nextId = 1L;

    // Add Book
    public Book addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    // Get All Books
    public List<Book> getAllBooks() {
        return books;
    }

    // Get Book By ID
    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new BookNotFoundException(
                        "Book not found with ID: " + id
                    )
                );
    }

    // Update Book
    public Book updateBook(Long id, Book updatedBook) {

        Book existingBook = getBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setCategory(updatedBook.getCategory());
        existingBook.setQuantity(updatedBook.getQuantity());

        return existingBook;
    }

    // Delete Book
    public boolean deleteBook(Long id) {

        Book existingBook = getBookById(id);

        books.remove(existingBook);

        return true;
    }
}