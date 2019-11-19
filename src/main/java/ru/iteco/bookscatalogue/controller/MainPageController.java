package ru.iteco.bookscatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.iteco.bookscatalogue.service.BookService;

/**
 * Описание контроллера для главной страницы
 */
@Controller
@RequestMapping(value = "/")
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Возвращает название html файла для отображения последних добавленных книг
     *
     * @param model {@link Model}
     * @return название html файла
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String getMainPageBooksList(Model model) {
        model.addAttribute("bookView", bookService.getLastAddedBooksList());
        return "index";
    }
}
