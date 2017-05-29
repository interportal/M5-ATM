package md.curs.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import md.curs.enums.CurrencyEnum;
import md.curs.exception.NotEnoughMoney;
import md.curs.model.Deposit;
import md.curs.model.Transaction;
import md.curs.model.User;
import md.curs.service.TransactionService;
import md.curs.service.UserService;

@ManagedBean
@ViewScoped
public class UserBean {

	@ManagedProperty("#{userService}")
	private UserService userService;
	
	@ManagedProperty("#{transactionService}")
	private TransactionService transactionService;
	
	private User user;
	private Long id;
	private CurrencyEnum currency;
	private BigDecimal money;
	
	
	public void initUser() {
		user = userService.getUser(id);
	}

	public void depositMoney() {
		//Deposit money
		userService.depositMoney(user, currency, money);
		//Generate and save transaction
		Transaction transaction = transactionService.generateTransaction(money, "Deposit", user);
		transactionService.save(transaction);
		//Inform user
		Messages.addInfo("growl-deposit", "Money has been added");
	}
	
	public void withdrawMoney() {
		
		try {
			//Withdraw money
			userService.withdrawMoney(user, currency, money);
			
			//Generate and save transaction
			Transaction transaction = transactionService.generateTransaction(money, "Withdraw", user);
			transactionService.save(transaction);
			
			//Inform user
			Messages.addInfo("growl-deposit", "Money has been subtracted");
		} catch (NotEnoughMoney nem) {
			Messages.addError("growl-deposit", nem.getMessage());
		}
	}
		
	public List<Deposit> getDepositsAsList() {
		return new ArrayList<> (user.getDeposit());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}
}
