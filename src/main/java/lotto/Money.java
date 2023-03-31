package lotto;

public class Money {

	public static final String ERROR_MESSAGE = "잘못된 문자 입니다.";
	private final long money;

	public Money(String moneyString) {
		validate(moneyString);
		money = Long.parseLong(moneyString);
	}

	private void validate(String moneyString) {
		if (!moneyString.matches("[1-9]+[0-9]*0{3}")) {
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}
	}

	public long getTicketsQuantity() {
		return money / 1000;
	}

	public String getWiningRate(Long winnings) {
		float rate = Float.valueOf(winnings - money) / money;
		return String.format("%.2f", rate * 100);
	}
}
