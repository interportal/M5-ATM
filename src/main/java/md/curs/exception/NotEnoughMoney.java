package md.curs.exception;

public class NotEnoughMoney extends Exception {
	
	private static final long serialVersionUID = 2051863698316766423L;

	@Override
	public String getMessage() {
		return "Not enough money to subtract";
	}
}
