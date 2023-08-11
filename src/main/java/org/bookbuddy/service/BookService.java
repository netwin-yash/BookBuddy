package org.bookbuddy.service;

import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.User;

import java.text.ParseException;
import java.util.List;

public interface BookService {
    public String addBook(Book book,User user);
    List<Book> getListOfBooks();

   String borrowBook(Integer bid, User user) throws ParseException;

   String returnBook(Integer bid,User user);

   List<Book> getAllAvailableBooks();
   List<Book> searchBookByName(String name);
    List<Book> searchBookWithAuthorName(String sName);

    List<Book> searchBookWithKeywords(String keyword);
    void setUserService(UserService userService);
}
