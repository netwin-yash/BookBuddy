package org.bookbuddy.pojo;

import org.bookbuddy.constants.BookStatus;

import java.util.Set;

public class Book {
    private String name;
    private Integer bid;
    private Set<String> author;
    private BookStatus bookStatus;
    private User owner;
    private Set<String> keyword;
    private Integer noOfBooks;

    public Book(){
    }
    public Book(String name, Integer bid,Integer count,  Set<String> author, BookStatus status,User user,Set<String> keywords){
        this.name = name;
        this.bid = bid;
        this.noOfBooks= count;
        this.author = author;
        this.bookStatus = status;
        this.owner = user;
        this.keyword = keywords;
    }

    public void setKeyword(Set<String> keyword) {
        this.keyword = keyword;
    }

    public Integer getNoOfBooks() {
        return noOfBooks;
    }

    public void setNoOfBooks(Integer noOfBooks) {
        this.noOfBooks = noOfBooks;
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
    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public void setAuthor(Set<String> author){
        this.author = author;
    }
    public String getName(){
        return name;
    }
    public Integer getBid() {
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
