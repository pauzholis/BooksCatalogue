package ru.iteco.bookscatalogue.dao;

import org.springframework.stereotype.Repository;
import ru.iteco.bookscatalogue.model.Author;
import ru.iteco.bookscatalogue.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getLastBooksList() {
        String queryString = String.format("SELECT b FROM %s b ORDER BY b.id DESC", Book.class.getSimpleName());
        TypedQuery<Book> query = em.createQuery(queryString, Book.class);
        query.setMaxResults(10);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getBooksByAuthorId(Long authorId) {
        Author author = em.find(Author.class, authorId);
        return new ArrayList<>(author.getBooks());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getAllBooks() {
        String queryString = String.format("SELECT b FROM %s b ORDER BY b.name", Book.class.getSimpleName());
        TypedQuery<Book> query = em.createQuery(queryString, Book.class);
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
        TypedQuery<Book> query = em.createQuery(queryString, Book.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Book getBookById(Long id) {
        return em.find(Book.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveBookAndAuthor(Book book, Author author) {
        em.persist(book);
        em.persist(author);
    }

    @Override
    public Book getBookByName(String name) {
        String queryString = String.format(
                "SELECT b FROM %s b WHERE b.name = :name ", Book.class.getSimpleName()
        );
        TypedQuery<Book> query = em.createQuery(queryString, Book.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
