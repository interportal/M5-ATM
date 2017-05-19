package md.curs.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import md.curs.enums.CurrencyEnum;

@Embeddable
public class Deposit {

	@Enumerated(EnumType.STRING)
	private CurrencyEnum currency;
	private BigDecimal amount;

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
