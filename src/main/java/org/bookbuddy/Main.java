package org.bookbuddy;



import org.bookbuddy.pojo.Book;
import org.bookbuddy.pojo.User;
import org.bookbuddy.service.LibraryService;
import org.bookbuddy.service.impl.BookServiceImpl;
import org.bookbuddy.service.impl.LibraryServiceImpl;
import org.bookbuddy.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);
    static BookServiceImpl bookService;
    static UserServiceImpl userService;
    static LibraryServiceImpl libraryService;
    //reading String input
    static Scanner sc = new Scanner(System.in);
    // integer input reading
    static Scanner scanner = new Scanner(System.in);
    private static final String USERNAME_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String PHONE_REGEX = "^\\d{10}$";
    //home Page
    public static int loginUI() {
        System.out.println("\n");
        System.out.printf("%108s %n", "*---**---***---- Book Buddy Application ----***---**---*\n");
        System.out.printf("%43s %45s %30s %n", " 1. Login", "2. Register as new User", "3. Exit");
        System.out.println("\n");
        System.out.print(" Enter your choice : ");
        return scanner.nextInt();
    }
    //instructions
    private static void appInstructions() {
        System.out.println();
        System.out.printf("%103s %n", "*--------------- Instructions ---------------*\n");
        System.out.println();
        System.out.printf("%-60s%n", "1. Usernames and passwords must contain at least 8 characters.");
        System.out.printf("%-60s%n", "2. Usernames and passwords must include at least 1 lowercase letter, 1 uppercase letter, 1 special character, and 1 numeric digit.");
        System.out.printf("%-60s%n", "3. Phone numbers should consist of exactly 10 digits & must be unique.");



    }
    //register new USER...!
    static User registerNewUser() throws ParseException {
        System.out.println("\n");
        System.out.printf("%103s %n", "*--------------- Registration ---------------*\n");
        boolean isUsernameValid = false;
        boolean isPasswordValid = false;
        boolean isMobileValid = false;
        User user = new User();
        System.out.print("  Enter Your name : ");
        String name = sc.nextLine();
        user.setName(name);
        while (!(isUsernameValid && isPasswordValid && isMobileValid)) {
            if (!isUsernameValid) {
                System.out.println();
                System.out.print("  Enter user name  : ");
                String uname = sc.nextLine();
                if (Pattern.matches(USERNAME_REGEX, uname)) {
                   boolean flag =  userService.isUsernameExists(uname);
                   if(flag){
                       System.out.println();
                       System.out.println("  This Username is already taken... Try different username...!");
                       isUsernameValid = false;
                   }else {
                       user.setuName(uname);
                       isUsernameValid = true;
                   }
                } else {
                    System.out.println();
                    System.out.println("  Sorry, the username you provided does not meet the required format. Please ensure your username adheres to the specified criteria. ");
                }
            }
            if (isUsernameValid && !isPasswordValid) {
                System.out.println();
                System.out.print("  Enter your password : ");
                String pwd = sc.nextLine();
                if (Pattern.matches(PASSWORD_REGEX, pwd)) {
                    user.setPassword(pwd);
                    isPasswordValid = true;
                } else {
                    System.out.println("  Invalid Password format, please try again.");
                }
            }
            if (isUsernameValid && isPasswordValid && !isMobileValid) {
                System.out.println();
                System.out.print("  Enter your Mobile : ");
                String phone = sc.nextLine();
                if (Pattern.matches(PHONE_REGEX, phone)) {
                    if (userService.isPhoneNumberRegistered(phone)) {
                        System.out.println("  This phone number is already registered with us...!");
                    } else {
                        user.setPhone(phone);
                        isMobileValid = true;
                    }
                } else {
                    System.out.println("  Invalid Phone no. format, Mobile should be 10 numbers only.");
                }

            }
        }
        System.out.println();
        System.out.print("  Enter your Address : ");
        String add = sc.nextLine();
        user.setuAddress(add);
        String response = userService.addUser(user);
        System.out.println("\n"+response+"\n");
        if(response.matches("  Registration Successful..!!")){
            return user;
        }else {
            return null;
        }
    }
    //login ui
    static User loginToSystem(User user,boolean flag) {
        if(flag){
          User u =  userService.validateUser(user.getuName(), user.getPassword());
          if(u==null){
              System.out.println("  Not a valid user");
          } else {
              System.out.println();
              System.out.println(" Authentication Successful...!\n");
          }
          return u;
        }
        else {
            System.out.print(" \n Enter your username : ");
            String uName = sc.nextLine();
            System.out.println();
            System.out.print(" Enter your password : ");
            String pwd = sc.nextLine();
            User tempUser = new User();
            tempUser = userService.validateUser(uName, pwd);
            if (tempUser == null) {
                System.out.println("New User ? Register yourself with us first...!");
            } else {
                System.out.println();
                System.out.println(" Authentication Successful...!\n");
            }
            return tempUser;
        }
    }

    //dashboard
    static void dashboard(boolean isRunning, User user) throws ParseException {
        designLine();
        System.out.println();
        System.out.printf("%125s %n", "* <== ** <=== *** <====  Welcome " + user.getName() + ", Have a great book sharing ====> *** ===> ** ==> *\n");
        designLine();
        System.out.println();
        while (isRunning) {
            System.out.printf("%44s %34s %43s %n"," 1. Add a book"," 2. All Books"," 3. Search a book");
            System.out.println("\n");
            System.out.printf("%49s %33s %39s %n"," 4. Available books"," 5. Borrow a book","6. Return a book");
            System.out.println("\n");
            System.out.printf("%52s %22s %38s %n"," 7. Books Shared by me"," 8. Other","9. Exit");
            System.out.print("\n Enter your choice : ");
            int choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    addNewBook(user);
                    break;
                case 2:
                    System.out.println("\n");
                    System.out.printf("%110s %n", "* <== ** <=== *** <====  LIST OF BOOKS ====> *** ===> ** ==> *\n");
                    designLine();
                    System.out.printf("%34s %24s %23s %25s %25s %n", "Name", "Author", "Book Id", "Status", "Available Books\n");
                    getListOfBooks();
                    designLine();
                    handleBookActions(user);
                    break;
                case 3:
                    handleSearchBookActions();
                    break;
                case 4:
                    System.out.println();
                    System.out.printf("%113s %n", "* <== ** <=== *** <====  LIST OF AVAILABLE BOOKS ====> *** ===> ** ==> *");
                    designLine();
                    System.out.printf("%34s %24s %23s %25s %25s %n", "Name", "Author", "Book Id", "Status", "Available Books\n");
                    int c = availableBooks();
                    if(c==0){
                        break;
                    }
                    System.out.println();
                    designLine();
                    handleBookActions(user);
                    break;
                case 5:
                    System.out.println();
                    System.out.printf("%113s %n", "* <== ** <=== *** <====  LIST OF AVAILABLE BOOKS ====> *** ===> ** ==> *\n");
                    designLine();
                    System.out.printf("%34s %24s %23s %25s %25s %n", "Name", "Author", "Book Id", "Status", "Available Books\n");
                    int ch = availableBooks();
                    if(ch ==0){
                        break;
                    }
                    designLine();
                    System.out.print(" \n Enter the Book Id of the book you want to borrow : ");
                    int borrowBid = scanner.nextInt();
                    String borrowResult = borrowBook(borrowBid,user);
                    System.out.println("\n"+borrowResult+"\n");
                    break;
                case 6:
                    myBorrowedBooks(user,false);
                    System.out.print(" Enter the Book Id of the book you want to return : ");
                    int returnBid = scanner.nextInt();
                    String returnResult = bookService.returnBook(returnBid);
                    System.out.println("\n"+returnResult+"\n");
                    break;
                case 7:
                    System.out.printf("%110s %n", "* <-- ** <--- *** <---- BOOKS SHARED BY YOU ----> *** ---> ** --> *\n\n");
                     int count = mySharedBooks(user);
                     if(count==0){
                         break;
                     }
                    break;
                case 8:
                 handleOtherOption(user);
                 break;
                case 9:
                    System.out.println("\n See You Soon..! " +user.getName());
                    return;
                default:
                    System.out.println(" \n Under Construction. Please try again later.\n\n");
                    break;
            }
        }
    }
    // add a new bok into the system.
    private static void addNewBook(User user) {
        Book book = new Book();
        System.out.print(" Enter title of book : ");
        String name = sc.nextLine();
        book.setName(name);
        System.out.print("\n Enter author names (comma-separated) : ");
        String authorNamesInput = sc.nextLine();
        System.out.println();
        // Split the input string using commas as the delimiter
        String[] authorNamesArray = authorNamesInput.split(",");
        // Create a list containing the author names
        Set<String> authorList = Set.of(authorNamesArray);
        book.setAuthor(authorList);

        System.out.print(" Enter quantity of books you want to share : ");
        Integer count  = scanner.nextInt();
        System.out.println();
        book.setNoOfBooks(count);
        System.out.print(" Enter unique accessible keywords for your book (comma-separated) : ");
        String keywords = sc.nextLine();
        String[] keywordArray = keywords.split(",");
        Set<String> keywordList = Set.of(keywordArray);
        book.setKeyword(keywordList);
        System.out.println();
        String response = bookService.addBook(book,user);
        System.out.println(response+"\n" );
    }
    // all books
    private static void getListOfBooks() {
        List<Book> booklist = bookService.getListOfBooks();
        for (Book b : booklist) {
            // Use format specifiers to maintain similar spaces between book details
            String auth = String.join(", ",b.getAuthor());
            System.out.printf("%38s %20s %21s %30s %15s %n", b.getName(), auth, b.getBid(), b.getBookStatus(), b.getNoOfBooks());
        }
    }
    // borrow a book
    private static String borrowBook(Integer bid,User user) throws ParseException {
        return bookService.borrowBook(bid,user);
    }
    // available books to borrow
    private static int availableBooks() {

        List<Book> bookList = bookService.getAllAvailbleBooks();
        if(bookList == null){
            return 0;
        }else {
            for(Book b : bookList){
                String auth = String.join(", ",b.getAuthor());
                System.out.printf("%38s %20s %21s %30s %15s %n", b.getName(), auth, b.getBid(), b.getBookStatus(), b.getNoOfBooks());
            }
            return 1;
        }

    }
    //my shared books
    private static int mySharedBooks(User user) {
        int count = 0;
        if(user !=null) {
            List<Book> bookList = userService.mySharedBookHistory(user);
            if(bookList!=null) {
                System.out.printf("%34s %24s %23s %25s %25s %n", "Name", "Author", "Book Id", "Status", "Available Books\n");
                for (Book b : bookList) {
                    // Use format specifiers to maintain similar spaces between book details
                    String auth = String.join(", ",b.getAuthor());
                    System.out.printf("%38s %20s %21s %30s %15s %n", b.getName(), auth, b.getBid(), b.getBookStatus(), b.getNoOfBooks());
                    count++;
                }
                System.out.println("\n");
                designLine();

            }
            else{
                count = 0;
                System.out.println("Please share some books into the system...!");
            }
        }else{
            count =0;
            System.out.println("User can not be empty");
        }
        System.out.println("\n");
       return count;
    }
    //search a book
    static int searchBookWithName(String name){
        List<Book> bookList = bookService.searchBookByName(name);
        if(bookList == null ){
            return 0;
        }
        else {
            System.out.printf("%110s %n", "* <== ** <=== *** <====  LIST OF BOOKS ====> *** ===> ** ==> *");
            System.out.println("\n");
            designLine();
            System.out.printf("%34s %24s %23s %25s %25s %n", "Name", "Author", "Book Id", "Status", "Available Books\n");
            for (Book b : bookList) {
                String auth = String.join(", ",b.getAuthor());
                System.out.printf("%38s %20s %21s %30s %15s %n", b.getName(), auth, b.getBid(), b.getBookStatus(), b.getNoOfBooks());
            }
            designLine();
            return 1;
        }
    }
    private static void designLine(){
        String line =  "--------------------------------------------------------------------------------------------------------------------------------";
        int totalWidth = 100;
        int lineLength = line.length();
        int spaces = (totalWidth - lineLength) / 2;
        String centeredLine = String.format("%" + spaces + "s%s%" + spaces + "s%n", "", line, "");
        System.out.print("\n"+centeredLine+"\n");
    }
    // my borrowed books
    private static boolean myBorrowedBooks(User user,boolean flag){
        Set<Book> book = userService.getListOfBorrowedBooks(user);
        if(book == null){
            System.out.println(" You have not borrowed any book yet...!\n");
            return true;
        }
        System.out.printf("%110s %n", "* <-- ** <--- *** <---- BOOKS BORROWED BY YOU ----> *** ---> ** --> *\n\n");
        designLine();
        System.out.printf("%34s %24s %23s %25s %25s %n", "Name", "Author", "Book Id", "Status", "Available Books\n");

        for(Book b : book){
            String auth = String.join(", ",b.getAuthor());
            System.out.printf("%34s %24s %21s %30s %15s %n", b.getName(), auth, b.getBid(), b.getBookStatus(), b.getNoOfBooks());
        }
        designLine();
        return true;
    }


    //other
    private static void handleOtherOption(User user){
         while(true){
             System.out.println("\n");
             System.out.printf("%53s %31s %28s%n", " 1. Book borrowed by me", "2. favourite books","3. Back");
             System.out.print(" \n\n Enter your choice : ");
             int userChoice = scanner.nextInt();
             System.out.println();
             switch (userChoice){
                 case 1:
                     boolean flag1=false;
                     boolean flag = myBorrowedBooks(user,flag1);
                     if(flag){
                         break;
                     }
                 case 2:
                  //   userService.myFavouriteBookList(user);

                     break;
                 case 3:
                     return;
                 default:
                     System.out.println(" In-valid Option, Please try again");
                     break;

             }
         }
    }
    static void handleSearchBookActions() {
        while(true){
            System.out.println("\n");
            System.out.printf("%41s %32s %32s %20s %n", " 1. Search book by name", "2. Search book by author", "3. Search Book By Keyword","4. Back");
            System.out.print(" \n\n Enter your choice : ");
            int userChoice = scanner.nextInt();
            System.out.println("\n");
            switch (userChoice) {
                case 1:
                    System.out.print(" Enter the name of a book : ");
                    String bName = sc.nextLine();
                    System.out.println();
                    int ch = searchBookWithName(bName);
                    if(ch == 0){
                        System.out.println("\n Book named with "+bName+ " is currently not available in library\n");
                        break;
                    }else {
                        break;
                    }
                case 2:
                    System.out.print(" Enter Author name : ");
                    String name = sc.nextLine();
                    System.out.println();
                    List<Book> book =  bookService.searchBookWithAuthorName(name);
                    if(book == null){
                        System.out.println(" No records found with the author named\n "+name);
                        return;
                    }else {
                        System.out.printf("%110s %n", "* <== ** <=== *** <====  LIST OF BOOKS ====> *** ===> ** ==> *\n");
                        designLine();
                        System.out.println();
                        System.out.printf("%34s %24s %23s %25s %25s %n", "Name", "Author", "Book Id", "Status", "Available Books\n");
                        for (Book b : book) {
                            String authors = String.join(", ", b.getAuthor());
                            System.out.printf("%38s %20s %21s %30s %15s %n", b.getName(), authors, b.getBid(), b.getBookStatus(), b.getNoOfBooks());
                        }
                        System.out.println();
                        designLine();
                        break;
                    }
                case 3:
                    System.out.print(" Enter a keyword : ");
                    String keyword = sc.nextLine();
                    System.out.println();
                    List<Book> bookList = bookService.searchBookWithKeywords(keyword);
                    if(bookList.isEmpty() || bookList == null){
                        System.out.println(" No records Found..!");
                        return;
                    }
                    System.out.printf("%110s %n", "* <== ** <=== *** <====  LIST OF BOOKS ====> *** ===> ** ==> *\n");
                    designLine();
                    System.out.println();
                    System.out.printf("%34s %24s %23s %25s %25s %n", "Name", "Author", "Book Id", "Status", "Available Books\n");
                    for (Book b : bookList) {
                        String authors = String.join(", ", b.getAuthor());
                        System.out.printf("%38s %20s %21s %30s %15s %n", b.getName(), authors, b.getBid(), b.getBookStatus(), b.getNoOfBooks());
                    }
                    System.out.println();
                    designLine();
                    break;
                case 4:
                    // Back to Main menu
                    return;
                default:
                    System.out.println(" Under Construction. Please choose a valid option.");
            }
        }
    }
    //library
    static void handleBookActions(User user) throws ParseException {
        while (true) {

            System.out.printf("%45s %35s %45s %n", " 1. Borrow book", "2. Return book", "3. Back to Main menu");
            System.out.print("\n Enter your choice : ");
            int userChoice = scanner.nextInt();
            System.out.println("\n");
            switch (userChoice) {
                case 1:
                    // Borrow book
                    System.out.println();
                    System.out.printf("%113s %n", "* <== ** <=== *** <====  LIST OF AVAILABLE BOOKS ====> *** ===> ** ==> *");
                    designLine();
                    System.out.printf("%34s %24s %23s %25s %25s %n", "Name", "Author", "Book Id", "Status", "Available Books\n");
                    int c = availableBooks();
                    if(c==0){
                        break;
                    }
                    System.out.println();
                    designLine();
                    System.out.print("\n Enter the Book Id of the book you want to borrow : ");
                    int borrowBid = scanner.nextInt();
                    String borrowResult = borrowBook(borrowBid,user);
                    System.out.println("\n"+borrowResult+"\n");
                    break;
                case 2:
                    // Return book
                    myBorrowedBooks(user,false);
                    System.out.print(" Enter the Book Id of the book you want to return : ");
                    int returnBid = scanner.nextInt();
                    String returnResult = bookService.returnBook(returnBid);
                    System.out.println("\n"+returnResult+"\n");
                    break;
                case 3:
                    // Back to Main menu
                    designLine();
                    return;
                default:
                    System.out.println(" Invalid choice. Please choose a valid option.");
            }
        }
    }
    public static void main(String[] args) throws ParseException {
        // final Logger logger = LoggerFactory.getLogger(Main.class);
        bookService = new BookServiceImpl(userService,libraryService);
        userService = new UserServiceImpl(bookService);
        libraryService = new LibraryServiceImpl(bookService,userService);
        boolean isRunning = true;
        while (isRunning) {
            int ch = loginUI();
            switch (ch) {
                case 1:
                    boolean flag;
                    User user = loginToSystem(null,false);
                    if (user == null) {
                        break;
                    }
                    dashboard(isRunning, user);
                    break;
                case 2:
                    appInstructions();
                    designLine();
                    user = registerNewUser();
                    if (user != null) {
                        System.out.print("  Would you like to proceed with login? Please enter [Y] for Yes, or [N] if you would like to login later : ");
                        char c = sc.next().charAt(0);
                        if (c == 'Y' || c == 'y') {
                            flag = true;
                            loginToSystem(user, flag);
                        }
                        if(c == 'N' || c=='n'){
                            return;
                        }
                    }else {
                        System.out.println("  Try again :) ");
                        break;
                    }
                    dashboard(isRunning, user);
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