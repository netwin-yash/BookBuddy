package org.bookbuddy.pojo;

import java.util.Date;

public class Library {
    private Book book;
    private User user;
    private Date allocateDate;
    private Date returnDate;
    public Library(Book b, User user, Date parse){
    }
    public Library(Book book, User user, Date allocateDate, Date returnDate) {
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

    public Date getAllocateDate() {
        return allocateDate;
    }

    public void setAllocateDate(Date allocateDate) {
        this.allocateDate = allocateDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
