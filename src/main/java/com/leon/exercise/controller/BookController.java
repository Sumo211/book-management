package com.leon.exercise.controller;

import com.leon.exercise.model.Book;
import com.leon.exercise.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books")
    public ModelAndView findAll() {
        ModelAndView page = new ModelAndView("books");
        page.addObject("books", bookService.findAll());
        return page;
    }

    @GetMapping(value = "/book/{id}")
    public ModelAndView findOne(@PathVariable long id) {
        ModelAndView page = new ModelAndView("book");
        page.addObject("book", bookService.findOne(id));
        return page;
    }

    @GetMapping(value = "/book/new")
    public ModelAndView showCreationPage(Book book) {
        ModelAndView page = new ModelAndView("book-creation");
        page.addObject("book", book);
        return page;
    }

    @PostMapping(value = "/book")
    public ModelAndView save(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return showCreationPage(book);
        }

        bookService.save(book);
        return findAll();
    }

    @GetMapping(value = "/book/delete/{id}")
    public ModelAndView delete(@PathVariable long id) {
        bookService.delete(id);
        return findAll();
    }

    @GetMapping(value = "/book/edit/{id}")
    public ModelAndView update(@PathVariable long id) {
        ModelAndView page = new ModelAndView("book-edition");
        page.addObject("book", bookService.findOne(id));
        return page;
    }

}
