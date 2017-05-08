package Final.SeasonData;

/**
 * ACIT 2515 Final Exam
 * @author Gagan Heer, A00933997
 * @date April 18, 2017
 */
public class GoalScorerSeason extends Season {

    private int goals;
    private int assists;
    private int points;
    private int penaltyMinutes;

    public GoalScorerSeason(String season, String team, int gamesPlayed, int goals, int assists, int points, int penaltyMinutes) {
        super(season, team, gamesPlayed);
        this.goals = goals;
        this.assists = assists;
        this.points = points;
        this.penaltyMinutes = penaltyMinutes;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getPoints() {
        return points;
    }

    public int getPenaltyMinutes() {
        return penaltyMinutes;
    }
}
