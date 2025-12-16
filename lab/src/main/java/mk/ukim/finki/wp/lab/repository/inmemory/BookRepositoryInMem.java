package mk.ukim.finki.wp.lab.repository.inmemory;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookRepositoryInMem {
    List<Book> findAll();
    Book findById(Long id);
    List<Book> searchBooks(String text, Double rating);
    void save(Book book);
    void delete(Long id);
}
