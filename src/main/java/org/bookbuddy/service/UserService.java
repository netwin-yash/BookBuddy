package org.bookbuddy.service;

import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.User;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface UserService {

     String addUser(User user) throws ParseException;


     User validateUser(String uName, String pwd);
     List<Book> mySharedBookHistory(User user);

  //   List<Book> getListOfBorrowedBooks(User user);

     public  boolean isUsernameExists(String username);
      boolean isPhoneNumberRegistered(String phoneNumber);
      Set<Book> getListOfBorrowedBooks(User user);
     void setBookService(BookService bookService);
}
