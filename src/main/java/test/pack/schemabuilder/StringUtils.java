package test.pack.schemabuilder;

import java.util.Arrays;

public class StringUtils {
    public static String toTitleCase(String input) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(input.split("\\s+"))
                .forEach(s -> sb.append(Character.toTitleCase(s.charAt(0)))
                        .append(s.substring(1))
                        .append(" "));
        return sb.toString().trim();
    }
}
