package md.curs.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import md.curs.enums.CurrencyEnum;
import md.curs.exception.NotEnoughMoney;
import md.curs.model.Deposit;
import md.curs.model.Transaction;
import md.curs.model.User;
import md.curs.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUser(long id) {
		return userRepository.getUser(id);
	}

	/**
	 * Add a new user or update an existing one depending on the provided
	 * user.id
	 *
	 * @param user
	 *            The user to save
	 */
	@Transactional
	public void saveUser(User user) {

		userRepository.save(user);
	}

	@Transactional
	public void depositMoney(User user, CurrencyEnum currency, BigDecimal amount) {

		// Add money to deposit
		for (Deposit deposit : user.getDeposit()) {

			CurrencyEnum currencyFromDeposit = deposit.getCurrency();

			if (currencyFromDeposit.equals(currency)) {
				deposit.setAmount(deposit.getAmount().add(amount));
			}

		}

		// Save data
		saveUser(user);
	}

	@Transactional
	public void withdrawMoney(User user, CurrencyEnum currency, BigDecimal amount) throws NotEnoughMoney {

		// Withdraw money from deposit
		for (Deposit deposit : user.getDeposit()) {

			CurrencyEnum currencyFromDeposit = deposit.getCurrency();

			if (currencyFromDeposit.equals(currency)) {
				
				if (hasDepositMoney(deposit.getAmount(), amount)) {
					deposit.setAmount(deposit.getAmount().subtract(amount));
				} else {
					throw new NotEnoughMoney();
				}
			}
		}
		
		// Save data
		saveUser(user);
	}
	
	private boolean hasDepositMoney(BigDecimal depositAmount, BigDecimal amountToSubtract) {

		return depositAmount.compareTo(amountToSubtract) >= 1 || depositAmount.compareTo(amountToSubtract) == 0;		
	}
	
}
