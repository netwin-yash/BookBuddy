package org.bookbuddy.service;

import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.Library;
import org.bookbuddy.pojo.User;

import java.text.ParseException;
import java.util.Map;
import java.util.Set;

public interface LibraryService {


     Map<String, Library> getDetailsOfBorrowedBooks(User user, Set<Book> borrowedBooks) throws ParseException;



}
