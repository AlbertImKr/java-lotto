package lotto;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PrizeTest {

	@DisplayName("matchCount와 일치한 Prize 타입을 return 한다")
	@ParameterizedTest
	@CsvSource(value = {"FIRST,6", "SECOND,5", "THIRD,4", "FOURTH,3", "NO,2", "NO,1", "NO,0"})
	void getPrize(Prize prize, int matchCount) {
		assertThat(Prize.getPrize(matchCount)).isEqualTo(prize);
	}
}
