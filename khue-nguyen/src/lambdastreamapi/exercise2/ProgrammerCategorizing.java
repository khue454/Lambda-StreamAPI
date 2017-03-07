package lambdastreamapi.exercise2;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import java.util.stream.Stream;

import org.json.simple.JSONObject;

import com.google.gson.GsonBuilder;
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
		}).limit(LIMITED_PROGRAMMER).collect(toList());
		return programmers;
	}

	// Categorize programmers into divisions
	static void doTask1(List<Programmer> programmers) {
		System.out.println("Categorize programmers into divisions:");

		Map<String, List<String>> mapProgrammers = programmers.parallelStream()
				.collect(groupingBy(Programmer::getDivision, mapping(Programmer::getName, toList())));

		System.out.println(new GsonBuilder().setPrettyPrinting().create()
				.toJson(new JsonParser().parse(new JSONObject(mapProgrammers).toJSONString())));
	}

	// Categorize programmers into divisions, then teams, then genders
	static void doTask2(List<Programmer> programmers) {
		System.out.println("Categorize programmers into divisions, then teams, then genders");

		Map<String, Map<String, Map<Programmer.Gender, List<String>>>> mapProgrammers = programmers.parallelStream()
				.collect(groupingBy(Programmer::getDivision, groupingBy(Programmer::getTeam,
						groupingBy(Programmer::getGender, mapping(Programmer::getName, toList())))));

		System.out.println(new GsonBuilder().setPrettyPrinting().create()
				.toJson(new JsonParser().parse(new JSONObject(mapProgrammers).toJSONString())));
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
