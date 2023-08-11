package org.bookbuddy.pojo;

import java.time.LocalDate;
import java.util.Date;

public class Library {
    private Book book;
    private User user;
    private LocalDate allocateDate;
    private LocalDate returnDate;
    public Library(Book b, User user, Date parse){
    }
    public Library(Book book, User user, LocalDate allocateDate, LocalDate returnDate) {
        this.book = book;
        this.user = user;
        this.allocateDate = allocateDate;
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getAllocateDate() {
        return allocateDate;
    }

    public void setAllocateDate(LocalDate allocateDate) {
        this.allocateDate = allocateDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
