package org.bookbuddy.service;

import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.Library;
import org.bookbuddy.pojo.User;

import java.text.ParseException;
import java.util.Map;
import java.util.Set;

public interface LibraryService {


     String getDetailsOfBorrowedBooks(User user, Integer bid) throws ParseException;
     public void displayRegister();


}
