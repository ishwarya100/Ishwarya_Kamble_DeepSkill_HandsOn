package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Bean was discovered through component scanning, not explicit XML declaration
        BookService bookService = context.getBean("bookService", BookService.class);

        System.out.println(bookService.getBookDetails(101));
    }
}
