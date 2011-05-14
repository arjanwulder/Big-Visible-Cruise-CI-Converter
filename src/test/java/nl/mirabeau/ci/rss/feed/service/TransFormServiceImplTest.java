package nl.mirabeau.ci.rss.feed.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransFormServiceImplTest {

    private TransformService transformService;


    @Before
    public void setUp() {
        this.transformService = new TransformServiceImpl();
    }


    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenVendorIsNull() {
        // Act
        transformService.transformFeed(null, "uri");
    }


    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenUrlIsNull() {
        // Act
        transformService.transformFeed("vendor", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenUrlIsEmpty() {
        // Act
        transformService.transformFeed("vendor", "");
    }


    @After
    public void tearDown() {
        this.transformService = null;
    }
}
