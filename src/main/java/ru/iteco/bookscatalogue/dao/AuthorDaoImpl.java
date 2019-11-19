package ru.iteco.bookscatalogue.dao;

import org.springframework.stereotype.Repository;
import ru.iteco.bookscatalogue.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Author> getAllAuthors() {
        String queryString = String.format("SELECT a FROM %s a ORDER BY a.lastName", Author.class.getSimpleName());
        TypedQuery<Author> query = em.createQuery(queryString, Author.class);
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
        TypedQuery<Author> query = em.createQuery(queryString, Author.class);
        query.setParameter("lastName", "%" + lastName + "%");
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author getAuthorById(Long id) {
        return em.find(Author.class, id);
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
        TypedQuery<Author> query = em.createQuery(queryString, Author.class);
        query.setParameter("lastName", lastName);
        query.setParameter("firstName", firstName);
        query.setParameter("middleName", middleName);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
