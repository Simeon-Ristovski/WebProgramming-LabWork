package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text,rating);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void save(Book book) {
       bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
       bookRepository.delete(id);
    }

    @Override
    public Book edit(Long id, String title, String genre, double averageRating, Long authorId) {
        Book book=bookRepository.findById(id);
        Author author= authorRepository.findById(id);
        if(book==null || author==null){
            return null;
        }
        book.setAuthor(author);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setTitle(title);
        bookRepository.save(book);
        return book;
    }
}
