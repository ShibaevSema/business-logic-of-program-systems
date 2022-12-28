package backend.utils;

public class TextUtil {

    public static String concatenateStrings(String... args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg);
        }
        return builder.toString();
    }

}
