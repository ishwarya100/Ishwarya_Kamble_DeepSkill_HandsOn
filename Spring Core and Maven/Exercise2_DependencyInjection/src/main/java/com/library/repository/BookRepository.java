package com.library.repository;

public class BookRepository {

    public String findBookById(int id) {
        // Returns dummy book data to simulate a data source
        return "Book{id=" + id + ", title=Effective Java}";
    }
}
