package org.bookbuddy.exceptions;

public class BookExceptions extends RuntimeException{
    private String msg;
   public BookExceptions(String msg){
        super(msg);
    }
}
