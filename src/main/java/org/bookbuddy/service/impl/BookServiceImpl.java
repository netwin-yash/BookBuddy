package org.bookbuddy.service.impl;

import org.bookbuddy.constants.BookStatus;
import org.bookbuddy.exceptions.BookExceptions;
import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.User;
import org.bookbuddy.service.BookService;
import org.bookbuddy.service.LibraryService;
import org.bookbuddy.service.UserService;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    // Instance variable to store the list of books
    private List<Book> initialBookList;
    private final UserService userService;
    private final LibraryService libraryService;
        public BookServiceImpl(UserService userService, LibraryService libraryService) {
            this.userService = userService;
            this.libraryService = libraryService;
        initialBookList = new ArrayList<>();
        initializeBooks();
    }

    public void initializeBooks() {
        initialBookList.add(new Book("Wings Of Fire", 1,3,Set.of("Apj", "Kalam"), BookStatus.AVAILABLE, new User("Yash@123", 1, "1111111111",Set.of()),Set.of("apj","kalam book","wings","fire","Rocket")));
        initialBookList.add(new Book("Meditation", 2, 3,Set.of("Vivekananda"), BookStatus.AVAILABLE, new User("Yash@123", 1, "1111111111",Set.of()),Set.of("swami","vivekanand","swami vivekanand","paranayam","peace","medi")));
        initialBookList.add(new Book("Ramayana", 3,1, Set.of("Ram"), BookStatus.AVAILABLE, new User("Shre@123", 2, "3222222222",Set.of()),Set.of("Shree Ram","Tulsidas","sita","ram-sita","ramrajya")));
        initialBookList.add(new Book("Ramdas swami", 4,1, Set.of("Ram"), BookStatus.AVAILABLE, new User("Shre@123", 2, "3222222222",Set.of()),Set.of("ram bhakt","swami","sajjangad","ram")));
        initialBookList.add(new Book("Bhagavad gita", 5,3, Set.of("Yash"), BookStatus.AVAILABLE, new User("Shre@123", 2, "3222222222",Set.of()),Set.of("gita","krishna","arjuna","mahabharat","vyas","bhagwad")));
        initialBookList.add(new Book("Bhagalpur ka chita", 6,8, Set.of("Yash"), BookStatus.AVAILABLE, new User("Shre@123", 2, "3222222222",Set.of()),Set.of("bhagalpur","chitta")));
        initialBookList.add(new Book("Krishna", 7,4, Set.of("Yash"), BookStatus.AVAILABLE, new User("Ramesh@123", 3, "4222222222",Set.of()), Set.of("krishna","kisna")));
        initialBookList.add(new Book("Hanuman", 8, 5,Set.of("Apj", "Yash"), BookStatus.AVAILABLE, new User("Ramesh@123", 3, "4222222222",Set.of()), Set.of("ram ke bhakt","hanuman","maruti","shree ram","anjani putra","ram")));
        initialBookList.add(new Book("Wings Of Fire 2", 9,7, Set.of("Apj", "Kalam"), BookStatus.AVAILABLE, new User("Yash@123", 1, "1111111111",Set.of()),Set.of("apj","kalam book","wings","fire","Rocket")));
    }

    @Override
    public String addBook(Book book,User user) {
        if(book == null){
            throw new BookExceptions(" Please add a book with appropriate records");
        }
        int nextBid = initialBookList.size() + 1;
        book.setBid(nextBid);
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setOwner(user);
        book.setNoOfBooks(book.getNoOfBooks());
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
    public String borrowBook(Integer bid, User user) throws ParseException {
        if (bid == null || bid == 0) {
            throw new BookExceptions("Book Id can not be zero or empty");
        }
        Book b = initialBookList.stream().filter(book -> book.getBid() == bid).findFirst().get();

        if (b != null && b.getBookStatus() == BookStatus.AVAILABLE && b.getNoOfBooks() > 0) {
            // Check if the user is not already an owner of the book
            if (!b.getOwner().equals(user)) {
                Set<Book> borrowedBooks = user.getBorrowedBooks();

                if (borrowedBooks != null && user.getBorrowedBooks().contains(b)) {
                    return " You have borrowed one copy of this book already ";
                }
                Integer i = b.getNoOfBooks();
                // Decrement the book count only if the user is not already an owner
                i--;
                b.setNoOfBooks(i);
                if (i == 0) {
                    // For the last book in the system
                    b.setBookStatus(BookStatus.TAKEN);
                }
                // Adding the current book to the existing set of borrowed books
                //if no book is borrowed then creates new set of book
                if (borrowedBooks == null) {
                    borrowedBooks = new HashSet<>();
                }
                // adding into existing set
                borrowedBooks.add(b);
                user.setBorrowedBooks(borrowedBooks);


                //making entry to library
                libraryService.getDetailsOfBorrowedBooks(user,borrowedBooks);

                return " Book Borrowed Successfully...!!";
            } else {
                // User is already an owner of the book
                return " You already own a copy of this book.";
            }
        } else {
            return " Sorry, the book is not available for borrowing.";
        }

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
                flag = a.trim().equalsIgnoreCase(sName.trim().toLowerCase());
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
