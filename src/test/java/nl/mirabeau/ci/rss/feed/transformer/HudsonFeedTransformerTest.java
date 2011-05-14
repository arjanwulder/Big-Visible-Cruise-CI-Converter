package nl.mirabeau.ci.rss.feed.transformer;

import nl.mirabeau.ci.rss.feed.domain.Projects;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext-test.xml"})
public class HudsonFeedTransformerTest {

    @Autowired
    @Qualifier(value = "hudsonFeedTransformer")
    private HudsonFeedTransformer feedTransformer;

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenUrlIsNull() {
        // Act
        this.feedTransformer.transform(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentExceptionWhenUrlIsEmpty() {
        // Act
        this.feedTransformer.transform("");
    }

    @Test
    public void mustReturnRssFeedResultTransformedInDomainObject() throws IOException, SAXException,
            ParserConfigurationException {
        // Arrange
        String uri = "http://uri.com";
        FeedGetter feedGetter = Mockito.mock(FeedGetter.class);
        feedTransformer.setFeedGetter(feedGetter);

        List<Node> entres = createListOfEntries(1);
        Mockito.when(feedGetter.getFeed(uri)).thenReturn(entres);

        // Act
        Projects result = feedTransformer.transform(uri);

        // Assert
        Mockito.verify(feedGetter).getFeed(uri);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.getProjectList().size());
        Assert.assertEquals("Failure", result.getProjectList().get(0).getLastBuildStatus());
    }

    private List<Node> createListOfEntries(int numberOfEntries) throws IOException, SAXException,
            ParserConfigurationException {
        List<Node> entries = new ArrayList<Node>();
        for (int i = 0; i < numberOfEntries; i++) {
            entries.add(createEntryNode());
        }
        return entries;
    }

    public Node createEntryNode() throws IOException, SAXException, ParserConfigurationException {
        String entryNodeAsString = "<entry><title>Sonar analysis transavia-srs-ws (asa charter) #3 (broken for a long time)</title><link type=\"text/html\" href=\"http://trans-build1:8080/job/Sonar%20analysis%20transavia-srs-ws%20(asa%20charter)/3/\" rel=\"alternate\" /><id>tag:hudson.dev.java.net,2011:Sonar analysis transavia-srs-ws (asa charter):2011-02-05_09-16-18</id><published>2011-02-05T08:16:18Z</published><updated>2011-02-05T08:16:18Z</updated></entry>";
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        return docBuilder.parse(new InputSource(new StringReader(entryNodeAsString))).getDocumentElement();
    }

    @After
    public void tearDown() {
        this.feedTransformer = null;
    }
}
