package library_management_system;
import java.util.Scanner;

class Book {
    int id;
    String title;
    boolean isIssued;
    int lastUserId;           
    String lastAction;         

    Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
        this.lastUserId = -1;
        this.lastAction = "None";
    }
}

class User {
    int userId;
    String name;

    User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

class Library {
    Book[] books;
    int booksCount;
    User[] users;
    int usersCount;

    Library(int bookCapacity, int userCapacity) {
        books = new Book[bookCapacity];
        booksCount = 0;
        users = new User[userCapacity];
        usersCount = 0;
    }

    void addBook(Book book) {
        if (booksCount < books.length) {
            books[booksCount++] = book;
        }
    }

    void addUser(User user) {
        if (usersCount < users.length) {
            users[usersCount++] = user;
        }
    }

    User getUserById(int userId) {
        for (int i = 0; i < usersCount; i++) {
            if (users[i].userId == userId)
                return users[i];
        }
        return null;
    }

    void issueBook(int bookId, int userId) {
        for (int i = 0; i < booksCount; i++) {
            if (books[i].id == bookId && !books[i].isIssued) {
                books[i].isIssued = true;
                books[i].lastUserId = userId;
                books[i].lastAction = "Issued";
                System.out.println("Book issued!");
                return;
            }
        }
        System.out.println("Book not available.");
    }

    void returnBook(int bookId, int userId) {
        for (int i = 0; i < booksCount; i++) {
            if (books[i].id == bookId && books[i].isIssued) {
                books[i].isIssued = false;
                books[i].lastUserId = userId;
                books[i].lastAction = "Returned";
                System.out.println("Book returned!");
                return;
            }
        }
        System.out.println("Book not found or not issued.");
    }

    void showBookDetails(int bookId) {
        for (int i = 0; i < booksCount; i++) {
            if (books[i].id == bookId) {
                String status = books[i].isIssued ? "Issued" : "Available";
                String userName = "None";
                if (books[i].lastUserId != -1) {
                    User user = getUserById(books[i].lastUserId);
                    if (user != null) userName = user.name;
                }
                System.out.println("Book ID: " + books[i].id +
                                   ", Book Name: " + books[i].title +
                                   ", Status: " + status +
                                   ", Last " + books[i].lastAction + " by: " + userName);
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

public class library_management_system {
    public static void main(String[] args) {
        Library lib = new Library(10, 10);

        Book b1 = new Book(1, "Java Basic Book");
        Book b2 = new Book(2, "Java Intermediate Book");
        Book b3 = new Book(3, "Java Advanced Book");
        
        lib.addBook(b1);
        lib.addBook(b2);
        lib.addBook(b3);

        User u1 = new User(101, "user1");
        User u2 = new User(102, "user2");
        User u3 = new User(103, "user3");
        
        lib.addUser(u1);
        lib.addUser(u2);
        lib.addUser(u3);

        lib.issueBook(1, 101);   
        lib.returnBook(1, 101);  
        
        lib.issueBook(2, 102);
        lib.issueBook(2, 102);
        
        lib.issueBook(3, 103);
        lib.issueBook(3, 103);
        

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID to search: ");
        int searchId = sc.nextInt();

        lib.showBookDetails(searchId); 
        sc.close();
    }
}