package lambdastreamapi.exercise1;

public class Topic {
	private String id;

	private String name;

	private String description;

	public Topic(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		String topic = String.format("Topic [id: %-10s, name: %-20s, description: %s]", id, name, description);
		return topic;
	}
}
