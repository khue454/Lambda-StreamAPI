package lambdastreamapi.exercise2;

public class Programmer {
	private String id;

	private String name;

	private String division;

	private String team;

	private Gender gender;

	enum Gender {
		MALE, FEMALE
	}

	public Programmer() {
	}

	public Programmer(String id, String name, String division, String team, Gender gender) {
		this.id = id;
		this.name = name;
		this.division = division;
		this.team = team;
		this.gender = gender;
	}

	public String toString() {
		String programmer = String.format("Programmer: [id: %-5s, name: %-15s, division: %-7s, team: %-13s, gender: %-6s]",
				id, name, division, team, gender.toString());
		return programmer;
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
