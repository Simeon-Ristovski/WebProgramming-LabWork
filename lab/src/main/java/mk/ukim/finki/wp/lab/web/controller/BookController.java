package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("")
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        List<Book> books = bookService.listAll();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/add-form")
    public String addBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        Book book = new Book(title, genre, averageRating, authorService.findById(authorId).orElseThrow());
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/{id}")
    public String editBook(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
       bookService.edit(id,title,genre, averageRating,authorId);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/book-form")
    public String getAddBookPage(Model model){
        List<Author> authors=authorService.findAll();
        model.addAttribute("authors",authors);
        return "book-form";
    }

    @GetMapping("/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model){
        Book book=bookService.findById(id);
        List<Author> authors=authorService.findAll();
        if(book!=null){
            model.addAttribute("book",book);
            model.addAttribute("authors",authors);
            return "book-form";
        }else {
            return "redirect:/books?error=BookNotFound";
        }
    }
    @GetMapping("/by-author/{authorId}")
    public String getBooksByAuthor(@PathVariable Long authorId, Model model) {
        List<Book> books = bookService.findAllByAuthorId(authorId);
        model.addAttribute("books", books);
        return "listBooks";
    }



}
