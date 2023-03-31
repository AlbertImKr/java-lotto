package lotto;

import java.util.Map;
import java.util.Optional;

public class WinningCalculator {

	public static String getWinningRate(Map<Prize, Long> result, Money money) {
		Optional<Long> optionalWinnings = result.entrySet().stream()
			.map(eachResult -> eachResult.getKey().getWinnings() * eachResult.getValue())
			.reduce(Long::sum);
		Long winnings = optionalWinnings.orElse(0L);
		return money.getWiningRate(winnings);
	}
}
