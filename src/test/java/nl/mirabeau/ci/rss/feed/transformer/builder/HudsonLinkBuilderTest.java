package nl.mirabeau.ci.rss.feed.transformer.builder;

import junit.framework.Assert;
import org.junit.Test;

public class HudsonLinkBuilderTest {

    @Test
    public void mustReturnFeedUrl() {
        // Arrange
        String url = "http://www.feedurl.com";
        String view = "Monitoring";

        HudsonLinkBuilder linkBuilder = new HudsonLinkBuilder();
        linkBuilder.setUrl(url);

        // Act
        String result = linkBuilder.buildUrl(view);

        // Arrange
        Assert.assertNotNull(result);
        //Assert.assertEquals("http://www.feedurl.com/view/Monitoring/rssLatest", result);
        Assert.assertEquals("http://trans-build1.mirabeau.nl:8080/view/Monitoring/rssLatest", result);
    }
}
