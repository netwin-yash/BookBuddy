package org.bookbuddy.pojo;

import java.util.Date;
import java.util.List;

public class Library {
    private List<Book> book;
    private List<User> user;
    private Date allocateDate;
    private Date returnDate;
    public Library(){
    }
    public Library(List<Book> book, List<User> user, Date allocateDate, Date returnDate) {
        this.book = book;
        this.user = user;
        this.allocateDate = allocateDate;
        this.returnDate = returnDate;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
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
