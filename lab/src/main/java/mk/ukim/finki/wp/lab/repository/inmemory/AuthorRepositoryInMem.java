package mk.ukim.finki.wp.lab.repository.inmemory;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;

public interface AuthorRepositoryInMem {
    public List<Author> findAll();
    public Author findById(Long id);
    void saveAuthor(Author author);
    void deleteAuthor(Long id);
}
