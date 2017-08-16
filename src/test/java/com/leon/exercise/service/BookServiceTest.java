package com.leon.exercise.service;

import com.leon.exercise.controller.exception.BookNotFoundException;
import com.leon.exercise.model.Book;
import com.leon.exercise.repository.BookRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @After
    public void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    public void testSaveBook_Success() {
        //given
        Book book = Book.builder().title("test").author("test").build();
        assertEquals(0, book.getId());

        //when
        Book savedBook = bookService.save(book);

        //then
        assertTrue(savedBook.getId() != 0);
    }

    @Test
    public void testFindAllBooks_Success() {
        //given
        Book book1 = Book.builder().title("test1").author("test1").build();
        Book book2 = Book.builder().title("test2").author("test2").build();
        bookRepository.save(Arrays.asList(book1, book2));

        //when
        Iterable<Book> books = bookService.findAll();

        //then
        int count = 0;
        for (Book book : books) {
            count++;
            assertTrue(book.getId() != 0);
        }
        assertEquals(2, count);
    }

    @Test
    public void testFindOneBook_Success() {
        //given
        long id = bookRepository.save(Book.builder().title("test").author("test").build()).getId();

        //when
        Book result = bookService.findOne(id);

        //then
        assertEquals("test", result.getTitle());
        assertEquals("test", result.getAuthor());
    }

    @Test(expected = BookNotFoundException.class)
    public void testFindOneBook_NotFound() {
        //given
        long id = 1;

        //when
        bookService.findOne(id);
    }

    @Test
    public void deleteBook_Success() {
        //given
        long id = bookRepository.save(Book.builder().title("test").author("test").build()).getId();

        //when
        bookService.delete(id);

        //then
        assertEquals(0, StreamSupport.stream(bookService.findAll().spliterator(), false).count());
    }

}
