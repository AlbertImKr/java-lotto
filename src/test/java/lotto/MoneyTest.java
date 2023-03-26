package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

	@DisplayName("잘못된 금액 문자일 때 에러를 던진다.")
	@ParameterizedTest
	@ValueSource(strings = {"100", "900", "1210", "aaaa", "11aaa", "ac1111"})
	void generateFailed(String moneyString) {
		assertThatThrownBy(() -> new Money(moneyString)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage(Money.ERROR_MESSAGE);
	}

	@DisplayName("정확한 금액 문자일 때 객체가 생성된다.")
	@ParameterizedTest
	@ValueSource(strings = {"1000", "9000", "121000"})
	void generateSuccess(String moneyString) {
		assertThat(new Money(moneyString)).hasFieldOrPropertyWithValue("money", Long.parseLong(moneyString));
	}
}
