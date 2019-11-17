package ru.iteco.bookscatalogue.dao;

import ru.iteco.bookscatalogue.model.Author;
import ru.iteco.bookscatalogue.model.Book;

import java.util.List;

/**
 * Описание методов для работы с таблицей книг
 */
public interface BookDao {

    /**
     * Получить 10 последних книг
     *
     * @param count кол-во последних загруженных книг
     * @return список последних загруженных книг
     */
    List<Book> getLastBooksList(int count);

    /**
     * Получить все книги автора по идентификатору автора
     *
     * @param authorId идентификатор автора
     * @return список книг автора
     */
    List<Book> getBooksByAuthorId(Long authorId);

    /**
     * Получить все книги
     *
     * @return список всех книг
     */
    List<Book> getAllBooks();

    /**
     * Получить книги по наименованию
     *
     * @param name наименование
     * @return список книг
     */
    List<Book> getBooksByName(String name);

    /**
     * Получить книгу по идентификатору
     *
     * @param id идентификатор
     * @return книга
     */
    Book getBookById(Long id);

    /**
     * Сохранить книгу и автора
     *
     * @param book   книга
     * @param author автор
     */
    void saveBookAndAuthor(Book book, Author author);

    /**
     * Получить книгу по наименованию
     *
     * @param name наименование книги
     * @return книга
     */
    Book getBookByName(String name);
}
