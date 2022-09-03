package com.acs.elearn.service;

import com.acs.elearn.dao.model.Book;
import com.acs.elearn.dao.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Iterable<Book> allBooks() {
        return bookRepository.findAll();
    }

    public String addBook(String name) {
        Book b = new Book();
        b.setName(name);
        bookRepository.save(b);
        return "Saved";
    }
}
