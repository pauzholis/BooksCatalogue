package ru.iteco.bookscatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.iteco.bookscatalogue.service.AuthorService;
import ru.iteco.bookscatalogue.view.AuthorView;

/**
 * Описание контроллеров работающих с запросами по авторам
 */
@Controller
@RequestMapping(value = "/")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Возвращает название html файла для отображения списка всех авторов
     *
     * @param model {@link Model}
     * @return название html файла
     */
    @RequestMapping(value = "authors", method = RequestMethod.GET)
    public String getAllAuthors(Model model) {
        model.addAttribute("allAuthors", authorService.getAllAuthors());
        model.addAttribute("authorView", new AuthorView());
        return "authors";
    }

    /**
     * Возвращает название html файла для отображения найденных авторов
     *
     * @param model {@link Model}
     * @return название html файла
     */
    @RequestMapping(value = "findAuthors/submit", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String findAuthor(Model model, @ModelAttribute("authorView") AuthorView authorView) {
        String lastName = authorView.lastName;
        model.addAttribute("allAuthors", authorService.findAuthorsByLastName(lastName));
        return "authors";
    }

    /**
     * Возвращает название html файла для отображения информации об авторе
     *
     * @param model {@link Model}
     * @return название html файла
     */
    @RequestMapping(value = "findAuthor/{id}", method = RequestMethod.GET)
    public String findAuthorById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("authorById", authorService.getAuthorById(id));
        model.addAttribute("authorBooks", authorService.getAuthorBooksByAuthorId(id));
        return "authorInfo";
    }
}
