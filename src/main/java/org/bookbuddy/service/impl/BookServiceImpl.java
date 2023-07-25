package org.bookbuddy.service.impl;

import org.bookbuddy.constants.BookStatus;
import org.bookbuddy.constants.UserStatus;
import org.bookbuddy.exceptions.BookExceptions;
import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.User;
import org.bookbuddy.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    // Instance variable to store the list of books
    private List<Book> initialBookList;

    public BookServiceImpl() {
        initialBookList = new ArrayList<>();
        initializeBooks();
    }

    public void initializeBooks() {
        initialBookList.add(new Book("Wings Of Fire", 1, "Apj", BookStatus.AVAILABLE, new User("Prakash", 1, "1111111111")));
        initialBookList.add(new Book("Meditation", 2, "Vivekananda", BookStatus.AVAILABLE, new User("Ramesh", 2, "2222222222")));
        initialBookList.add(new Book("Ram", 3, "Vk", BookStatus.TAKEN, new User("Msd", 3, "3222222222")));
    }


    @Override
    public String addBook(Book book) {
        if(book == null){
            throw new BookExceptions("Please add one book with appropriate records");
        }
        int nextBid = initialBookList.size() + 1;
        book.setBid(nextBid);
        book.setBookStatus(BookStatus.AVAILABLE);
        initialBookList.add(book);
        return "Book added successfully..!!";
    }

    @Override
    public List<Book> getListOfBooks() {
        if(initialBookList.isEmpty()){
            throw new BookExceptions("No Book Present in the library");
        }
     return initialBookList;
    }

    @Override
    public String borrowBook(Integer bid) {
        if(bid == null || bid == 0) {
            throw new BookExceptions("Book Id can not be zero or empty");
        }
        Book b = initialBookList.stream()
                .filter(book -> book.getBid() == bid)
                .findFirst().get();
        if(b!=null && b.getBookStatus()==BookStatus.AVAILABLE){
            b.setBookStatus(BookStatus.TAKEN);
            return "Book Borrowed Successfully...!!";
        }
        return "This Book has been already taken...!!";
    }

    public String returnBook(Integer bid){
        if(bid == null || bid == 0) {
            throw new BookExceptions("Book Id can not be zero or empty");
        }
        Book b =initialBookList.stream().filter(book -> book.getBid()==bid).findFirst().get();
        if(b!=null && b.getBookStatus()==BookStatus.TAKEN){
            b.setBookStatus(BookStatus.AVAILABLE);
        }else{
            return "Return only the book you borrowed.";
        }
        return "Book Returned Successfully...!!";
    }




}
