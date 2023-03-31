package lotto;

public enum Prize {
	FIRST(6, 2_000_000_000, "6개 일치 (2,000,000,000원)"), BONUS(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"), SECOND(5,
		1_500_000,"5개 일치 (1,500,000원)"), THIRD(4, 50_000,"4개 일치 (50,000원)"), FOURTH(3, 5_000,"3개 일치 (5,000원)"), NO(0, 0,
		"0원"),;

	private final int matchCount;
	private final int winnings;
	private final String description;

	Prize(int matchCount, int winnings,String description) {
		this.matchCount = matchCount;
		this.winnings = winnings;
		this.description = description;
	}

	public static Prize getPrize(int matchCount) {
		switch (matchCount) {
			case 6:
				return FIRST;
			case 5:
				return SECOND;
			case 4:
				return THIRD;
			case 3:
				return FOURTH;
			default:
				return NO;
		}
	}

	public int getMatchCount() {
		return matchCount;
	}

	public int getWinnings() {
		return winnings;
	}

	public String getDescription() {
		return description;
	}
}
