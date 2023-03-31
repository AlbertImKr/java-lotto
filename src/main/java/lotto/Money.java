package lotto;

public class Money {

	public static final String ERROR_MESSAGE = "잘못된 문자 입니다.";
	public static final String MONEY_REGEX = "[1-9]+[0-9]*0{3}";
	public static final int TICKET_PRICE = 1000;
	public static final String PERCENT_REGEX = "%.2f";
	public static final int HUNDRED = 100;
	private final long money;

	public Money(String moneyString) {
		validate(moneyString);
		money = Long.parseLong(moneyString);
	}

	private void validate(String moneyString) {
		if (!moneyString.matches(MONEY_REGEX)) {
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}
	}

	public long getTicketsQuantity() {
		return money / TICKET_PRICE;
	}

	public String getWiningRate(Long winnings) {
		float rate = (float)(winnings - money) / money;
		return String.format(PERCENT_REGEX, rate * HUNDRED);
	}
}
