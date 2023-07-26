package org.bookbuddy.pojo;

import org.bookbuddy.constants.BookStatus;

public class Book {
    private String name;
    private Integer bid;
    private String author;
    private BookStatus bookStatus;
    private User owner;

    public Book(){
    }
    public Book(String name, int bid, String author, BookStatus status,User user){
        this.name = name;
        this.bid = bid;
        this.author = author;
        this.bookStatus = status;
        this.owner = user;
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    public void  setName(String name){
        this.name = name;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public String getName(){
        return name;
    }
    public int getBid() {
        return bid;
    }

    public String getAuthor(){
        return author;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}
