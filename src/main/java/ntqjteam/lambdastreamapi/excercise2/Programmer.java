package ntqjteam.lambdastreamapi.excercise2;

/**
 * Created by toan on 3/1/17.
 */
public class Programmer {

    private String id;

    private String name;

    private String division;

    private String team;

    private Gender gender;

    public enum Gender {
        MALE,
        FEMALE
    }

    public Programmer(String id, String name, String division, String team, Gender gender) {
        this.id = id;
        this.name = name;
        this.division = division;
        this.team = team;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDivision() {
        return division;
    }

    public String getTeam() {
        return team;
    }

    public Gender getGender() {
        return gender;
    }

}
