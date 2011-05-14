package nl.mirabeau.ci.rss.feed.transformer.builder;

public interface LinkBuilder {

    String buildUrl(String view);

    String buildUrl(String view, String parameters);

}