package org.bookbuddy.service.impl;

import org.bookbuddy.constants.BookStatus;
import org.bookbuddy.constants.UserStatus;
import org.bookbuddy.exceptions.BookExceptions;
import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.User;
import org.bookbuddy.service.BookService;
import org.bookbuddy.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final Map<String, User> userMap;
    private final BookService bookService;


    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public UserServiceImpl(BookService bookService) throws ParseException {
        this.bookService = bookService;
        userMap = new HashMap<>();
        initialUsers();
    }

    public void initialUsers() throws ParseException {
        String date_string = "24-07-2023";
        //Instantiating the SimpleDateFormat class

        //Parsing the given String to Date object
        Date date = formatter.parse(date_string);
        userMap.put("Admin@123", new User("Admin", 1, "0000000000", "Admin@123", "Pune", UserStatus.ACTIVE, date));
        userMap.put("Yash@123", new User("Yash Joshi", 2, "1111111111", "Yash@123", "Pune", UserStatus.ACTIVE, date));
        userMap.put("Shre@123", new User("Shreya", 3, "3222222222", "Shre@123", "Nashik", UserStatus.ACTIVE, date));
        userMap.put("Ramesh@123", new User("Ramesh", 4, "4222222222", "Ramesh@123", "Mumbai", UserStatus.BLOCKED, date));

    }
    public  boolean isUsernameExists(String username) {
        return userMap.containsKey(username.trim());
    }
    public boolean isPhoneNumberRegistered(String phoneNumber) {
        for(Map.Entry<String,User> map : userMap.entrySet()){
            if (map.getValue().getPhone().trim().equalsIgnoreCase(phoneNumber.trim().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String addUser(User user) throws ParseException {
        if (user == null) {
            throw new BookExceptions("  Fields cannot be empty");
        }
        //verify user is already exist or not
        for(Map.Entry<String,User> map : userMap.entrySet()){
            String userName = map.getKey();
            if(user.getuName().equalsIgnoreCase(userName.trim().toLowerCase())
            && user.getName().trim().equalsIgnoreCase(map.getValue().getName().trim().toLowerCase())
            && user.getPassword().trim().equalsIgnoreCase(map.getValue().getPassword().trim().toLowerCase())
            && user.getPhone().trim().equalsIgnoreCase(map.getValue().getPhone().trim().toLowerCase())
            && user.getuAddress().trim().equalsIgnoreCase(map.getValue().getuAddress().trim().toLowerCase())){
                return "  This User already exist... ! Try to log in to the system...";
            }
        }

        int a = userMap.size();
        user.setUid(a + 1);
        // Set the registration date
        Date date = new Date();
        user.setRegDate(date);
        user.setStatus(UserStatus.ACTIVE);
        userMap.put(user.getuName(), user);
        return "  Registration Successful..!!";
    }

    @Override
    public User validateUser(String uName, String pwd) {
        boolean userName = false;
        boolean password = false;
        for (Map.Entry<String, User> map : userMap.entrySet()) {
            String storedUserName = map.getKey();
            if (storedUserName.trim().equals(uName.trim())) {
                userName = true;
                User user = map.getValue();
                if (user.getPassword().trim().equals(pwd.trim())) {
                    password = true;
                    user.setuName(uName);
                    return user;
                }
            }
        }
        if (!userName && !password) {
            System.out.println("Invalid UserName & Password, Enter correct credentials...!");
            return null;
        }
        if (!password) {
            System.out.println("Invalid Password, Enter correct Password...!");
        }
        return null;
    }

    public List<Book> mySharedBookHistory(User user) {
        List<Book> book = bookService.getListOfBooks().stream().filter(book1 -> book1.getOwner().getuName().trim().equals(user.getuName())).collect(Collectors.toList());
        if (book.isEmpty() || book == null) {
            System.out.println("You have not shared any book yet...");
            return null;
        }
        return book;
    }

    public List<Book> getFavouriteBooks() {

        return null;
    }

    public Set<Book> getListOfBorrowedBooks(User user) {
        if (user == null) {
            return null;
        }
        boolean flag = false;
        List<Book> book = bookService.getListOfBooks();

       Set<Book> books =  user.getBorrowedBooks();



        return books;
    }


}
