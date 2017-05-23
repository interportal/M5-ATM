package md.curs.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import md.curs.model.User;

@Repository
public class UserRepository {

	@PersistenceContext
	EntityManager em;
	
	
	/**
     * Gets an User by it's ID
     *
     * @param id
     * @return The found user or {@code null} if not found
     */
    public User getUser(long id) {
        return em.find(User.class, id);
    }
}
