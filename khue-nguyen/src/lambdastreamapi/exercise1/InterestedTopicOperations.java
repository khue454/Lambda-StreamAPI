package lambdastreamapi.exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InterestedTopicOperations {

	public static void main(String[] args) {
		List<Topic> currentTopics; // needs initializing
		List<Topic> updatingTopics; // needs initializing

		currentTopics = new ArrayList<>();
		updatingTopics = new ArrayList<>();
		System.out.println("++++++++++++++++++++++++++");
		currentTopics.add(new Topic("topic 1", "Grails", "new"));
		currentTopics.add(new Topic("topic 2", "OOPs", "new"));
		currentTopics.add(new Topic("topic 3", "IO", "new"));
		currentTopics.add(new Topic("topic 4", "Clean code", "new"));

		updatingTopics.add(new Topic("topic 2", "OOPs", "new"));
		updatingTopics.add(new Topic("topic 4", "Clean code", "new"));
		updatingTopics.add(new Topic("topic 5", "Lambda Stream API", "new"));

		// Find out these groups of topics from currentTopics and updatingTopics

		// find out list newly added topics
		List<Topic> addedTopics;
		addedTopics = updatingTopics.stream().filter(
				topic -> currentTopics.stream().noneMatch(currentTopic -> currentTopic.getId().equals(topic.getId())))
				.collect(Collectors.toList());
		if (!addedTopics.isEmpty()) {
			System.out.println("Newly added Topics:");
			addedTopics.forEach(System.out::println);
		} else {
			System.out.println("There is no newly added topic");
		}
		System.out.println("=========================================================");

		// find out list updated topics
		List<Topic> updatedTopics;
		updatedTopics = updatingTopics.stream().filter(
				topic -> currentTopics.stream().anyMatch(currentTopic -> currentTopic.getId().equals(topic.getId())))
				.collect(Collectors.toList());
		if (!updatedTopics.isEmpty()) {
			System.out.println("Updated Topics:");
			updatedTopics.forEach(System.out::println);
		} else {
			System.out.println("There is no updated topic");
		}
		System.out.println("=========================================================");

		// find out list deleted topics
		List<Topic> deletedTopics;
		deletedTopics = currentTopics.stream().filter(
				topic -> updatingTopics.stream().noneMatch(updatedTopic -> updatedTopic.getId().equals(topic.getId())))
				.collect(Collectors.toList());
		if (!deletedTopics.isEmpty()) {
			System.out.println("Deleted Topics:");
			deletedTopics.forEach(System.out::println);
		} else {
			System.out.println("There is no deleted topic");
		}
	}
}