package test.pack.schemabuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StringUtils {

    private static boolean underScoreRemoverEnable;

    @Value("${schema.underscore-remover-enable:false}")
    public void setUnderScoreRemoverEnable(boolean underScoreRemoverEnable) {
        StringUtils.underScoreRemoverEnable = underScoreRemoverEnable;
    }

    public static String toTitleCase(String input) {
        if (input == null) return null;

        input = input.trim();
        if (input.length() == 0) return "";
        if (input.length() == 1) return String.valueOf(Character.toUpperCase(input.charAt(0)));

        StringBuilder sb = new StringBuilder();

        if (underScoreRemoverEnable) input = input.replace("_", " ");
        Arrays.stream(input.split("\\s+"))
                .forEach(s -> sb.append(Character.toTitleCase(s.charAt(0)))
                        .append(s.substring(1).toLowerCase())
                        .append(" "));
        return sb.toString().trim();
    }

    public static String toLowerCamelCase(String input) {
        if (input == null) return null;

        input = input.trim();
        if (input.length() == 0) return "";
        if (input.length() == 1) return String.valueOf(Character.toLowerCase(input.charAt(0)));

        StringBuilder sb = new StringBuilder();

        if (underScoreRemoverEnable) input = input.replace("_", " ");
        Arrays.stream(input.split("\\s+"))
                .forEach(s -> sb.append(Character.toUpperCase(s.charAt(0)))
                        .append(s.substring(1).toLowerCase()));
        String str = sb.toString();
        str = Character.toLowerCase(str.charAt(0)) + str.substring(1);
        return str;
    }

    public static String toUpperCamelCase(String input) {
        if (input == null) return null;

        input = input.trim();
        if (input.length() == 0) return "";
        if (input.length() == 1) return String.valueOf(Character.toUpperCase(input.charAt(0)));

        StringBuilder sb = new StringBuilder();

        if (underScoreRemoverEnable) input = input.replace("_", " ");
        Arrays.stream(input.split("\\s+"))
                .forEach(s -> sb.append(Character.toUpperCase(s.charAt(0)))
                        .append(s.substring(1).toLowerCase()));
        return sb.toString().trim();
    }
}
