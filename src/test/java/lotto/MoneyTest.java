package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

	@DisplayName("티켓 개수를 return")
	@ParameterizedTest
	@CsvSource(value = {"1000,1", "9000,9", "200000,200"})
	void getTicketsQuantity(String moneyString, int exceptCounts) {
		Money money = new Money(moneyString);
		assertThat(money.getTicketsQuantity()).isEqualTo(exceptCounts);
	}

	@DisplayName("수익율 계산 결과를 return")
	@ParameterizedTest
	@CsvSource(value = {"1000,1000,0.00", "1000,0,-100.00", "1000,10000,900.00"})
	void getWiningRate(String moneyString, long winnings, String except) {
		Money money = new Money(moneyString);
		assertThat(money.getWiningRate(winnings)).isEqualTo(except);
	}
}
