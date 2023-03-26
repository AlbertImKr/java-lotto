package lotto;

public final class LottoNumber {

	public static final String RANGE_ERROR = "로또 번호 범위를 초과 했습니다.";
	private final int lottoNumber;

	public LottoNumber(int lottoNumber) {
		validate(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	private void validate(int lottoNumber) {
		if (lottoNumber < 1 || lottoNumber > 45) {
			throw new IllegalArgumentException(RANGE_ERROR);
		}
	}

	public int getLottoNumber() {
		return lottoNumber;
	}
}
