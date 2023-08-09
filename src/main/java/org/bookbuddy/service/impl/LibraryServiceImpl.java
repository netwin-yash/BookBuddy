package org.bookbuddy.service.impl;

import org.bookbuddy.Main;
import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.Library;
import org.bookbuddy.pojo.User;
import org.bookbuddy.service.BookService;
import org.bookbuddy.service.LibraryService;
import org.bookbuddy.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LibraryServiceImpl implements LibraryService {

private final BookServiceImpl bookService;
private final UserServiceImpl userService;
    public LibraryServiceImpl(BookServiceImpl bookService, UserServiceImpl userService) {
        this.bookService = bookService;
        this.userService = userService;
    }
    Map<String, Library> map = new HashMap<>();

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    public Map<String,Library> getDetailsOfBorrowedBooks(User user,  Set<Book> borrowedBooks) throws ParseException {


        for(Book b : borrowedBooks){
            Date borrowDate = new Date();
            String borrowDateStr = formatter.format(borrowDate);
            // Adding 9 days to the borrow date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(borrowDate);
            calendar.add(Calendar.DAY_OF_MONTH, 9);
            Date returnDate = calendar.getTime();
            String returnDateStr = formatter.format(returnDate);
            map.put(user.getuName(), new Library(b,user, formatter.parse(borrowDateStr), formatter.parse(returnDateStr)));

        }

return map;

    }






}
