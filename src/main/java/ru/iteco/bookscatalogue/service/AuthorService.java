package ru.iteco.bookscatalogue.service;

import ru.iteco.bookscatalogue.view.AuthorView;
import ru.iteco.bookscatalogue.view.BookView;

import java.util.List;

/**
 * Сервис для работы с авторами
 */
public interface AuthorService {

    /**
     * Получить всех авторов
     *
     * @return список всех авторов
     */
    List<AuthorView> getAllAuthors();

    /**
     * Получить всех авторов по фамилии
     *
     * @param lastName фамилия автора
     * @return список авторов найденных по фамилии
     */
    List<AuthorView> findAuthorsByLastName(String lastName);

    /**
     * Получить автора по идентификатору
     *
     * @param id идентификатор
     * @return автор
     */
    AuthorView getAuthorById(Long id);

    /**
     * Получить книги автора
     *
     * @param authorId идентификатор автора
     * @return список книг автора
     */
    List<BookView> getAuthorBooksByAuthorId(Long authorId);
}
