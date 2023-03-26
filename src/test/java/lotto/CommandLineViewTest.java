package lotto;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandLineViewTest {

	@DisplayName("잘못된 금액 문자 일 때 Optional.empty() 반환한다.")
	@ParameterizedTest
	@ValueSource(strings = {"100", "900", "1210", "aaaa", "11aaa", "ac1111"})
	void inputMoneyFailed(String moneyString) {
		InputStream inputStream = new ByteArrayInputStream(moneyString.getBytes());
		System.setIn(inputStream);
		assertThat(CommandLineView.inputMoney()).isEmpty();
	}

	@DisplayName("정확한 금액 문자 일 때 객체를 Optional 에 담아 반환한다.")
	@ParameterizedTest
	@ValueSource(strings = {"1000", "9000", "121000"})
	void inputMoneySuccess(String moneyString) {
		InputStream inputStream = new ByteArrayInputStream(moneyString.getBytes());
		System.setIn(inputStream);
		assertThat(CommandLineView.inputMoney()).isPresent();
	}
}
