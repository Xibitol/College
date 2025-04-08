package dev.pimous.l2s3sdn.td2;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class Project{
	
	private static final String LINE_FEED = "\n";
	private static final String TITLE_FORMAT = "%s Project tasks %s";
	private static final String TITLE_DELIMITER = "-";
	private static final String TASK_FORMAT = "\"%s\" assigned to engineer %s;";
	private static final String ADD_FORMAT = "%s added a task.";
	private static final String REMOVE_FORMAT = "%s removed last task.";

	private Stack<Map.Entry<String,Ingenieur>> tasks = new Stack<>();

	// SETTERS
	public void addTask(String name, Ingenieur engineer){
		tasks.push(Map.entry(name, engineer));
		System.out.println(ADD_FORMAT.formatted(engineer));
	}
	public void removeTask(Ingenieur engineer){
		tasks.pop();
		System.out.println(REMOVE_FORMAT.formatted(engineer));
	}

	// FUNCTIONS
	@Override
	public String toString(){
		List<String> tasksStringified = tasks.stream().map(entry ->
			TASK_FORMAT.formatted(entry.getKey(), entry.getValue().getNom())
		).toList();

		int maxLen = tasksStringified.stream().max((t1, t2) ->
			Integer.compare(t1.length(), t2.length())
		).orElse("").length();
		double titlePadding = (maxLen - (TITLE_FORMAT.length() - 4))/2.0;

		return tasksStringified.stream().collect(Collectors.joining(LINE_FEED,
			TITLE_FORMAT.formatted(
				TITLE_DELIMITER.repeat((int) Math.ceil(titlePadding)),
				TITLE_DELIMITER.repeat((int) Math.floor(titlePadding))
			) + LINE_FEED,
			LINE_FEED + TITLE_DELIMITER.repeat(maxLen)
		));
	}
}
