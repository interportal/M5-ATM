package md.curs.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import md.curs.model.Transaction;
import md.curs.model.User;
import md.curs.repository.TransactionRepository;

@Service
@Transactional(readOnly = true)
public class TransactionService {

	TransactionRepository transactionRepository;

	@Autowired
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Transactional
	public Transaction save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public Transaction generateTransaction(BigDecimal amount, String type, User user) {

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setTransactionType(type);
		transaction.setTime(new Date());
		transaction.setUser(user);

		return transaction;
	}

	public List<Transaction> getTransactions(String query) {

		if (query == null) query = "";
		return transactionRepository.findTransactions(query);
	}
}
