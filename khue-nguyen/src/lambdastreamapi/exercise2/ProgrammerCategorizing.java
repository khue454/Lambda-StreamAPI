package lambdastreamapi.exercise2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import lambdastreamapi.exercise2.Programmer.Gender;

public class ProgrammerCategorizing {
	
	static int programmerCounter = 0;
	static final int LIMITED_PROGRAMMER = 10;
	static final String[] TEAMS = { "Java", "JavaScript", "PHP", "Android", ".Net" };
	static final String[] DIVISIONS = { "NS1", "NS2", "NS3", "NS6" };

	static List<Programmer> loadData() {
		List<Programmer> programmers = Stream.generate(() -> {
			int id = ++programmerCounter;
			int division = (int) (Math.random() * 4);
			int team = (int) (Math.random() * 5);
			String name = "Programmer_" + id;
			Gender gender = (id + division + team) % 2 == 0 ? Gender.MALE : Gender.FEMALE;
			return new Programmer(id + "", name, DIVISIONS[division], TEAMS[team], gender);
		}).limit(LIMITED_PROGRAMMER).collect(Collectors.toList());
		return programmers;
	}

	// Categorize programmers into divisions
	@SuppressWarnings("unchecked")
	static void doTask1(List<Programmer> programmers) {
		JSONObject jsonProgrammers = new JSONObject();
		System.out.println("Categorize programmers into divisions:");

		// filter by division
		programmers.stream()
		.map(division -> division.getDivision())
		.distinct().forEach(division -> {

			// get programmer's name for each division
			JSONArray list = new JSONArray();
			programmers.stream()
			.filter(programmer -> programmer.getDivision().equals(division))
			.map(programmer -> programmer.getName()).forEach(name -> {
						list.add(name);
					});
			jsonProgrammers.put(division, list);
		});
		System.out.println(new GsonBuilder().setPrettyPrinting().create()
				.toJson(new JsonParser().parse(jsonProgrammers.toJSONString())));
	}

	// Categorize programmers into divisions, then teams, then genders
	@SuppressWarnings("unchecked")
	static void doTask2(List<Programmer> programmers) {
		JSONObject jsonProgrammers = new JSONObject();
		System.out.println("Categorize programmers into divisions, then teams, then genders");

		// filter by division
		programmers.stream()
		.map(programmer -> programmer.getDivision())
		.distinct().forEach(division -> {

			// filter by team for each division
			JSONObject subObjTeam = new JSONObject();
			programmers.stream()
			.filter(programmer -> programmer.getDivision().equals(division))
			.map(programmer -> programmer.getTeam())
			.distinct().forEach(team -> {

						// filter by gender
						JSONObject subObjGender = new JSONObject();
						programmers.stream()
						.filter(programmer -> programmer.getTeam().equals(team)
										&& programmer.getDivision().equals(division))
						.map(programmer -> programmer.getGender())
						.distinct().forEach(gender -> {

									// get programmer's name for each gender
									JsonArray subArray = new JsonArray();
									programmers.stream()
									.filter(programmer -> programmer.getTeam().equals(team)
											&& programmer.getDivision().equals(division)
											&& programmer.getGender() == gender)
									.map(programmer -> programmer.getName())
									.forEach(subArray::add);
									subObjGender.put(gender.toString(), subArray);
								});
						subObjTeam.put(team, subObjGender);
					});
			jsonProgrammers.put(division, subObjTeam);
		});
		System.out.println(new GsonBuilder().setPrettyPrinting().create()
				.toJson(new JsonParser().parse(jsonProgrammers.toJSONString())));
	}

	public static void main(String[] args) {
		List<Programmer> programmers = loadData();

		// List all programmers
		System.out.println("List all:");
		programmers.forEach(System.out::println);
		System.out.println("=========================================");

		// Categorize programmers into divisions
		doTask1(programmers);
		System.out.println("=========================================");

		// Categorize programmers into divisions, then teams, then genders
		doTask2(programmers);
	}
}
