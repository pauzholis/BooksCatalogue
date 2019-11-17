package ru.iteco.bookscatalogue.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iteco.bookscatalogue.model.Author;
import ru.iteco.bookscatalogue.model.Book;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class BookDaoImpl implements BookDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getLastBooksList(int count) {
        String queryString = String.format("SELECT b FROM %s b ORDER BY b.id DESC", Book.class.getSimpleName());
        Query<Book> query = getSession().createQuery(queryString, Book.class);
        query.setMaxResults(count);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getBooksByAuthorId(Long authorId) {
        Author author = getSession().get(Author.class, authorId);
        return new ArrayList<>(author.getBooks());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getAllBooks() {
        String queryString = String.format("SELECT b FROM %s b ORDER BY b.name", Book.class.getSimpleName());
        Query<Book> query = getSession().createQuery(queryString, Book.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getBooksByName(String name) {
        String queryString = String.format(
                "SELECT b FROM %s b WHERE LOWER(b.name) LIKE LOWER(:name) ", Book.class.getSimpleName()
        );
        Query<Book> query = getSession().createQuery(queryString, Book.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Book getBookById(Long id) {
        return getSession().get(Book.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveBookAndAuthor(Book book, Author author) {
        Session session = getSession();
        session.persist(book);
        session.persist(author);
    }

    @Override
    public Book getBookByName(String name) {
        String queryString = String.format(
                "SELECT b FROM %s b WHERE b.name = :name ", Book.class.getSimpleName()
        );
        Query<Book> query = getSession().createQuery(queryString, Book.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Получить текущую сессию
     *
     * @return текущая ссессия
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
