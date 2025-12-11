import java.util.HashSet;

/**
 * Represents a library member who can borrow books.
 * @author Maverick Guinto
 * @verson 1.0
 */
public class Member {

    private String memberId;
    private String name;
    private HashSet<String> borrowedIsbns;

    /**
     * Creates a new member with an ID and name.
     *
     * @param memberId the ID of the member
     * @param name     the name of the member
     */
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedIsbns = new HashSet<>();
    }

    /**
     * Returns the member ID.
     *
     * @return the member ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * Returns the member's name.
     *
     * @return the member name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns true if the member has already borrowed a book with this ISBN.
     *
     * @param isbn the ISBN to check
     * @return true if already borrowed
     */
    public boolean hasBorrowed(String isbn) {
        return borrowedIsbns.contains(isbn);
    }

    /**
     * Records that the member has borrowed a book.
     *
     * @param isbn the ISBN of the borrowed book
     */
    public void borrowBook(String isbn) {
        borrowedIsbns.add(isbn);
    }

    /**
     * Records that the member has returned a book.
     *
     * @param isbn the ISBN of the returned book
     */
    public void returnBook(String isbn) {
        borrowedIsbns.remove(isbn);
    }

    /**
     * Returns the set of ISBNs the member has borrowed.
     *
     * @return set of borrowed ISBNs
     */
    public HashSet<String> getBorrowedIsbns() {
        return borrowedIsbns;
    }
}
