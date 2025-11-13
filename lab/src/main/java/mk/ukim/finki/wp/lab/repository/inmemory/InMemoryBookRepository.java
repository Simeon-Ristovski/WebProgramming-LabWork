package mk.ukim.finki.wp.lab.repository.inmemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository implements BookRepository {

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public Book findById(Long id) {
        return DataHolder.books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(text.toLowerCase()) &&
                        book.getAverageRating() >= rating)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Book book) {
        // Ако book има ID, заменуваме постоечки
        if (book.getId() != null) {
            Book existing = findById(book.getId());
            if (existing != null) {
                DataHolder.books.remove(existing);
            }
        } else {
            // Генерираме ID ако нема
            long nextId = DataHolder.books.stream().mapToLong(Book::getId).max().orElse(0) + 1;
            book.setId(nextId);
        }
        DataHolder.books.add(book);
    }

    @Override
    public void delete(Long id) {
        Book book = findById(id);
        if (book != null) {
            DataHolder.books.remove(book);
        }
    }
}
