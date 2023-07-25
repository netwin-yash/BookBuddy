package org.bookbuddy;


import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.User;
import org.bookbuddy.service.impl.BookServiceImpl;
import org.bookbuddy.service.impl.UserServiceImpl;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);
    static BookServiceImpl bookService;
    static UserServiceImpl userService;
    //reading String input
    static Scanner sc = new Scanner(System.in);
    // integer input reading
    static Scanner scanner = new Scanner(System.in);
    private static final String USERNAME_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String PHONE_REGEX = "^\\d{10}$";

    private static void addNewBook() {
        Book book = new Book();
        System.out.println(" Enter title of book");
        String name = sc.nextLine();
        book.setName(name);
        System.out.println(" Enter Author of book");
        String author = sc.nextLine();
        book.setAuthor(author);
        String response = bookService.addBook(book);
        System.out.println("\n" + response);
    }

    private static void getListOfBooks() {
        List<Book> booklist = bookService.getListOfBooks();
        for (Book b : booklist) {
            // Use format specifiers to maintain similar spaces between book details
            System.out.printf("%50s %20s %21s %30s %n", b.getName(), b.getAuthor(), b.getBid(), b.getBookStatus());
        }
    }

    private static void borrowBook(Integer bid) {
        String response = bookService.borrowBook(bid);
        System.out.println(response);
    }

    static User loginToSystem() {
        System.out.println(" \nEnter your username");
        String uName = sc.nextLine();
        System.out.println(" Enter your password");
        String pwd = sc.nextLine();
        User user = userService.validateUser(uName, pwd);
        if (user == null) {
            System.out.println("New User ? Register yourself with us first...!");
        } else {
            System.out.println(" Authentication Successful...!\n");
        }
        return user;
    }

    static void addNewUser() throws ParseException {
        boolean isUsernameValid = false;
        boolean isPasswordValid = false;
        boolean isMobileValid = false;
        User user = new User();
        while (!(isUsernameValid && isPasswordValid && isMobileValid)) {
            if (!isUsernameValid) {
                System.out.println(" Enter user name");
                String name = sc.nextLine();
                if (Pattern.matches(USERNAME_REGEX, name)) {
                    user.setuName(name);
                    isUsernameValid = true;
                } else {
                    System.out.println("Invalid User name format, please try again.");
                }
            }
            if (isUsernameValid && !isPasswordValid) {
                System.out.println(" Enter your password");
                String pwd = sc.nextLine();
                if (Pattern.matches(PASSWORD_REGEX, pwd)) {
                    user.setPassword(pwd);
                    isPasswordValid = true;
                } else {
                    System.out.println("Invalid Password format, please try again.");
                }
            }
            if (isUsernameValid && isPasswordValid && !isMobileValid) {
                System.out.println(" Enter your Mobile");
                String phone = sc.nextLine();
                if (Pattern.matches(PHONE_REGEX, phone)) {
                    user.setPhone(phone);
                    isMobileValid = true;
                } else {
                    System.out.println("Invalid Phone no. format, Mobile should be 10 number only.");
                }
            }
        }

            System.out.println(" Enter your Address");
            String add = sc.nextLine();
            user.setuAddress(add);
            String response = userService.addUser(user);
            System.out.println(response);
    }
    //library
    static void handleBookActions() {
        while (true) {
            System.out.println("\n");
            System.out.printf("%44s %35s %35s %n", " 1. Borrow book", "2. Return book", "3. Back to Main menu");
            System.out.print(" \nEnter your choice : ");
            int userChoice = scanner.nextInt();
            System.out.println("\n");
            switch (userChoice) {
                case 1:
                    // Borrow book
                    System.out.println(" Enter the Book Id of the book you want to borrow:");
                    int borrowBid = scanner.nextInt();
                    String borrowResult = bookService.borrowBook(borrowBid);
                    System.out.println(borrowResult);
                    break;
                case 2:
                    // Return book
                    System.out.println(" Enter the Book Id of the book you want to return:");
                    int returnBid = scanner.nextInt();
                    String returnResult = bookService.returnBook(returnBid);
                    System.out.println(returnResult);
                    break;
                case 3:
                    // Back to Main menu
                    return;
                default:
                    System.out.println(" Invalid choice. Please choose a valid option.");
            }
        }
    }
    //dashboard
    static void dashboard(boolean isRunning, User user) {
        System.out.printf("%120s %n", "* <== ** <=== *** <====  Welcome " + user.getuName() + " Have a great book sharing ====> *** ===> ** ==> *\n\n");
        while (isRunning) {
            // Display the dashboard options

            System.out.printf("%43s %45s %30s %n", " 1. Add a book", "2. Display list of books", "3. Borrow a book");
            System.out.println("\n");
            System.out.printf("%46s %40s %23s %n", " 4. Return a book", "5. Books Shared by you", "6. Exit");
            System.out.print("\n Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    System.out.println();
                    System.out.printf("%112s %n", "* <== ** <=== *** <====  LIST OF BOOKS ====> *** ===> ** ==> *\n\n");
                    System.out.printf("%46s %25s %24s %25s %n", "Name", "Author", "Book Id", "Status");
                    System.out.println();
                    getListOfBooks();
                    handleBookActions();
                    break;
                case 3:
                    getListOfBooks();
                    System.out.println(" \nEnter the Book Id of the book you want to borrow:");
                    int borrowBid = scanner.nextInt();
                    borrowBook(borrowBid);
                    break;
                case 4:
                    System.out.println(" Enter the Book Id of the book you want to return:");
                    int returnBid = scanner.nextInt();
                    String returnResult = bookService.returnBook(returnBid);
                    System.out.println(returnResult);
                    break;
                case 6:
                    System.out.println("\n Goodbye!");
                    return;
                default:
                    System.out.println(" \nUnder Construction. Please try again later.");
                    break;
            }
        }
    }
   //home Page
    public static int loginUI() {
        System.out.println("\n");
        System.out.printf("%108s %n", "*---**---***---- Book Buddy Application ----***---**---*\n");
        System.out.printf("%43s %45s %30s %n", " 1. Login", "2. Register as new User", "3. Exit");
        System.out.println("\n");
        System.out.print(" Enter your choice : ");
        int ch = scanner.nextInt();
        return ch;
    }

    public static void main(String[] args) throws ParseException {
        // final Logger logger = LoggerFactory.getLogger(Main.class);
        bookService = new BookServiceImpl();
        userService = new UserServiceImpl();
        boolean isRunning = true;
        while (isRunning) {
            int ch = loginUI();
            switch (ch) {
                case 1:
                    User user = loginToSystem();
                    if (user == null) {
                        break;
                    }
                    dashboard(isRunning, user);
                    break;
                case 2:
                    addNewUser();
                    break;
                case 3:
                    System.out.println("\n");
                    System.out.printf("%105s %n", "*---**---***---- See You AGAIN :) ----***---**---*\n");
                    isRunning = false;
                    break;

                default:
                    System.out.println(" Invalid Option. Please try again..!");
                    break;
            }

        }


    }


}