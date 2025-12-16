package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.repository.mock.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.mock.BookRepository;
import mk.ukim.finki.wp.lab.repository.mock.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books;
    public static List<Author> authors;
    public static List<BookReservation> reservations;
    public final AuthorRepository authorRepository;
    public  final BookRepository bookRepository;
    public  final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void DataHolder() {
        if (userRepository.findAll().isEmpty()) {
            userRepository.save(new User("simeon.ristovski", passwordEncoder.encode("sr"), "Simeon", "Ristovski", Role.ROLE_USER));
            userRepository.save(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
        }
        authors=new ArrayList<>();
        Author author1=new Author("George", "Orwell", "United Kingdom", "George Orwell was an English novelist, essayist, journalist, and critic, best known for '1984' and 'Animal Farm'.", Gender.MALE);
        Author author2=new Author("Haruki", "Murakami", "Japan", "Haruki Murakami is a Japanese writer known for his surreal narratives and novels such as 'Kafka on the Shore' and 'Norwegian Wood'.",Gender.MALE);
        Author author3=new Author("Isabel", "Allende", "Chile", "Isabel Allende is a Chilean author famous for her novels blending magical realism and historical fiction, including 'The House of the Spirits'.",Gender.FEMALE);
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);


        books=new ArrayList<>();
        bookRepository.save(new Book("The Hobbit", "Fantasy", 4.8,author3));
        bookRepository.save(new Book("1984", "Dystopian", 4.7,author2));
        bookRepository.save(new Book("Pride and Prejudice", "Romance", 4.5,author3));
        bookRepository.save(new Book("To Kill a Mockingbird", "Classic", 4.9,author1));
        bookRepository.save(new Book("The Great Gatsby", "Classic", 4.4,author1));
        bookRepository.save(new Book("Harry Potter", "Fantasy", 4.7,author3));
        bookRepository.save(new Book("The Da Vinci Code", "Thriller", 4.3,author2));
        bookRepository.save(new Book("The Catcher in the Rye", "Classic", 4.2,author1));
        bookRepository.save(new Book("The Alchemist", "Adventure", 4.6,author2));
        bookRepository.save(new Book("Moby Dick", "Adventure", 4.1,author3));
        reservations = new ArrayList<>();
    }


}
