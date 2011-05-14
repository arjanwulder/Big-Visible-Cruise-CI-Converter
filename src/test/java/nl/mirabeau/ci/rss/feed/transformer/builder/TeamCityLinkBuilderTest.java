package nl.mirabeau.ci.rss.feed.transformer.builder;

import junit.framework.Assert;
import org.junit.Test;

public class TeamCityLinkBuilderTest {

    @Test
    public void mustReturnFeedUrl() {
        // Arrange
        String url = "http://www.feedurl.com";
        String view = "builds";

        TeamCityLinkBuilder linkBuilder = new TeamCityLinkBuilder();
        linkBuilder.setUrl(url);

        // Act
        String result = linkBuilder.buildUrl(view);

        // Arrange
        Assert.assertNotNull(result);
//        Assert.assertEquals("http://www.feedurl.com/feed.html?itemsType=builds&buildStatus=successful&buildStatus=failed",
//                result);
        Assert.assertEquals("http://trans-build1.mirabeau.nl:9090/feed.html?itemsType=builds&buildStatus=successful&buildStatus=failed",
                result);
    }
}
