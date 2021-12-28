package my.nalab5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
public static void main(String[] args) {
	String str = "14,1000";
	String regex = "\\d+,\\d+";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(str);
	if (matcher.find())
	{
		
		String[] parts = str.split(",");
		System.out.println("X " + parts[0]);
		System.out.println("Y " + parts[1]);
	}
	else
	{
		System.out.println("Нихуя немае");
	}
}
}
