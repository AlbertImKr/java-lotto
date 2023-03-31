package lotto;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningCalculatorTest {

	@DisplayName("결과들의 수익율을 계산")
	@ParameterizedTest
	@CsvSource(value = {"BONUS,SECOND,1000", "FIRST,SECOND,5000"})
	void getWinningRate(Prize firstPrize, Prize secondPrize, int purchase) {
		Map<Prize, Long> result = new HashMap<>();
		result.put(firstPrize, 1L);
		result.put(secondPrize, 1L);
		Assertions.assertThat(WinningCalculator.getWinningRate(result, new Money(String.valueOf(purchase))))
			.isEqualTo(
				String.format("%.2f",
					((float)(firstPrize.getWinnings() + secondPrize.getWinnings() - purchase)) / purchase * 100));
	}
}
