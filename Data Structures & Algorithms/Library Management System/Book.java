// Class to store book details
class Book {

    int bookId;
    String title;
    String author;

    Book(int bookId, String title, String author) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    void display() {

        System.out.println(bookId + " " + title + " " + author);
    }
}