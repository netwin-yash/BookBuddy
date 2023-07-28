package org.bookbuddy.service.impl;

import org.bookbuddy.constants.BookStatus;
import org.bookbuddy.exceptions.BookExceptions;
import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.User;
import org.bookbuddy.service.BookService;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    // Instance variable to store the list of books
    private List<Book> initialBookList;
        public BookServiceImpl() {
        initialBookList = new ArrayList<>();
        initializeBooks();
    }

    public void initializeBooks() {
        initialBookList.add(new Book("Wings Of Fire", 1, Set.of("Apj", "Kalam"), BookStatus.TAKEN, new User("Yash@123", 1, "1111111111"),Set.of("apj","kalam book","wings","fire","Rocket")));
        initialBookList.add(new Book("Meditation", 2, Set.of("Vivekananda"), BookStatus.AVAILABLE, new User("Yash@123", 1, "1111111111"),Set.of("swami","vivekanand","swami vivekanand","paranayam","peace","medi")));
        initialBookList.add(new Book("Ramayana", 3, Set.of("Ram"), BookStatus.AVAILABLE, new User("Shre@123", 2, "3222222222"),Set.of("Shree Ram","Tulsidas","sita","ram-sita","ramrajya")));
        initialBookList.add(new Book("Ramdas swami", 4, Set.of("Ram"), BookStatus.AVAILABLE, new User("Shre@123", 2, "3222222222"),Set.of("ram bhakt","swami","sajjangad","ram")));
        initialBookList.add(new Book("Bhagavad gita", 5, Set.of("Yash"), BookStatus.AVAILABLE, new User("Shre@123", 2, "3222222222"),Set.of("gita","krishna","arjuna","mahabharat","vyas","bhagwad")));
        initialBookList.add(new Book("Bhagalpur ka chita", 6, Set.of("Yash"), BookStatus.AVAILABLE, new User("Shre@123", 2, "3222222222"),Set.of("bhagalpur","chitta")));
        initialBookList.add(new Book("Krishna", 7, Set.of("Yash"), BookStatus.AVAILABLE, new User("Ramesh@123", 3, "4222222222"), Set.of("krishna","kisna")));
        initialBookList.add(new Book("Hanuman", 8, Set.of("Apj", "Yash"), BookStatus.TAKEN, new User("Ramesh@123", 3, "4222222222"), Set.of("ram ke bhakt","hanuman","maruti","shree ram","anjani putra","ram")));
        initialBookList.add(new Book("Wings Of Fire 2", 9, Set.of("Apj", "Kalam"), BookStatus.TAKEN, new User("Yash@123", 1, "1111111111"),Set.of("apj","kalam book","wings","fire","Rocket")));
    }

    @Override
    public String addBook(Book book) {
        if(book == null){
            throw new BookExceptions(" Please add a book with appropriate records");
        }
        int nextBid = initialBookList.size() + 1;
        book.setBid(nextBid);
        book.setBookStatus(BookStatus.AVAILABLE);
        initialBookList.add(book);
        return " Book added successfully..!!";
    }

    @Override
    public List<Book> getListOfBooks() {
        if(initialBookList.isEmpty()){
            throw new BookExceptions("No Book Present in the library");
        }
     return initialBookList;
    }

    @Override
    public String borrowBook(Integer bid,User user) {
        if(bid == null || bid == 0) {
            throw new BookExceptions("Book Id can not be zero or empty");
        }
        Book b = initialBookList.stream()
                .filter(book -> book.getBid() == bid)
                .findFirst().get();
        if(b!=null && b.getBookStatus()==BookStatus.AVAILABLE){
            b.setBookStatus(BookStatus.TAKEN);
            b.setOwner(user);
            return " Book Borrowed Successfully...!!";
        }
        return " This Book has been already taken...!!";
    }

    public String returnBook(Integer bid){
        if(bid == null || bid == 0) {
            throw new BookExceptions(" Book Id can not be zero or empty");
        }
        Book b =initialBookList.stream().filter(book -> book.getBid()==bid).findFirst().get();
        if(b!=null && b.getBookStatus()==BookStatus.TAKEN){
            b.setBookStatus(BookStatus.AVAILABLE);
        }else{
            return " Return only the book you borrowed.";
        }
        return " Book Returned Successfully...!!";
    }

    public List<Book> getAllAvailbleBooks(){
        List<Book> b = getListOfBooks().stream().filter(book -> book.getBookStatus().equals(BookStatus.AVAILABLE)).collect(Collectors.toList());
        if(b.isEmpty()){
            System.out.println("No Book Available...! Come back later :) ");
            return null;
        }else {
            return b;
        }
    }

    public List<Book> searchBookByName(String name) {
        List<Book> bookList = getListOfBooks().stream().filter(book -> book.getName().trim().equalsIgnoreCase(name.trim().toLowerCase())).collect(Collectors.toList());
        if (bookList.isEmpty()) {
            return null;
        }
        return bookList;
    }

    public List<Book> searchBookWithAuthorName(String sName) {
        if (sName.isEmpty()) {
            System.out.println("Author name can not be empty field");
            return null;
        }
        List<Book> books = new ArrayList<>();
        boolean flag = false;
        List<Book> book = getListOfBooks();
        for (Book b : book) {
            Set<String> au = b.getAuthor();
            for (String a : au) {
                flag = a.equals(sName);
                if (flag) {
                    books.add(b);
                }
            }
        }
        if (books == null || books.isEmpty()) {
            return null;
        }
        return books;
    }

    public List<Book> searchBookWithKeywords(String keyword){

        if(keyword.isEmpty() || keyword == null){
            System.out.println("keyword field can not be empty");
            return null;
        }
        boolean flag = false;
        List<Book> bl = new ArrayList<>();
        List<Book> book = getListOfBooks();
        for(Book b : book){
            for(String s : b.getKeyword()) {
                if (s.trim().equalsIgnoreCase(keyword.trim().toLowerCase())) {
                    flag = true;
                }else{
                    flag = false;
                }
                if (flag) {
                    bl.add(b);
                }

            }

        }
           return bl;
    }













}
