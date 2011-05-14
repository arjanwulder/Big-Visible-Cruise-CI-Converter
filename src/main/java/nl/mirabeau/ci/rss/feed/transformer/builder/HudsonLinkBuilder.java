package nl.mirabeau.ci.rss.feed.transformer.builder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("hudsonLinkBuilder")
public class HudsonLinkBuilder implements LinkBuilder {

    @Value("${nl.mirabeau.hudson.base.url}")
    private String url;

    private static final String STATIC_URL = "http://trans-build1.mirabeau.nl:8080";

    @Override
    public String buildUrl(String view) {
        StringBuilder linkStringBuilder = new StringBuilder(STATIC_URL);
        linkStringBuilder.append("/view/").append(view).append("/rssLatest");
        return linkStringBuilder.toString();
    }

    @Override
    public String buildUrl(String view, String parameter) {
        StringBuilder linkStringBuilder = new StringBuilder(buildUrl(view));
        linkStringBuilder.append("&").append(parameter);
        return linkStringBuilder.toString();
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
