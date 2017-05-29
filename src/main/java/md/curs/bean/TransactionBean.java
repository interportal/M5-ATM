package md.curs.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import md.curs.model.Transaction;
import md.curs.service.TransactionService;

@ManagedBean
@ViewScoped
public class TransactionBean {

	@ManagedProperty("#{transactionService}")
	TransactionService transactionService;
	
	private String searchQuery;
	private List<Transaction> transactionList;

	@PostConstruct
    public void init() {
        search();
    }

    public void search() {
        this.transactionList = transactionService.getTransactions(searchQuery);
    }
	
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}
}
