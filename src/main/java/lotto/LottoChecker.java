package lotto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoChecker {
	public static Map<Prize, Long> getResult(Lotto winningLotto, LottoNumber lottoNumber, List<Lotto> tickets) {
		return tickets.stream()
			.map(ticket -> LottoChecker.getResult(winningLotto, lottoNumber, ticket))
			.collect(Collectors.groupingBy(prize -> prize, Collectors.counting()));
	}

	private static Prize getResult(Lotto winningLotto, LottoNumber lottoNumber, Lotto ticket) {
		int matchCount = winningLotto.getMatchCount(ticket);
		Prize prize = Prize.getPrize(matchCount);
		if (prize == Prize.SECOND && ticket.contains(lottoNumber)) {
			return Prize.BONUS;
		}
		return prize;
	}
}
