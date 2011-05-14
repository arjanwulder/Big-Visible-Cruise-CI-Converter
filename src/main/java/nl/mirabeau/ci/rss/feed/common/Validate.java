package nl.mirabeau.ci.rss.feed.common;

public class Validate {

    public static void notNull(Object argument) {
        if (argument == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void notNull(Object argument, String message) {
        if (argument == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasText(String argument) {
        if (argument == null || "".equals(argument)) {
            throw new IllegalArgumentException();
        }
    }

    public static void hasText(String argument, String message) {
        if (argument == null || "".equals(argument)) {
            throw new IllegalArgumentException(message);
        }
    }

}
