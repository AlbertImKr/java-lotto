package lotto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Lotto {

	public static final String SIZE_ERROR = "로또 번호는 서로 다르고 6개 이여야 합니다.";
	private final Set<LottoNumber> numbers;

	public Lotto(Set<LottoNumber> numbers) {
		validate(numbers);
		this.numbers = numbers;
	}

	private void validate(Set<LottoNumber> lottoNumber) {
		if (lottoNumber.size() != 6) {
			throw new IllegalArgumentException(SIZE_ERROR);
		}
	}

	public String getLottoNumber() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(numbers);
		lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getLottoNumber));
		return lottoNumbers.toString();
	}

	public boolean contains(LottoNumber lottoNumber) {
		return numbers.contains(lottoNumber);
	}

}
