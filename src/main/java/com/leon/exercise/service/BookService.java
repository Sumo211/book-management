package com.leon.exercise.service;

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
        return bookRepository.findOne(id);
    }

    public Book save(Book newBook) {
        return bookRepository.save(newBook);
    }

    public void delete(long id) {
        bookRepository.delete(id);
    }

}
