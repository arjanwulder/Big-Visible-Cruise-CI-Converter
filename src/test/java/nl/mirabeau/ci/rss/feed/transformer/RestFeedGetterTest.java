package nl.mirabeau.ci.rss.feed.transformer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RestFeedGetterTest {

    private FeedGetter feedGetter;

    @Before
    public void setUp() {
        this.feedGetter = new RestFeedGetter();
    }


    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenUrlIsNull() {
        // Act
        this.feedGetter.getFeed(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenUrlIsEmpty() {
        // Act
        this.feedGetter.getFeed("");
    }


    @After
    public void tearDown() {
        this.feedGetter = null;
    }
}
