package nl.mirabeau.ci.rss.feed.service.domain.assembler;

import nl.mirabeau.ci.rss.feed.domain.Project;
import nl.mirabeau.ci.rss.feed.domain.assembler.TeamCityProjectAssembler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class TeamCityProjectAssemblerTest {

    private TeamCityProjectAssembler assembler;


    @Before
    public void setUp() {
        assembler = new TeamCityProjectAssembler();
    }


    @Test
    public void mustReturnAssemledProjectWithCorrectProperties() throws IOException, SAXException,
            ParserConfigurationException {
        // Arrange
        Node entry = createEntryNode();

        // Act
        Project result = assembler.assemble(entry);

        // Assert
        Assert.assertNotNull(result);
        Assert.assertEquals("Sleeping", result.getActivity());
        Assert.assertEquals("transavia-web-mgmt", result.getName());
        Assert.assertEquals("Success", result.getLastBuildStatus());
    }


    @After
    public void tearDown() {
        assembler = null;
    }


    public Node createEntryNode() throws IOException, SAXException, ParserConfigurationException {
        String entryNodeAsString = "<entry><title>Build transavia.com - Builds::transavia-web-mgmt #2.8.2 was successful</title><link rel=\"alternate\" href=\"http://trans-build1:8008/TeamCity-6.0.2/viewLog.html?buildId=15&amp;buildTypeId=bt4\" /><author><name>Successful Build</name></author><updated>2011-02-05T06:18:48Z</updated><published>2011-02-05T06:18:48Z</published><summary type=\"html\">Status: &lt;strong&gt;Tests passed: 7&lt;/strong&gt;&lt;br/&gt;&#xD; Finished on: &lt;strong&gt;Feb 5, 2011 7:18:48 AM&lt;/strong&gt;&lt;br/&gt;&#xD; Changes in the build: none&lt;br/&gt;&#xD; Agent: &lt;strong&gt;trans-build1&lt;/strong&gt;&lt;br/&gt;&#xD; &#xD; Tests: &lt;strong&gt;7 passed&#xD; &#xD; &#xD; &lt;/strong&gt;&lt;br/&gt;&#xD; &lt;a href=\"http://trans-build1:8008/TeamCity-6.0.2/viewLog.html?tab=buildLog&amp;buildId=15&amp;buildTypeId=bt4\"&gt;Build log&lt;/a&gt;</summary><dc:creator>Successful Build</dc:creator><dc:date>2011-02-05T06:18:48Z</dc:date></entry>";
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        return docBuilder.parse(new InputSource(new StringReader(entryNodeAsString))).getDocumentElement();
    }
}
