import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages books and members and provides library operations.
 * @author Maverick Guinto
 * @verson 1.0
 */
public class Library {

    private HashMap<String, Book> booksByIsbn;
    private HashMap<String, Book> booksByTitle;
    private HashMap<String, ArrayList<Book>> booksByAuthor;
    private HashMap<String, Member> members;
    private int nextMemberNumber;

    /**
     * Creates an empty library with no books or members.
     */
    public Library() {
        booksByIsbn = new HashMap<>();
        booksByTitle = new HashMap<>();
        booksByAuthor = new HashMap<>();
        members = new HashMap<>();
        nextMemberNumber = 1;
    }

    /**
     * Adds a book or updates copies if it already exists.
     *
     * @param title  the book title
     * @param author the book author
     * @param isbn   the book ISBN
     * @param copies number of copies to add
     */
    public void addBook(String title, String author, String isbn, int copies) {
        String titleKey = title.toLowerCase();

        Book existing = booksByIsbn.get(isbn);
        if (existing != null
                && existing.getTitle().equals(title)
                && existing.getAuthor().equals(author)) {

            existing.addCopies(copies);
            System.out.println("Book already exists. Updated the number of copies in the library. " +
                    "Copies available for borrowing: " + existing.getAvailableCopies());
            return;
        }

        Book book = new Book(title, author, isbn, copies);
        booksByIsbn.put(isbn, book);
        booksByTitle.put(titleKey, book);

        String authorKey = author.toLowerCase();
        booksByAuthor.putIfAbsent(authorKey, new ArrayList<>());
        booksByAuthor.get(authorKey).add(book);

        System.out.println("Book added: " + title + " by " + author +
                " (ISBN: " + isbn + ").");
        System.out.println("Copies available for borrowing: " + book.getAvailableCopies());
    }

    /**
     * Adds a new member and assigns a new member ID.
     *
     * @param name the name of the member
     */
    public void addMember(String name) {
        String id = "M" + nextMemberNumber;
        nextMemberNumber++;

        Member member = new Member(id, name);
        members.put(id, member);

        System.out.println("Member added: " + name + " (Member ID: " + id + ")");
    }

    /**
     * Searches for a book by title and prints the result.
     *
     * @param title the book title to search
     */
    public void searchByTitle(String title) {
        Book book = booksByTitle.get(title.toLowerCase());
        if (book != null) {
            System.out.println("Book found: " + book.toString());
        } else {
            System.out.println("No book found with the title: " + title);
        }
    }

    /**
     * Searches for a book by ISBN and prints the result.
     *
     * @param isbn the ISBN to search
     */
    public void searchByIsbn(String isbn) {
        Book book = booksByIsbn.get(isbn);
        if (book != null) {
            System.out.println("Book found: " + book.toString());
        } else {
            System.out.println("No book found with the ISBN: " + isbn);
        }
    }

    /**
     * Searches for books by author and prints all matches.
     *
     * @param author the author name to search
     */
    public void searchByAuthor(String author) {
        String authorKey = author.toLowerCase();
        ArrayList<Book> books = booksByAuthor.get(authorKey);

        if (books == null || books.isEmpty()) {
            System.out.println("No books found by " + author);
            return;
        }

        System.out.println("Books by " + author + ":");
        for (Book b : books) {
            System.out.println(b.getTitle() + " (ISBN: " + b.getIsbn() + "). " +
                    "Copies available for borrowing: " + b.getAvailableCopies());
        }
    }

    /**
     * Issues a book with the given title to a member.
     *
     * @param memberId the member ID
     * @param title    the title of the book to issue
     */
    public void issueBook(String memberId, String title) {
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Member ID not found.");
            return;
        }

        Book book = booksByTitle.get(title.toLowerCase());
        if (book == null) {
            System.out.println("Book not found with title: " + title);
            return;
        }

        String isbn = book.getIsbn();

        if (member.hasBorrowed(isbn)) {
            System.out.println(memberId + " have already borrowed this book: " + book.getTitle());
            System.out.println("Could not issue the book.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("This book is currently unavailable. Could not issue the book.");
            return;
        }

        book.borrowCopy();
        member.borrowBook(isbn);
        System.out.println("Book issued: " + book.getTitle() + " to " + memberId + ".");
    }

    /**
     * Returns a book with the given title from a member.
     *
     * @param memberId the member ID
     * @param title    the title of the book to return
     */
    public void returnBook(String memberId, String title) {
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Member ID not found.");
            return;
        }

        Book book = booksByTitle.get(title.toLowerCase());
        if (book == null) {
            System.out.println("Book not found with title: " + title);
            return;
        }

        String isbn = book.getIsbn();

        if (!member.hasBorrowed(isbn)) {
            System.out.println(memberId + " has not borrowed this book: " +
                    book.getTitle() + " by " + book.getAuthor() + ".");
            return;
        }

        member.returnBook(isbn);
        book.returnCopy();

        System.out.println("Book returned: " + book.getTitle() +
                " by " + book.getAuthor() +
                (book.getIsbn() != null ? " (ISBN: " + book.getIsbn() + ")." : "."));
    }

    /**
     * Lists all books currently borrowed by a member.
     *
     * @param memberId the member ID
     */
    public void listBorrowedBooks(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Member ID not found.");
            return;
        }

        System.out.println(memberId + " borrowed books:");
        if (member.getBorrowedIsbns().isEmpty()) {
            System.out.println("No books borrowed.");
            return;
        }

        for (String isbn : member.getBorrowedIsbns()) {
            Book b = booksByIsbn.get(isbn);
            if (b != null) {
                System.out.println(b.getTitle() + " (ISBN: " + b.getIsbn() + ").");
            }
        }
    }
}
