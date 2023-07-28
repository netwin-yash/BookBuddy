package org.bookbuddy.service;

import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.User;

import java.util.List;

public interface BookService {
    String addBook(Book book);
    List<Book> getListOfBooks();

   String borrowBook(Integer bid, User user);

   String returnBook(Integer bid);

   List<Book> getAllAvailbleBooks();
   List<Book> searchBookByName(String name);
    List<Book> searchBookWithAuthorName(String sName);

    List<Book> searchBookWithKeywords(String keyword);
}
