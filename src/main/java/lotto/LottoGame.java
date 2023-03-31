package lotto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGame {

	void start() {
		Optional<Money> optionalMoney = CommandLineView.inputMoney();
		while (optionalMoney.isEmpty()) {
			optionalMoney = CommandLineView.inputMoney();
		}

		Money money = optionalMoney.get();
		long ticketsQuantity = money.getTicketsQuantity();

		List<Lotto> tickets = IntStream.iterate(0, i -> i < ticketsQuantity, i -> i + 1)
			.mapToObj(i -> LottoMachine.generateRandomTicket())
			.collect(Collectors.toList());

		System.out.println(tickets.size() + "개를 구매했습니다.");
		for (Lotto lotto : tickets) {
			System.out.println(lotto.getLottoNumber());
		}

		Optional<Lotto> optionalWinningNumbers = CommandLineView.inputWinningNumbers();
		while (optionalWinningNumbers.isEmpty()) {
			optionalWinningNumbers = CommandLineView.inputWinningNumbers();
		}

		Optional<LottoNumber> optionalLottoNumber = CommandLineView.inputBonusNumber();
		while (optionalLottoNumber.isEmpty()) {
			optionalLottoNumber = CommandLineView.inputBonusNumber();
		}

		Lotto winningLotto = optionalWinningNumbers.get();
		LottoNumber lottoNumber = optionalLottoNumber.get();
		Map<Prize, Long> result = LottoChecker.getResult(winningLotto, lottoNumber, tickets);

		List<Prize> collect = Arrays.stream(Prize.values())
			.filter(prize -> prize != Prize.NO)
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.toList());

		for (Prize prize : collect) {
			Long quantity = result.getOrDefault(prize, 0L);
			System.out.println(prize.getDescription() + " - " + quantity + "개");
		}

		String rate = WinningCalculator.getWinningRate(result, money);
		System.out.println("총 수익률은 " + rate + "%입니다.");
	}
}
