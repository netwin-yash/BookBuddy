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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LibraryServiceImpl implements LibraryService {

    private final BookService bookService;
    private final UserService userService;

    public LibraryServiceImpl(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    Map<String, Library> map = new HashMap<>();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String getDetailsOfBorrowedBooks(User user, Integer bid) throws ParseException {


        List<Book> bList = bookService.getListOfBooks();
        for (Book books : bList) {
            if (books.getBid().equals(bid)) {
                LocalDate borrowDate = LocalDate.now();
                LocalDate returnDate = borrowDate.plusDays(9);
                String borrowDateStr = borrowDate.format(formatter);
                String returnDateStr = returnDate.format(formatter);
                map.put(user.getuName(), new Library(books, user, LocalDate.parse(borrowDateStr, formatter), LocalDate.parse(returnDateStr, formatter)));
                return " Successfully added to register...!";
            } else {
                return " Not valid entry";
            }
        }
            return " Not valid entry";
    }

    public void displayRegister() {
        for (Map.Entry<String, Library> mapp : map.entrySet()) {
            System.out.println(mapp.getKey() + " " + mapp.getValue().getBook().getName() + " " + mapp.getValue().getUser().getName() + " " + mapp.getValue().getAllocateDate().format(formatter) + " " + mapp.getValue().getReturnDate().format(formatter));
        }
    }


}


