package Final;

import Final.PlayerData.Player;
import Final.SeasonData.DefenceSeason;
import Final.SeasonData.GoalScorerSeason;
import Final.SeasonData.GoalieSeason;
import Final.SeasonData.Season;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ACIT 2515 Final Exam
 * @author Gagan Heer, A00933997
 * @date April 18, 2017
 */
public class CSVParser {

    public static final int PLAYER_NAME = 0;
    public static final int PLAYER_POSITION = 1;
    public static final int PLAYER_BIRTH_DATE = 2;
    public static final int PLAYER_BIRTH_PLACE = 3;
    public static final int PLAYER_HEIGHT = 4;
    public static final int PLAYER_WEIGHT = 5;
    public static final int PLAYER_SHOOTS = 6;
    public static final String DEFENSE = "defense";
    public static final String GOALIE = "goalie";
    public static final int SEASON_YEAR = 0;
    public static final int SEASON_TEAM = 1;
    public static final int SEASON_GAMES = 2;
    public static final int SEASON_GOALS = 3;
    public static final int SEASON_ASSISTS = 4;
    public static final int SEASON_POINTS = 5;
    public static final int SEASON_PENALTY = 6;
    public static final int SEASON_PLUS_MINUS = 7;
    public static final int SEASON_SHUTOUTS = 3;
    public static final int SEASON_GOALS_AGAINST = 4;
    public static final int SEASON_SAVE_PERCENTAGE = 5;

    private String fileName;
    private String[] playerData;
    private String[] seasonData;
    private Map<Player, ArrayList<Season>> seasonsByPlayer = new HashMap<>();

    public CSVParser(String fileName) {
        this.fileName = fileName;
    }

    public Map<Player, ArrayList<Season>> parseFile(){
        Scanner scan = null;
        File statsFile = new File(fileName);
        try {
            scan = new Scanner(statsFile);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        Player tempPlayer = null;
        ArrayList<Season> seasons = null;
        while(scan.hasNextLine()){

            if(scan.hasNext("#,,,,,,,,")) {
                scan.nextLine();
                seasons = new ArrayList<>();
                String playerLine = scan.nextLine().trim();

                playerData = playerLine.split(",");
                String name = playerData[PLAYER_NAME];
                String position = playerData[PLAYER_POSITION];
                String dateOfBirth = playerData[PLAYER_BIRTH_DATE];
                String placeOfBirth = playerData[PLAYER_BIRTH_PLACE];
                double height = Double.parseDouble(playerData[PLAYER_HEIGHT]);
                double weight = Double.parseDouble(playerData[PLAYER_WEIGHT]);
                String shoots = playerData[PLAYER_SHOOTS];
                tempPlayer = new Player(name, position, dateOfBirth, placeOfBirth, height, weight, shoots);
            } else {
                String seasonLine = scan.nextLine().trim();
                seasonData = seasonLine.split(",");
                String season = seasonData[SEASON_YEAR];
                String team = seasonData[SEASON_TEAM];
                int gamesPlayed = Integer.parseInt(seasonData[SEASON_GAMES]);
                Season tempSeason;

                if (tempPlayer.getPosition().equalsIgnoreCase(DEFENSE)) {
                    int goals = Integer.parseInt(seasonData[SEASON_GOALS]);
                    int assists = Integer.parseInt(seasonData[SEASON_ASSISTS]);
                    int points = Integer.parseInt(seasonData[SEASON_POINTS]);
                    int penaltyMinutes = Integer.parseInt(seasonData[SEASON_PENALTY]);
                    int plusMinus = Integer.parseInt(seasonData[SEASON_PLUS_MINUS]);
                    tempSeason = new DefenceSeason(season, team, gamesPlayed, goals, assists, points, penaltyMinutes, plusMinus);

                } else if (tempPlayer.getPosition().equalsIgnoreCase(GOALIE)) {
                    int shutouts = Integer.parseInt(seasonData[SEASON_SHUTOUTS]);
                    double goalsAgainst = Double.parseDouble(seasonData[SEASON_GOALS_AGAINST]);
                    double savePercentage = Double.parseDouble(seasonData[SEASON_SAVE_PERCENTAGE]);
                    tempSeason = new GoalieSeason(season, team, gamesPlayed, shutouts, goalsAgainst, savePercentage);

                } else {
                    int goals = Integer.parseInt(seasonData[SEASON_GOALS]);
                    int assists = Integer.parseInt(seasonData[SEASON_ASSISTS]);
                    int points = Integer.parseInt(seasonData[SEASON_POINTS]);
                    int penaltyMinutes = Integer.parseInt(seasonData[SEASON_PENALTY]);
                    tempSeason = new GoalScorerSeason(season, team, gamesPlayed, goals, assists, points, penaltyMinutes);

                }
                seasons.add(tempSeason);
            }
            seasonsByPlayer.put(tempPlayer, seasons);
        }
        scan.close();
        return seasonsByPlayer;
    }
}
