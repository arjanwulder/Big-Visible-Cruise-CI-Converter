package nl.mirabeau.ci.rss.feed.service.domain.assembler;

import nl.mirabeau.ci.rss.feed.domain.Project;
import nl.mirabeau.ci.rss.feed.domain.assembler.HudsonProjectAssembler;
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

public class HudsonProjectAssemblerTest {

    private HudsonProjectAssembler assembler;


    @Before
    public void setUp() {
        assembler = new HudsonProjectAssembler();
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
        Assert.assertEquals("Sonar analysis transavia-srs-ws (asa charter)", result.getName());
        Assert.assertEquals("Failure", result.getLastBuildStatus());
    }


    @After
    public void tearDown() {
        assembler = null;
    }


    public Node createEntryNode() throws IOException, SAXException, ParserConfigurationException {
        String entryNodeAsString = "<entry><title>Sonar analysis transavia-srs-ws (asa charter) #3 (broken for a long time)</title><link type=\"text/html\" href=\"http://trans-build1:8080/job/Sonar%20analysis%20transavia-srs-ws%20(asa%20charter)/3/\" rel=\"alternate\" /><id>tag:hudson.dev.java.net,2011:Sonar analysis transavia-srs-ws (asa charter):2011-02-05_09-16-18</id><published>2011-02-05T08:16:18Z</published><updated>2011-02-05T08:16:18Z</updated></entry>";
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        return docBuilder.parse(new InputSource(new StringReader(entryNodeAsString))).getDocumentElement();
    }
}
