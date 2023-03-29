package lotto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicketMachine {

	private final static List<LottoNumber> allLottoNumbers = IntStream.range(1, 46)
		.mapToObj(LottoNumber::new)
		.collect(Collectors.toList());

	private TicketMachine() {

	}

	public static Ticket generateRandomTicket() {
		Collections.shuffle(allLottoNumbers);
		List<LottoNumber> lottoNumbers = allLottoNumbers.subList(1, 6);
		lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getLottoNumber));
		return new Ticket(lottoNumbers.stream().collect(Collectors.toSet()));
	}
}
