package lotto;

public enum Prize {
	FIRST(Constants.FIRST_MATCH_COUNT, 2_000_000_000, "6개 일치 (2,000,000,000원)"),
	BONUS(Constants.BONUS_MATCH_COUNT, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
	SECOND(Constants.SECOND_MATCH_COUNT, 1_500_000, "5개 일치 (1,500,000원)"),
	THIRD(Constants.THIRD_MATCH_COUNT, 50_000, "4개 일치 (50,000원)"),
	FOURTH(Constants.FOURTH_MATCH_COUNT, 5_000, "3개 일치 (5,000원)"),
	NO(0, 0, "0원");

	private final int matchCount;
	private final int winnings;
	private final String description;

	Prize(int matchCount, int winnings, String description) {
		this.matchCount = matchCount;
		this.winnings = winnings;
		this.description = description;
	}

	public static Prize getPrize(int matchCount) {
		switch (matchCount) {
			case Constants.FIRST_MATCH_COUNT:
				return FIRST;
			case Constants.BONUS_MATCH_COUNT:
				return SECOND;
			case Constants.THIRD_MATCH_COUNT:
				return THIRD;
			case Constants.FOURTH_MATCH_COUNT:
				return FOURTH;
			default:
				return NO;
		}
	}

	public int getWinnings() {
		return winnings;
	}

	public String getDescription() {
		return description;
	}

	private static class Constants {
		public static final int FIRST_MATCH_COUNT = 6;
		public static final int SECOND_MATCH_COUNT = 5;
		public static final int BONUS_MATCH_COUNT = SECOND_MATCH_COUNT;
		public static final int THIRD_MATCH_COUNT = 4;
		public static final int FOURTH_MATCH_COUNT = 3;
	}
}
