package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public String findBookById(int id) {
        return "Book{id=" + id + ", title=Effective Java}";
    }
}
