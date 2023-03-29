package lotto;

import java.util.Set;

public class Ticket {
	public static final String SIZE_ERROR = "당첨번호는 서로 다르고 6개 이여야 합니다.";
	private final Set<LottoNumber> Ticket;

	public Ticket(Set<LottoNumber> winningNumbers) {
		validateSize(winningNumbers);
		this.Ticket = winningNumbers;
	}

	private void validateSize(Set<LottoNumber> winningNumbers) {
		if (winningNumbers.size() != 6) {
			throw new IllegalArgumentException(SIZE_ERROR);
		}
	}
}
