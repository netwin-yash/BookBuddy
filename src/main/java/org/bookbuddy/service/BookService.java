package org.bookbuddy.service;

import org.bookbuddy.pojo.Book;

import java.util.List;

public interface BookService {
    String addBook(Book book);

    List<Book> getListOfBooks();

   String borrowBook(Integer bid);

    public String returnBook(Integer bid);

    List<Book> getAllAvailbleBooks();
}
