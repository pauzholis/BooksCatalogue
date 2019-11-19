package ru.iteco.bookscatalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iteco.bookscatalogue.dao.AuthorDao;
import ru.iteco.bookscatalogue.model.Author;
import ru.iteco.bookscatalogue.model.Book;
import ru.iteco.bookscatalogue.view.AuthorView;
import ru.iteco.bookscatalogue.view.BookView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class WebAuthorService implements AuthorService {

    private final AuthorDao authorDao;

    @Autowired
    public WebAuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<AuthorView> getAllAuthors() {
        List<Author> authors = authorDao.getAllAuthors();
        if (authors != null) {
            return authorViewMapper(authors);
        } else {
            throw new IllegalStateException("Не удалось получить список авторов");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<AuthorView> findAuthorsByLastName(String lastName) {
        List<Author> authors = authorDao.getAuthorsByLastName(lastName);
        if (authors != null) {
            return authorViewMapper(authors);
        } else {
            throw new IllegalStateException("Произошла ошибка при поиске автора");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public AuthorView getAuthorById(Long id) {
        Author author = authorDao.getAuthorById(id);
        if (author != null) {
            return new AuthorView(author.getId(), author.getLastName(), author.getFirstName(), author.getMiddleName());
        } else {
            throw new IllegalStateException("Невозможно найти автора по идентификатору " + id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<BookView> getAuthorBooksByAuthorId(Long authorId) {
        Author author = authorDao.getAuthorById(authorId);
        if (author != null) {
            List<Book> authors = new ArrayList<>(author.getBooks());
            Function<Book, BookView> mapBook = b -> {
                BookView bookView = new BookView();
                bookView.id = b.getId();
                bookView.name = b.getName();
                return bookView;
            };
            return authors.stream().map(mapBook).collect(Collectors.toList());
        } else {
            throw new IllegalStateException("Невозможно найти автора по идентификатору " + authorId);
        }
    }

    /**
     * Преобразование списка авторов - сущностей в список авторов - представлений
     *
     * @param authors список авторов - сущностей
     * @return список авторов - представлений
     */
    private List<AuthorView> authorViewMapper(List<Author> authors) {
        Function<Author, AuthorView> mapAuthor = a -> {
            AuthorView authorView = new AuthorView();
            authorView.id = a.getId();
            authorView.lastName = a.getLastName();
            authorView.firstName = a.getFirstName();
            authorView.middleName = a.getMiddleName();
            return authorView;
        };
        return authors.stream().map(mapAuthor).collect(Collectors.toList());
    }
}
