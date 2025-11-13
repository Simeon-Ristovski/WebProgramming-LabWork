package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    Book findById(Long id);

    void save(Book book);
    void delete(Long id);
    Book edit(Long id, String title, String genre, double averageRating, Long authorId);

}
