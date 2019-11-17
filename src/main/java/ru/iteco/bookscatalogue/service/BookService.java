package ru.iteco.bookscatalogue.service;

import ru.iteco.bookscatalogue.view.AddBookForm;
import ru.iteco.bookscatalogue.view.AuthorView;
import ru.iteco.bookscatalogue.view.BookView;

import java.util.List;

/**
 * Сервис для работы с книгами
 */
public interface BookService {

    /**
     * Получить последние добавленные книги
     *
     * @param count кол-во последних книг
     */
    List<BookView> getLastAddedBooksList(int count);

    /**
     * Получить все книги автора
     *
     * @param authorId идентификатор автора
     */
    List<BookView> getBooksByAuthorId(Long authorId);

    /**
     * Получить все книги
     *
     * @return список всех книг
     */
    List<BookView> getAllBooks();

    /**
     * Получить список книг по наименованию
     *
     * @param name наименование
     * @return список книг
     */
    List<BookView> findBookByName(String name);

    /**
     * Получить книгу по идентификатору
     *
     * @param id идентификатор
     * @return книга
     */
    BookView getBookById(Long id);

    /**
     * Получить аторов книги
     *
     * @param bookId идентификатор книги
     * @return список авторов книги
     */
    List<AuthorView> getBookAuthorsByBookId(Long bookId);

    /**
     * Сохранить книгу и автора
     *
     * @param addBookForm данные из форм заполненных на фронте
     */
    void saveBookAndAuthor(AddBookForm addBookForm);
}
