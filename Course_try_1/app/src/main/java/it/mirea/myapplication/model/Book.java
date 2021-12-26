package it.mirea.myapplication.model;

public class Book {

    int id;
    String number_of_pages;
    String type;
    String title;
    String img;
    String description;
    String text;


    public Book(int id, String number_of_pages, String type, String title, String img, String description, String text) {
        this.id = id;
        this.number_of_pages = number_of_pages;
        this.type = type;
        this.title = title;
        this.img = img;
        this.description = description;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(String number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
