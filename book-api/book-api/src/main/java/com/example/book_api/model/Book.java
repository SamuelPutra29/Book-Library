package com.example.book_api.model;

import jakarta.persistence.*;


@Entity
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the id
    private Integer id;
    private String title;
    private String author;

    @Column(name =  "publish_year")
    private Integer year;

    // Default Constructor (required by JPA)
    public Book() {}

    // Constructor with parameters
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
