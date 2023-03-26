package lotto;

public final class LottoNumber {

	private final int lottoNumber;

	public LottoNumber(int lottoNumber) {
		validate(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	private void validate(int lottoNumber) {
		if (lottoNumber < 1 || lottoNumber > 45) {
			throw new IllegalArgumentException("롯도 번호 범위를 초과 했습니다.");
		}
	}

	public int getLottoNumber() {
		return lottoNumber;
	}
}
