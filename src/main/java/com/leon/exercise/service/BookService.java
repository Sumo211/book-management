package com.leon.exercise.service;

import com.leon.exercise.controller.exception.BookNotFoundException;
import com.leon.exercise.model.Book;
import com.leon.exercise.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(long id) {
        Book book = bookRepository.findOne(id);
        if (book == null) {
            throw new BookNotFoundException("Can not find book with id " + id);
        }

        return book;
    }

    public Book save(Book newBook) {
        return bookRepository.save(newBook);
    }

    public void delete(long id) {
        Book book = findOne(id);
        bookRepository.delete(book);
    }

}
