package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Gender;


import mk.ukim.finki.wp.lab.repository.mock.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
    authorRepository.deleteById(id);
    }

    @Override
    public Author editAuthor(Long id, String name, String surname, String country, String biography, Gender gender) {
        Author author=authorRepository.findById(id).orElseThrow();
        if(author==null ){
            return null;
        }
        author.setName(name);
        author.setName(surname);
        author.setCountry(country);
        author.setBiography(biography);
        author.setGender(gender);
        authorRepository.save(author);
        return author;
    }
}
