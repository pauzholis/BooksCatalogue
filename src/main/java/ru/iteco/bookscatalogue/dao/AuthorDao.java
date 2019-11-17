package ru.iteco.bookscatalogue.dao;

import ru.iteco.bookscatalogue.model.Author;

import java.util.List;

/**
 * Описание методов для работы с таблицей авторов
 */
public interface AuthorDao {

    /**
     * Получить всех авторов
     *
     * @return список всех авторов
     */
    List<Author> getAllAuthors();

    /**
     * Получить авторов по Фамилии
     *
     * @param lastName фамилия автора
     * @return список всех авторов
     */
    List<Author> getAuthorsByLastName(String lastName);

    /**
     * Получить автора по идентификатору
     *
     * @param id идентификатор
     * @return автор
     */
    Author getAuthorById(Long id);

    /**
     * Получить автора
     *
     * @param lastName   фамилия автора
     * @param firstName  имя автора
     * @param middleName отчество автора
     * @return автор
     */
    Author getAuthor(String lastName, String firstName, String middleName);
}
