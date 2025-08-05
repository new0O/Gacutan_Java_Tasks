package tasks.Task11;

public class Book {
    private String title;
    private String author;
    private int yearPublished;
    private double price;
    public Book(String title, String author, int yearPublished, double price) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.price = price;
    }
    @Override
    public String toString() {
        return "Title: \"" + this.title + "\"\n" +
               "Author: \"" + this.author + "\"\n" +
               "Year Published: " + this.yearPublished + "\n" +
               "Price: $" + String.format("%.2f", this.price); 
    }
}
