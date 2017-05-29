package md.curs.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import md.curs.model.Transaction;
import md.curs.model.User;

@Repository
public class TransactionRepository {

	@PersistenceContext
	EntityManager em;

	public Transaction getTransaction(long id) {
		return em.find(Transaction.class, id);
	}

	public Transaction getTransaction(User user) {
		return em.find(Transaction.class, user);
	}

	public Transaction save(Transaction transaction) {
		return em.merge(transaction);
	}

	public List<Transaction> findTransactions(String query) {

		query = "%" + query + "%";
		return em.createQuery("FROM Transaction t WHERE t.user.firstName LIKE :query OR t.user.lastName LIKE :query",
				Transaction.class).setParameter("query", query).getResultList();

	}
}
