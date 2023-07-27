package org.bookbuddy.pojo;

import org.bookbuddy.constants.BookStatus;

import java.util.List;
import java.util.Set;

public class Book {
    private String name;
    private Integer bid;
    private Set<String> author;
    private BookStatus bookStatus;
    private User owner;
    private Set<String> keyword;

    public Book(){
    }
    public Book(String name, int bid, Set<String> author, BookStatus status,User user,Set<String> keywords){
        this.name = name;
        this.bid = bid;
        this.author = author;
        this.bookStatus = status;
        this.owner = user;
        this.keyword = keywords;
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

    public void setKeywords(Set<String> keywords){
        this.keyword=keywords;
    }
    public Set<String> getKeyword(){
        return keyword;
    }
    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setAuthor(Set<String> author){
        this.author = author;
    }
    public String getName(){
        return name;
    }
    public int getBid() {
        return bid;
    }
    public Set<String> getAuthor(){
        return author;
    }
    public BookStatus getBookStatus() {
        return bookStatus;
    }
    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}
