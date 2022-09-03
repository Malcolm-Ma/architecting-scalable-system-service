package com.acs.elearn.web.controllers;

import com.acs.elearn.dao.model.Book;
import com.acs.elearn.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/book")
public class BookController {

    @Autowired // This means to get the bean called bookService
    BookService bookService;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewBook (@RequestParam String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return bookService.addBook(name);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        // This returns a JSON or XML with the users
        return bookService.allBooks();
    }
}
