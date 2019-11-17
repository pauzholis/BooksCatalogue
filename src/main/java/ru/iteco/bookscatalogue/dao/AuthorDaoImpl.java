package ru.iteco.bookscatalogue.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iteco.bookscatalogue.model.Author;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public AuthorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Author> getAllAuthors() {
        String queryString = String.format("SELECT a FROM %s a ORDER BY a.lastName", Author.class.getSimpleName());
        Query<Author> query = getSession().createQuery(queryString, Author.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Author> getAuthorsByLastName(String lastName) {
        String queryString = String.format(
                "SELECT a FROM %s a WHERE LOWER(a.lastName) LIKE LOWER(:lastName) ", Author.class.getSimpleName()
        );
        Query<Author> query = getSession().createQuery(queryString, Author.class);

        query.setParameter("lastName", "%" + lastName + "%");
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author getAuthorById(Long id) {
        return getSession().get(Author.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author getAuthor(String lastName, String firstName, String middleName) {
        String queryString = String.format(
                "SELECT a FROM %s a " +
                        "WHERE a.lastName = :lastName AND a.firstName = :firstName AND a.middleName = :middleName",
                Author.class.getSimpleName()
        );
        Query<Author> query = getSession().createQuery(queryString, Author.class);
        query.setParameter("lastName", lastName);
        query.setParameter("firstName", firstName);
        query.setParameter("middleName", middleName);
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
