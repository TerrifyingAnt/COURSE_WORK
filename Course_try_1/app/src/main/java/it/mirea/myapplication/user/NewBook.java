package it.mirea.myapplication.user;

import java.io.File;

public class NewBook {
    private File book;
    private String name;
    private String author;
    private String category;

    /**
     *
     * @param book - файл с книгой
     * @param name - имя книги
     * @param author - имя автора
     * @param category - категория книги
     */
    public NewBook(File book, String name, String author, String category) {
        this.book = book;
        this.name = name;
        this.author = author;
        this.category = category;
    }

    public File getBook() {
        return book;
    }

    public void setBook(File book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
