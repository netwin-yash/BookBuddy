package org.bookbuddy.service;

import org.bookbuddy.pojo.User;

import java.text.ParseException;

public interface UserService {

    String addUser(User user) throws ParseException;


    User validateUser(String uName, String pwd);


}
