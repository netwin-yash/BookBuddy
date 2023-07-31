package org.bookbuddy.pojo;

import org.bookbuddy.constants.UserStatus;
import org.bookbuddy.exceptions.BookExceptions;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class User {
    private static final String USERNAME_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String PHONE_REGEX = "^\\d{10}$";

    private String uName;
    private String name;
    private int uid;
    private String uAddress;
    private String phone;
    private UserStatus status;
    private Date regDate;
    private Set<Book> favouriteBooks;
    private Set<Book> borrowedBooks;
    private String msg;

    private String password;
    public User(){
    }
public User(String msg){
      this.msg =msg;
}
    public User(String uName,int uid, String phone,Set<Book> borrowedBooks) {
        this.uName = uName;
        this.uid = uid;
        this.phone = phone;
        this.borrowedBooks = borrowedBooks;
    }
    public User(String name,int uid, String phone,String password,String uAddress,UserStatus userStatus,Date regDate) {
        this.name = name;
        this.uid = uid;
        this.phone = phone;
        this.password = password;
        this.uAddress = uAddress;
        this.status = userStatus;
        this.regDate = regDate;
    }
    public User(String uName, String uAddress, String phone, UserStatus status, Date regDate, Set<Book> favouriteBooks, Set<Book> borrowedBooks){
        this.uName = uName;
        this.uAddress = uAddress;
        this.regDate = regDate;
        this.phone = phone;
        this.status = UserStatus.ACTIVE;
        this.favouriteBooks = favouriteBooks;
        this.borrowedBooks= borrowedBooks;
    }
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        if (isValidPassword(password)) {
            this.password = password;
        }
        else {
            throw new BookExceptions(" \nInvalid password format. Username must contain at least 1 uppercase letters, 1 lowercase letters,1 number, and 1 special character");
        }
    }
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
  public void setuName(String uName){
      if (isValidUserName(uName)) {
          this.uName = uName;
      }else {
          throw new BookExceptions(" \nInvalid username format. Username must contain at least 1 uppercase letters, 1 lowercase letters,1 number, and 1 special character");
      }
  }
    public String getuName(){
        return uName;
    }
    public void setuAddress(String address){
        this.uAddress = address;
    }
    public String getuAddress(){
        return uAddress;
    }
    public void setPhone(String phone){
      if(isValidPhone(phone)){
          this.phone = phone;
      }
      else {
          throw new BookExceptions(" \nInvalid Mobile format. It should be 10 digit only");
      }
    }
    public String getPhone(){
        return phone;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setRegDate(Date date){
        this.regDate = date;
    }
    public Date getRegDate(){
        return regDate;
    }
    public void setBorrowedBooks(Set<Book> borrowedBooks){
        this.borrowedBooks = borrowedBooks;
    }
    public Set<Book> getBorrowedBooks(){
        return borrowedBooks;
    }
    public void setFavouriteBooks(Set<Book> favouriteBooks){
        this.favouriteBooks = favouriteBooks;
    }
    public Set<Book> getFavouriteBooks(){
        return favouriteBooks;
    }



  static boolean isValidUserName(String uName){
        return Pattern.matches(USERNAME_REGEX,uName);
  }

    static boolean isValidPassword(String uName){
        return Pattern.matches(PASSWORD_REGEX,uName);
    }
    static boolean isValidPhone(String phone){
        return Pattern.matches(PHONE_REGEX,phone);
    }


}
