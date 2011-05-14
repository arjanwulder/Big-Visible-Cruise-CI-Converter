package nl.mirabeau.ci.rss.feed.transformer.factory;

import nl.mirabeau.ci.rss.feed.domain.enumeration.Vendor;
import nl.mirabeau.ci.rss.feed.exception.UnknownVendorException;
import nl.mirabeau.ci.rss.feed.transformer.FeedTransformer;
import nl.mirabeau.ci.rss.feed.transformer.HudsonFeedTransformer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FeedTransformationFactoryTest {

    private FeedTransformationFactory feedTransformationFactory;

    @Before
    public void setUp() {
        feedTransformationFactory = new FeedTransformationFactory();
    }


    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenVendorIsNull() throws UnknownVendorException {
        // Act
        feedTransformationFactory.getFeedTransformer(null);
    }


    @Test
    public void mustReturnHudsonFeedTransformerWhenVendorIsHudson() throws UnknownVendorException {
        // Arrange
        Vendor vendor = Vendor.HUDSON;

        FeedTransformer hudsonFeedTransformer = new HudsonFeedTransformer();
        feedTransformationFactory.setHudsonFeedTransformer(hudsonFeedTransformer);

        // Act
        FeedTransformer feedTransformer = feedTransformationFactory.getFeedTransformer(vendor);

        // Assert
        Assert.assertNotNull(feedTransformer);
        Assert.assertTrue(feedTransformer instanceof HudsonFeedTransformer);
    }


    @Test(expected = UnknownVendorException.class)
    public void mustThrowUnknownVendorExceptionWhenVendorHasNoFeedTransformerImplementation() throws UnknownVendorException {
        // Arrange
        Vendor vendor = Vendor.UNKNOWN;

        // Act
        feedTransformationFactory.getFeedTransformer(vendor);

    }


    @After
    public void tearDown() {
        feedTransformationFactory = null;
    }
}
