package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Gender;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("")
    public String getAuthorPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "listAuthors";
    }

    @GetMapping("/add-author-form")
    public String addAuthorPage(Model model) {
        model.addAttribute("genders",List.of(Gender.MALE,Gender.FEMALE));
        return "author-form";
    }

    @PostMapping("")
    public String saveAuthor(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String  country,
                           @RequestParam String  biography,
                           @RequestParam Gender gender) {
        Author author = new Author( name,  surname,  country,  biography, gender);
       authorService.saveAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/edit-author-form/{id}")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        Author author= authorService.findById(id).orElseThrow();
        model.addAttribute("author", author);
        model.addAttribute("genders",List.of(Gender.MALE,Gender.FEMALE));
        return "author-form";
    }

    @PostMapping("/{id}")
    public String editAuthor(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String  country,
                             @RequestParam String  biography,
                             @RequestParam Gender gender) {
        authorService.editAuthor(id,name,surname, country,biography,gender);
        return "redirect:/authors";
    }

    @GetMapping("/delete-author/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }

//    @GetMapping("/author-form")
//    public String getAddAuthorPage(Model model){
//        List<Author> authors=authorService.findAll();
//        model.addAttribute("authors",authors);
//        return "book-form";
//    }
//
//    @GetMapping("/book-form/{id}")
//    public String getEditBookForm(@PathVariable Long id, Model model){
//        Book book=bookService.findById(id);
//        List<Author> authors=authorService.findAll();
//        if(book!=null){
//            model.addAttribute("book",book);
//            model.addAttribute("authors",authors);
//            return "book-form";
//        }else {
//            return "redirect:/books?error=BookNotFound";
//        }
//    }




}
