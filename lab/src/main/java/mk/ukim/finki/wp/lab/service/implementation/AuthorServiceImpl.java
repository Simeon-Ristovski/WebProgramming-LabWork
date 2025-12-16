package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Gender;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {
    public final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepository.saveAuthor(author);
    }

    @Override
    public void deleteAuthor(Long id) {
    authorRepository.deleteAuthor(id);
    }

    @Override
    public Author editAuthor(Long id, String name, String surname, String country, String biography, Gender gender) {
        Author author=authorRepository.findById(id);
        if(author==null ){
            return null;
        }
        author.setName(name);
        author.setName(surname);
        author.setCountry(country);
        author.setBiography(biography);
        author.setGender(gender);
        authorRepository.saveAuthor(author);
        return author;
    }
}
