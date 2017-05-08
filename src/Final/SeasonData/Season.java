package Final.SeasonData;

/**
 * ACIT 2515 Final Exam
 * @author Gagan Heer, A00933997
 * @date April 18, 2017
 */
public class Season {

    private String season;
    private String team;
    private int gamesPlayed;

    public Season(String season, String team, int gamesPlayed) {
        this.season = season;
        this.team = team;
        this.gamesPlayed = gamesPlayed;
    }

    public String getSeason() {
        return season;
    }

    public String getTeam() {
        return team;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }
}
