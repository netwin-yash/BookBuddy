package org.bookbuddy.service.impl;

import org.bookbuddy.constants.UserStatus;
import org.bookbuddy.exceptions.BookExceptions;
import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.User;
import org.bookbuddy.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

   private List<User> userList;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    public UserServiceImpl() throws ParseException {
        userList = new ArrayList<>();
        initialUsers();
    }

    public void initialUsers() throws ParseException {
        String date_string = "24-07-2023";
        //Instantiating the SimpleDateFormat class

        //Parsing the given String to Date object
        Date date = formatter.parse(date_string);
        userList.add(new User("Yash@123",1,"1010101010","Yash@123","Pune",UserStatus.ACTIVE,date));
        userList.add(new User("Shre@123",2,"2010101010","Shre@123","Nashik",UserStatus.ACTIVE,date));

    }

    @Override
    public String addUser(User user) throws ParseException {
        if(user == null){
            throw new BookExceptions("Fields cannot be empty");
        }
        int a =  userList.size();
        user.setUid(a+1);
        // Set the registration date
        Date date = new Date();
        user.setRegDate(date);
        user.setStatus(UserStatus.ACTIVE);
        userList.add(user);
        return "User Created Successfully..!!";
    }
    @Override
    public User validateUser(String uName, String pwd) {
        boolean userName = false;
        boolean password = false;
        for(User user : userList){
            if(user.getuName().trim().equals(uName.trim())){
                userName=true;
                if(user.getPassword().trim().equals(pwd.trim())){
                    password=true;
                    return user;
                }
            }
        }
        if(!userName && !password){
            System.out.println("Invalid UserName & Password, Enter correct credentials...!");
            return null;
        }
        if(!password){
            System.out.println("Invalid Password, Enter correct Password...!");
        }

        return null;

    }


}
