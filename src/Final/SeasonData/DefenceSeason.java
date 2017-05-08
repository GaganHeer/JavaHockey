package Final.SeasonData;

/**
 * ACIT 2515 Final Exam
 * @author Gagan Heer, A00933997
 * @date April 18, 2017
 */
public class DefenceSeason extends GoalScorerSeason{

    private int plusMinus;

    public DefenceSeason(String season, String team, int gamesPlayed, int goals, int assists, int points, int penaltyMinutes, int plusMinus) {
        super(season, team, gamesPlayed, goals, assists, points, penaltyMinutes);
        this.plusMinus = plusMinus;
    }

    public int getPlusMinus() {
        return plusMinus;
    }
}
