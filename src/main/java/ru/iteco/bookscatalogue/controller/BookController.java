package ru.iteco.bookscatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.iteco.bookscatalogue.service.BookService;
import ru.iteco.bookscatalogue.view.AddBookForm;
import ru.iteco.bookscatalogue.view.BookView;

/**
 * Описание контроллеров работающих с запросами по книгам
 */
@Controller
@RequestMapping(value = "/")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Возвращает название html файла для отображения страницы книг
     *
     * @param model {@link Model}
     * @return название html файла
     */
    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String getAllBooks(Model model) {
        model.addAttribute("allBooks", bookService.getAllBooks());
        model.addAttribute("bookView", new BookView());
        return "books";
    }

    /**
     * Возвращает название html файла для отображения найденных книг
     *
     * @param model {@link Model}
     * @return название html файла
     */
    @RequestMapping(value = "findBooks/submit", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String findBook(Model model, @ModelAttribute("bookView") BookView bookView) {
        String name = bookView.name;
        if (StringUtils.isEmpty(name)) {
            model.addAttribute("errorMessage", "Заполните поле поиска");
            model.addAttribute("allBooks", bookService.getAllBooks());
            model.addAttribute("bookView", new BookView());
            return "books";
        } else {
            model.addAttribute("allBooks", bookService.findBookByName(name));
            return "books";
        }
    }

    /**
     * Возвращает название html файла для отображения книг автора
     *
     * @param model {@link Model}
     * @return название html файла
     */
    @RequestMapping(value = "findBooks/{authorId}", method = RequestMethod.GET)
    public String findBooksByAuthorId(Model model, @PathVariable("authorId") Long authorId) {
        if (authorId <= 0) {
            throw new IllegalStateException("Идентификатор автора не может быть меньше или равен 0");
        } else {
            model.addAttribute("booksByAuthorList", bookService.getBooksByAuthorId(authorId));
            return "booksByAuthor";
        }
    }

    /**
     * Возвращает название html файла для отображения информации о книге
     *
     * @param model {@link Model}
     * @return название html файла
     */
    @RequestMapping(value = "findBook/{id}", method = RequestMethod.GET)
    public String findBookById(Model model, @PathVariable("id") Long id) {
        if (id <= 0) {
            throw new IllegalStateException("Идентификатор книги не может быть меньше или равен 0");
        } else {
            model.addAttribute("bookById", bookService.getBookById(id));
            model.addAttribute("bookAuthors", bookService.getBookAuthorsByBookId(id));
            return "bookInfo";
        }
    }

    /**
     * Возвращает название html файла для отображения страницы добавления книг
     *
     * @param model {@link Model}
     * @return название html файла
     */
    @RequestMapping("addBookPage")
    public String getAddBookPage(Model model) {
        model.addAttribute("addBookForm", new AddBookForm());
        return "addBook";
    }

    /**
     * Если все формы заполнены, то перенаправляет на страницу с книгами, иначе выдает сообщение с ошибкой
     *
     * @param model       {@link Model}
     * @param addBookForm Объект с полями книги и автора
     * @return название html файла
     */
    @RequestMapping(value = "/addBook/submit", method = RequestMethod.POST)
    public String saveBook(Model model, @ModelAttribute("addBookForm") AddBookForm addBookForm) {
        String bookName = addBookForm.bookName;
        String lastName = addBookForm.lastName;
        String firstName = addBookForm.firstName;
        String middleName = addBookForm.middleName;

        if (!StringUtils.isEmpty(bookName) && !StringUtils.isEmpty(lastName) && !StringUtils.isEmpty(firstName)
                && !StringUtils.isEmpty(middleName)) {
            bookService.saveBookAndAuthor(addBookForm);
            return "redirect:/books";
        }

        model.addAttribute("errorMessage", "Заполните все поля");
        return "addBook";
    }
}
