/**
 * Represents a book in the library with title, author, ISBN, and copy counts.
 * @author Maverick Guinto
 * @verson 1.0
 */
public class Book {

    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int availableCopies;

    /**
     * Creates a new book with the given information and initial copies.
     *
     * @param title  the book title
     * @param author the book author
     * @param isbn   the book ISBN
     * @param copies the number of copies to add
     */
    public Book(String title, String author, String isbn, int copies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.totalCopies = copies;
        this.availableCopies = copies;
    }

    /**
     * Returns the title of this book.
     *
     * @return the book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of this book.
     *
     * @return the book author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the ISBN of this book.
     *
     * @return the book ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the total number of copies for this book.
     *
     * @return total copies
     */
    public int getTotalCopies() {
        return totalCopies;
    }

    /**
     * Returns the number of available copies for borrowing.
     *
     * @return available copies
     */
    public int getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Adds extra copies of this book to the library.
     *
     * @param extra the number of copies to add
     */
    public void addCopies(int extra) {
        if (extra > 0) {
            totalCopies += extra;
            availableCopies += extra;
        }
    }

    /**
     * Returns true if at least one copy is available to borrow.
     *
     * @return true if a copy is available
     */
    public boolean isAvailable() {
        return availableCopies > 0;
    }

    /**
     * Borrows one copy if available and returns whether it succeeded.
     *
     * @return true if a copy was borrowed
     */
    public boolean borrowCopy() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        }
        return false;
    }

    /**
     * Returns one copy to the library if below the total count.
     */
    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    /**
     * Returns a string describing this book and its available copies.
     *
     * @return a descriptive string
     */
    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + "). " +
                "Copies available for borrowing: " + availableCopies;
    }
}
