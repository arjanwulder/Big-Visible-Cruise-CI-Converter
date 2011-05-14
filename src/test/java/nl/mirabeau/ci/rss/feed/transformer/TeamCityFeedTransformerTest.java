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
public class TeamCityFeedTransformerTest {

    @Autowired
    @Qualifier(value = "teamCityFeedTransformer")
    private TeamCityFeedTransformer feedTransformer;


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

        List<Node> entries = createListOfEntries(1);
        Mockito.when(feedGetter.getFeed(uri)).thenReturn(entries);

        // Act
        Projects result = feedTransformer.transform(uri);

        // Assert
        Mockito.verify(feedGetter).getFeed(uri);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.getProjectList().size());
        Assert.assertEquals("Success", result.getProjectList().get(0).getLastBuildStatus());
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
        String entryNodeAsString = "<entry><title>Build transavia.com - Builds::transavia-web-mgmt #2.8.2 was successful</title><link rel=\"alternate\" href=\"http://trans-build1:8008/TeamCity-6.0.2/viewLog.html?buildId=15&amp;buildTypeId=bt4\" /><author><name>Successful Build</name></author><updated>2011-02-05T06:18:48Z</updated><published>2011-02-05T06:18:48Z</published><summary type=\"html\">Status: &lt;strong&gt;Tests passed: 7&lt;/strong&gt;&lt;br/&gt;&#xD; Finished on: &lt;strong&gt;Feb 5, 2011 7:18:48 AM&lt;/strong&gt;&lt;br/&gt;&#xD; Changes in the build: none&lt;br/&gt;&#xD; Agent: &lt;strong&gt;trans-build1&lt;/strong&gt;&lt;br/&gt;&#xD; &#xD; Tests: &lt;strong&gt;7 passed&#xD; &#xD; &#xD; &lt;/strong&gt;&lt;br/&gt;&#xD; &lt;a href=\"http://trans-build1:8008/TeamCity-6.0.2/viewLog.html?tab=buildLog&amp;buildId=15&amp;buildTypeId=bt4\"&gt;Build log&lt;/a&gt;</summary><dc:creator>Successful Build</dc:creator><dc:date>2011-02-05T06:18:48Z</dc:date></entry>";
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        return docBuilder.parse(new InputSource(new StringReader(entryNodeAsString))).getDocumentElement();
    }


    @After
    public void tearDown() {
        this.feedTransformer = null;
    }
}
