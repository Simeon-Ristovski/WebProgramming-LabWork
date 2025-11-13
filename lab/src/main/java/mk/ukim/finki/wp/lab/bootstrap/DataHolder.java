package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books;
    public static List<Author> authors;
    public static List<BookReservation> reservations;

    @PostConstruct
    public void DataHolder() {
        authors=new ArrayList<>();
        Author author1=new Author("George", "Orwell", "United Kingdom", "George Orwell was an English novelist, essayist, journalist, and critic, best known for '1984' and 'Animal Farm'.");
        Author author2=new Author("Haruki", "Murakami", "Japan", "Haruki Murakami is a Japanese writer known for his surreal narratives and novels such as 'Kafka on the Shore' and 'Norwegian Wood'.");
        Author author3=new Author("Isabel", "Allende", "Chile", "Isabel Allende is a Chilean author famous for her novels blending magical realism and historical fiction, including 'The House of the Spirits'.");
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);


        books=new ArrayList<>();
        books.add(new Book("The Hobbit", "Fantasy", 4.8,author3));
        books.add(new Book("1984", "Dystopian", 4.7,author2));
        books.add(new Book("Pride and Prejudice", "Romance", 4.5,author3));
        books.add(new Book("To Kill a Mockingbird", "Classic", 4.9,author1));
        books.add(new Book("The Great Gatsby", "Classic", 4.4,author1));
        books.add(new Book("Harry Potter", "Fantasy", 4.7,author3));
        books.add(new Book("The Da Vinci Code", "Thriller", 4.3,author2));
        books.add(new Book("The Catcher in the Rye", "Classic", 4.2,author1));
        books.add(new Book("The Alchemist", "Adventure", 4.6,author2));
        books.add(new Book("Moby Dick", "Adventure", 4.1,author3));
        reservations = new ArrayList<>();
    }
}
