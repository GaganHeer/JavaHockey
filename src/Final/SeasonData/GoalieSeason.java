package Final.SeasonData;

/**
 * ACIT 2515 Final Exam
 * @author Gagan Heer, A00933997
 * @date April 18, 2017
 */
public class GoalieSeason extends Season{

    private int shutouts;
    private double goalsAgainst;
    private double savePercentage;

    public GoalieSeason(String season, String team, int gamesPlayed, int shutouts, double goalsAgainst, double savePercentage) {
        super(season, team, gamesPlayed);
        this.shutouts = shutouts;
        this.goalsAgainst = goalsAgainst;
        this.savePercentage = savePercentage;
    }

    public int getShutouts() {
        return shutouts;
    }

    public double getGoalsAgainst() {
        return goalsAgainst;
    }

    public double getSavePercentage() {
        return savePercentage;
    }
}
