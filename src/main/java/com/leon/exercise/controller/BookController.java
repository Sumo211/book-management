package com.leon.exercise.controller;

import com.leon.exercise.controller.exception.BookNotFoundException;
import com.leon.exercise.model.Book;
import com.leon.exercise.service.BookService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    @GetMapping(value = "/books")
    public String findAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping(value = "/book/{id}")
    public String findOne(@PathVariable long id, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        return "book";
    }

    @GetMapping(value = "/book/new")
    public String showCreationPage(Book book, Model model) {
        model.addAttribute("book", book);
        return "book-creation";
    }

    @PostMapping(value = "/book")
    public String save(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book-creation";
        }

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping(value = "/book/delete/{id}")
    public String delete(@PathVariable long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping(value = "/book/edit/{id}")
    public String showEditionPage(@PathVariable long id, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        return "book-edition";
    }

    @ExceptionHandler(BookNotFoundException.class)
    public String showErrorPage() {
        return "error";
    }

}
