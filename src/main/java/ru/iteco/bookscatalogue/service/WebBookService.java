package ru.iteco.bookscatalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iteco.bookscatalogue.dao.AuthorDao;
import ru.iteco.bookscatalogue.dao.BookDao;
import ru.iteco.bookscatalogue.model.Author;
import ru.iteco.bookscatalogue.model.Book;
import ru.iteco.bookscatalogue.view.AddBookForm;
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
public class WebBookService implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;

    @Autowired
    public WebBookService(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<BookView> getLastAddedBooksList(int count) {
        List<Book> books = bookDao.getLastBooksList(count);
        return bookViewMapper(books);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<BookView> getBooksByAuthorId(Long authorId) {
        List<Book> books = bookDao.getBooksByAuthorId(authorId);
        return bookViewMapper(books);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<BookView> getAllBooks() {
        List<Book> books = bookDao.getAllBooks();
        return bookViewMapper(books);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<BookView> findBookByName(String name) {
        List<Book> books = bookDao.getBooksByName(name);
        return bookViewMapper(books);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public BookView getBookById(Long id) {
        Book book = bookDao.getBookById(id);
        if (book != null) {
            return new BookView(book.getId(), book.getName());
        } else {
            throw new IllegalStateException("Не найдена книга с идентификатором " + id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<AuthorView> getBookAuthorsByBookId(Long bookId) {
        Book book = bookDao.getBookById(bookId);
        if (book != null) {
            List<Author> authors = new ArrayList<>(book.getAuthors());
            Function<Author, AuthorView> mapAuthor = a -> {
                AuthorView authorView = new AuthorView();
                authorView.id = a.getId();
                authorView.lastName = a.getLastName();
                authorView.firstName = a.getFirstName();
                authorView.middleName = a.getMiddleName();
                return authorView;
            };
            return authors.stream().map(mapAuthor).collect(Collectors.toList());
        } else {
            throw new IllegalStateException("Не найдена книга с идентификатором " + bookId);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveBookAndAuthor(AddBookForm addBookForm) {
        Book book = new Book(addBookForm.bookName);
        Author author = new Author(addBookForm.lastName, addBookForm.firstName, addBookForm.middleName);

        Book bookFromDb = bookDao.getBookByName(book.getName());
        Author authorFromDb = authorDao.getAuthor(author.getLastName(), author.getFirstName(), author.getMiddleName());

        if (bookFromDb != null) {
            book = bookFromDb;
        }
        if (authorFromDb != null) {
            author = authorFromDb;
        }
        author.addBook(book);

        bookDao.saveBookAndAuthor(book, author);
    }

    /**
     * Преобразование списка книг - сущностей в список книг - представлений
     *
     * @param books список книг - сущностей
     * @return список книг - представлений
     */
    private List<BookView> bookViewMapper(List<Book> books) {
        Function<Book, BookView> mapBook = b -> {
            BookView bookView = new BookView();
            bookView.id = b.getId();
            bookView.name = b.getName();
            return bookView;
        };
        return books.stream().map(mapBook).collect(Collectors.toList());
    }
}
