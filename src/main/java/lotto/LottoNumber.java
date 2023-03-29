package lotto;

import java.util.Objects;

public final class LottoNumber {

	public static final String RANGE_ERROR = "로또 번호 범위를 초과 했습니다.";
	public static final String TYPE_ERROR = "생성하려고 하는 로또번호가 정혹하지 않습니다.";
	private final int lottoNumber;

	public LottoNumber(int lottoNumber) {
		validate(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	public static LottoNumber generateByString(String bonusNumberString) {
		if (!bonusNumberString.matches("^[1-9][0-9]?$")) {
			throw new IllegalArgumentException(TYPE_ERROR);
		}
		return new LottoNumber(Integer.parseInt(bonusNumberString));
	}

	private void validate(int lottoNumber) {
		if (lottoNumber < 1 || lottoNumber > 45) {
			throw new IllegalArgumentException(RANGE_ERROR);
		}
	}

	public int getLottoNumber() {
		return lottoNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof LottoNumber))
			return false;
		LottoNumber that = (LottoNumber)o;
		return getLottoNumber() == that.getLottoNumber();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLottoNumber());
	}
}
