import java.util.Scanner;

/**
 * Entry point class that provides a text menu for the library system.
 * @author Maverick Guinto
 * @verson 1.0
 */
public class LibraryApp {

    /**
     * Runs the library management system menu loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;

        while (running) {
            printMainMenu();
            String line = sc.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddBook(sc, library);
                    break;
                case 2:
                    handleAddMember(sc, library);
                    break;
                case 3:
                    handleSearchBook(sc, library);
                    break;
                case 4:
                    handleIssueBook(sc, library);
                    break;
                case 5:
                    handleReturnBook(sc, library);
                    break;
                case 6:
                    handleListBorrowed(sc, library);
                    break;
                case 7:
                    System.out.println("Exiting. . .");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    /**
     * Prints the main menu options to the console.
     */
    private static void printMainMenu() {
        System.out.println("Library Management System");
        System.out.println("1. Add Book");
        System.out.println("2. Add Member");
        System.out.println("3. Search Book");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. List Borrowed Books");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Handles user input for adding a new book.
     *
     * @param sc      scanner for input
     * @param library the library instance
     */
    private static void handleAddBook(Scanner sc, Library library) {
        System.out.print("Enter book title: ");
        String title = sc.nextLine().trim();

        System.out.print("Enter book author: ");
        String author = sc.nextLine().trim();

        System.out.print("Enter book ISBN: ");
        String isbn = sc.nextLine().trim();

        System.out.print("Enter number of copies: ");
        String copiesLine = sc.nextLine().trim();

        int copies;
        try {
            copies = Integer.parseInt(copiesLine);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of copies.");
            return;
        }

        library.addBook(title, author, isbn, copies);
    }

    /**
     * Handles user input for adding a new member.
     *
     * @param sc      scanner for input
     * @param library the library instance
     */
    private static void handleAddMember(Scanner sc, Library library) {
        System.out.print("Enter member name: ");
        String name = sc.nextLine().trim();
        library.addMember(name);
    }

    /**
     * Handles user input for searching for a book.
     *
     * @param sc      scanner for input
     * @param library the library instance
     */
    private static void handleSearchBook(Scanner sc, Library library) {
        System.out.println("Search Book");
        System.out.println("1. By Title");
        System.out.println("2. By ISBN");
        System.out.println("3. By Author");
        System.out.print("Choose an option: ");

        String line = sc.nextLine().trim();
        int searchChoice;
        try {
            searchChoice = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        switch (searchChoice) {
            case 1:
                System.out.print("Enter title: ");
                String title = sc.nextLine().trim();
                library.searchByTitle(title);
                break;
            case 2:
                System.out.print("Enter ISBN: ");
                String isbn = sc.nextLine().trim();
                library.searchByIsbn(isbn);
                break;
            case 3:
                System.out.print("Enter author: ");
                String author = sc.nextLine().trim();
                library.searchByAuthor(author);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Handles user input for issuing a book to a member.
     *
     * @param sc      scanner for input
     * @param library the library instance
     */
    private static void handleIssueBook(Scanner sc, Library library) {
        System.out.print("Enter member ID: ");
        String memberId = sc.nextLine().trim();

        System.out.print("Enter book title: ");
        String title = sc.nextLine().trim();

        library.issueBook(memberId, title);
    }

    /**
     * Handles user input for returning a book from a member.
     *
     * @param sc      scanner for input
     * @param library the library instance
     */
    private static void handleReturnBook(Scanner sc, Library library) {
        System.out.print("Enter member ID: ");
        String memberId = sc.nextLine().trim();

        System.out.print("Enter book title: ");
        String title = sc.nextLine().trim();

        library.returnBook(memberId, title);
    }

    /**
     * Handles user input for listing a member's borrowed books.
     *
     * @param sc      scanner for input
     * @param library the library instance
     */
    private static void handleListBorrowed(Scanner sc, Library library) {
        System.out.print("Enter member ID: ");
        String memberId = sc.nextLine().trim();

        library.listBorrowedBooks(memberId);
    }
}
