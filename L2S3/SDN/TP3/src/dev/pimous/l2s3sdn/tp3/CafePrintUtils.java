package dev.pimous.l2s3sdn.tp3;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class CafePrintUtils{

	public static final char ESCAPE_CODE = 0x1b;
	public static final int RESET_ESCAPE_CODE = 0;

	public static final int BOLD_ESCAPE_CODE = 1;

	public static final int RED_ESCAPE_CODE = 31;
	public static final int GREEN_ESCAPE_CODE = 32;
	public static final int YELLOW_ESCAPE_CODE = 33;

	private PrintStream out;
	private Cafe cafe;

	public CafePrintUtils(PrintStream out, Cafe cafe){
		this.out = out;
		this.cafe = cafe;
	}

	// GETTERS
	public String getStyleEsc(int[] escapes){
		return Arrays.stream(escapes).boxed()
			.map(esc -> esc.toString())
			.collect(Collectors.joining(
				";", "%c[".formatted(ESCAPE_CODE), "m"
			));
	}
	public String getStyleResetEsc(){
		return getStyleEsc(new int[]{RESET_ESCAPE_CODE});
	}

	public String getFinalText(String text, int[] escapes){
		return "%s%s%s".formatted(
			getStyleEsc(escapes), text, getStyleResetEsc()
		);
	}
	public String getStep(int startTime, int endTime){
		StringBuilder sb = new StringBuilder("T");

		sb.append(startTime);
		if(startTime != endTime){
			sb.append("..");
			sb.append(endTime);
		}

		return getFinalText(sb.toString(), new int[]{BOLD_ESCAPE_CODE});
	}
	
	// FUNCTIONS
	public void printCreated(){
		out.println(getFinalText(
			"%s created with a size of %d".formatted(
				cafe.getName(), cafe.getSize()
			),
			new int[]{BOLD_ESCAPE_CODE, GREEN_ESCAPE_CODE}
		));
	}
	public void printOpening(){
		out.println(getFinalText(
			"%s opened! %d groups will come, that means %d people.".formatted(
				cafe.getName(),
				cafe.getExpectedGroupCount(),
				cafe.getExpectedFilling()
			),
			new int[]{BOLD_ESCAPE_CODE, GREEN_ESCAPE_CODE}
		));
	}
	public void printStarting(){
		out.println(getFinalText(
			"Starting service of %s. Good luck...".formatted(cafe.getName()),
			new int[]{BOLD_ESCAPE_CODE, YELLOW_ESCAPE_CODE}
		));
	}
	public void printStep(int startTime, int endTime, Groupe group){
		if(startTime != endTime) out.append("%c[1F".formatted(ESCAPE_CODE));

		out.println("%s: %s (%d/%d filled);".formatted(
			getStep(startTime, endTime),
			Objects.isNull(group) ? "Nothing" : 
				"Group n°%d is %s for %d (until %d)".formatted(
					group.getIdentifier(),
					group.getStatus().name(),
					group.getWakeupTime() - endTime,
					group.getWakeupTime()
				),
			cafe.getFilling(), cafe.getSize()
		));
	}
	public void printDefeated(){
		out.println(getFinalText(
			"%s cannot be closed...".formatted(cafe.getName()),
			new int[]{BOLD_ESCAPE_CODE, RED_ESCAPE_CODE}
		));
	}
	public void printSucceed(){
		out.println(getFinalText(
			"%s successfuly closed!".formatted(cafe.getName()),
			new int[]{BOLD_ESCAPE_CODE, GREEN_ESCAPE_CODE}
		));
	}
	public void printClosing(){
		out.println(getFinalText(
			"%d groups (%d people) was forced to get out of %s.".formatted(
				cafe.getGroupCount(), cafe.getFilling(), cafe.getName()
			),
			new int[]{BOLD_ESCAPE_CODE, RED_ESCAPE_CODE}
		));
	}
}
