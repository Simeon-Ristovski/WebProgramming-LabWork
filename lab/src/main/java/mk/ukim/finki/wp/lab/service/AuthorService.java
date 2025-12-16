package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Gender;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> findAll();
    public Optional<Author> findById(Long id);



    void saveAuthor(Author author);
    void deleteAuthor(Long id);
    Author editAuthor(Long id, String name, String surname, String country, String biography, Gender gender);
}
