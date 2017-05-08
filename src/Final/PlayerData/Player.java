package Final.PlayerData;

/**
 * ACIT 2515 Final Exam
 * @author Gagan Heer, A00933997
 * @date April 18, 2017
 */
public class Player {

    private String name;
    private String position;
    private String dateOfBirth;
    private String placeOfBirth;
    private double height;
    private double weight;
    private String shoots;

    public Player(String name, String position, String dateOfBirth, String placeOfBirth, double height, double weight, String shoots) {
        this.name = name;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.height = height;
        this.weight = weight;
        this.shoots = shoots;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getShoots() {
        return shoots;
    }
}
