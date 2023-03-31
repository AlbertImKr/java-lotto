package lotto;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGame {

	void start() {
		Money money = getMoney();

		List<Lotto> tickets = buyTickets(money);

		printTickets(tickets);

		Lotto winningLotto = getWinningNumbers();

		LottoNumber bonusLottoNumber = getBonusLottoNumber();

		Map<Prize, Long> result = getResult(tickets, winningLotto, bonusLottoNumber);

		printResult(result);

		printWinningRate(money, result);
	}

	private static Money getMoney() {
		Optional<Money> optionalMoney = CommandLineView.inputMoney();
		while (optionalMoney.isEmpty()) {
			optionalMoney = CommandLineView.inputMoney();
		}
		return optionalMoney.get();
	}

	private static List<Lotto> buyTickets(Money money) {
		long ticketsQuantity = money.getTicketsQuantity();
		return IntStream.iterate(0, i -> i < ticketsQuantity, i -> i + 1)
			.mapToObj(i -> LottoMachine.generateRandomTicket())
			.collect(Collectors.toList());
	}

	private static void printTickets(List<Lotto> tickets) {
		CommandLineView.printTickets(tickets);
	}

	private static Lotto getWinningNumbers() {
		Optional<Lotto> optionalWinningNumbers = CommandLineView.inputWinningNumbers();
		while (optionalWinningNumbers.isEmpty()) {
			optionalWinningNumbers = CommandLineView.inputWinningNumbers();
		}
		return optionalWinningNumbers.get();
	}

	private static LottoNumber getBonusLottoNumber() {
		Optional<LottoNumber> optionalLottoNumber = CommandLineView.inputBonusNumber();
		while (optionalLottoNumber.isEmpty()) {
			optionalLottoNumber = CommandLineView.inputBonusNumber();
		}
		return optionalLottoNumber.get();
	}

	private static Map<Prize, Long> getResult(List<Lotto> tickets, Lotto winningLotto, LottoNumber bonusLottoNumber) {
		return LottoChecker.getResult(winningLotto, bonusLottoNumber, tickets);
	}

	private static void printResult(Map<Prize, Long> result) {
		CommandLineView.printResult(result);
	}

	private static void printWinningRate(Money money, Map<Prize, Long> result) {
		String rate = WinningCalculator.getWinningRate(result, money);
		CommandLineView.printWiningRate(rate);
	}
}
