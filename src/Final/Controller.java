package Final;

import Final.PlayerData.Player;
import Final.SeasonData.DefenceSeason;
import Final.SeasonData.GoalScorerSeason;
import Final.SeasonData.GoalieSeason;
import Final.SeasonData.Season;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * ACIT 2515 Final Exam
 * @author Gagan Heer, A00933997
 * @date April 18, 2017
 */

public class Controller{
    @FXML
    private ListView<String> playerList;
    @FXML
    private ListView<String> seasonList;
    @FXML
    private Label seasonInfoLabel;
    @FXML
    private Label playerInfoLabel;
    @FXML
    private Label seasonTeamLabel;
    @FXML
    private Label playerNameLabel;
    @FXML
    private AnchorPane playerPane;
    @FXML
    private AnchorPane seasonPane;

    private ObservableList<String> playerData = FXCollections.observableArrayList();
    private ObservableList<String> seasonData = FXCollections.observableArrayList();
    private Map<Player, ArrayList<Season>> seasonByPlayer;
    private ArrayList<Season> seasons;
    private Set<Player> playerKeys;
    private Player player;
    private String fileName;

    public void handleOpenAction(ActionEvent e){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            fileName = file.toString();
            CSVParser csvParser = new CSVParser(fileName);
            seasonByPlayer = csvParser.parseFile();
            playerKeys = seasonByPlayer.keySet();
            selectPlayer();
            selectSeason();
        }
    }

    private void selectPlayer(){
        for(Player tempPlayer : playerKeys){
            playerData.add(tempPlayer.getName());
        }
        playerList.setItems(playerData);
        playerList.getSelectionModel().selectedItemProperty().addListener((observable, lastPlayer, selectedPlayer) -> {
            seasonPane.setVisible(false);
            playerPane.setVisible(true);
            seasonList.getItems().removeAll(seasonList.getItems());
            fillPlayerLabel(selectedPlayer);
            for(Season tempSeason : seasons){
                seasonData.add(tempSeason.getSeason() + " " + tempSeason.getTeam());
            }
        });
    }

    private void selectSeason(){
        seasonList.setItems(seasonData);
        seasonList.getSelectionModel().selectedItemProperty().addListener((observable, lastSeason, selectedSeason) -> {
            seasonPane.setVisible(true);
            fillSeasonLabel(selectedSeason);
        });
    }

    private void fillPlayerLabel(String selectedPlayer){
        for(Player tempPlayer : playerKeys){
            if(tempPlayer.getName().equals(selectedPlayer)){
                seasons = seasonByPlayer.get(tempPlayer);
                player = tempPlayer;

                String position = player.getPosition();
                String place = player.getPlaceOfBirth();
                String date = player.getDateOfBirth();
                Double height = player.getHeight();
                Double weight = player.getWeight();
                String shoots = player.getShoots();

                playerNameLabel.setText(selectedPlayer);
                playerInfoLabel.setText("Position: " + position + "\nPlace of Birth: " + place + "\nDate of Birth: " +
                        date + "\nHeight: " + height + " Weight: " + weight + "\nShoots: " + shoots);
            }
        }
    }

    private void fillSeasonLabel(String selectedSeason){
        for(Season tempSeason : seasons){
            String seasonDetails = tempSeason.getSeason() + " " + tempSeason.getTeam();
            if(seasonDetails.equals(selectedSeason)){
                seasonTeamLabel.setText(seasonDetails);
                Integer games = tempSeason.getGamesPlayed();

                if(player.getPosition().equalsIgnoreCase(CSVParser.GOALIE)){
                    GoalieSeason goalieSeason = (GoalieSeason)tempSeason;
                    Integer shutouts = goalieSeason.getShutouts();
                    Double savePercentage = goalieSeason.getSavePercentage();
                    Double goalsAgainst = goalieSeason.getGoalsAgainst();

                    seasonInfoLabel.setText("Number of Games Played: " + games.toString() + "\nNumber of Shutouts: " + shutouts.toString() +
                            "\nGoals Against: " + goalsAgainst + "\nSave Percentage: " + savePercentage);
                } else if (player.getPosition().equalsIgnoreCase(CSVParser.DEFENSE)){
                    DefenceSeason defenseSeason = (DefenceSeason)tempSeason;
                    Integer goals = defenseSeason.getGoals();
                    Integer assists = defenseSeason.getAssists();
                    Integer points = defenseSeason.getPoints();
                    Integer penaltyMinutes = defenseSeason.getPenaltyMinutes();
                    Integer plusMinus = defenseSeason.getPlusMinus();

                    seasonInfoLabel.setText("Number of Games Played: " + games.toString() + "\nGoals: " + goals.toString() +
                            "\nAssists: " + assists.toString() + "\nPoints: " + points.toString() + "\nPenalty Minutes: " +
                            penaltyMinutes.toString() + "\nPlus Minus: " + plusMinus.toString());
                } else {
                    GoalScorerSeason goalScorerSeason = (GoalScorerSeason) tempSeason;
                    Integer goals = goalScorerSeason.getGoals();
                    Integer assists = goalScorerSeason.getAssists();
                    Integer points = goalScorerSeason.getPoints();
                    Integer penaltyMinutes = goalScorerSeason.getPenaltyMinutes();

                    seasonInfoLabel.setText("Number of Games Played: " + games.toString() + "\nGoals: " + goals.toString() +
                            "\nAssists: " + assists.toString() + "\nPoints: " + points.toString() + "\nPenalty Minutes: " + penaltyMinutes.toString());
                }
            }
        }
    }
}
