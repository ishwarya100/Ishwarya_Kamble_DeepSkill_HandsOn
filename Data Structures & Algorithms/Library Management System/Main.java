public class Main {

    // Array to store books
    static Book[] books = {
            new Book(101, "C Programming", "Dennis"),
            new Book(102, "Data Structures", "Mark"),
            new Book(103, "Java Programming", "James"),
            new Book(104, "Python Basics", "Guido")
    };

    // Linear Search
    static void linearSearch(String title) {

        for(Book b : books) {

            if(b.title.equalsIgnoreCase(title)) {

                System.out.println("Book Found:");
                b.display();
                return;
            }
        }

        System.out.println("Book not found");
    }

    // Binary Search
    static void binarySearch(String title) {

        int low = 0;
        int high = books.length - 1;

        while(low <= high) {

            int mid = (low + high) / 2;

            int result = books[mid].title.compareToIgnoreCase(title);

            if(result == 0) {

                System.out.println("Book Found:");
                books[mid].display();
                return;
            }

            else if(result < 0) {

                low = mid + 1;
            }

            else {

                high = mid - 1;
            }
        }

        System.out.println("Book not found");
    }
    public static void main(String[] args) {

        System.out.println("Linear Search:");
        linearSearch("Java Programming");

        System.out.println("\nBinary Search:");
        binarySearch("Java Programming");
    }
}