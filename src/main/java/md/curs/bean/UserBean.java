package md.curs.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import md.curs.enums.CurrencyEnum;
import md.curs.model.Deposit;
import md.curs.model.User;
import md.curs.service.UserService;

@ManagedBean
@ViewScoped
public class UserBean {

	@ManagedProperty("#{userService}")
	private UserService userService;
	private User user;
	private Long id;
	private CurrencyEnum currency;
	private BigDecimal money;

	
	public void initUser() {
		user = userService.getUser(id);
	}

	public void depositMoney() {
		
		userService.depositMoney(user, currency, money);
		
		Messages.add("growl-deposit", new FacesMessage("Money has been added"));
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
