package nl.mirabeau.ci.rss.feed.transformer;

import nl.mirabeau.ci.rss.feed.common.Validate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.support.RestGatewaySupport;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathException;
import org.w3c.dom.Node;

import javax.xml.transform.Source;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("feedGetter")
public class RestFeedGetter extends RestGatewaySupport implements FeedGetter {

    @Override
    public List<Node> getFeed(String url) {
        Validate.hasText(url);

        Source result = getRestTemplate().getForObject(url, Source.class);

        try {
            Jaxp13XPathTemplate xpathTemplate = new Jaxp13XPathTemplate();
            xpathTemplate.setNamespaces(getNamespaces());

            List<Node> entries = xpathTemplate.evaluateAsNodeList("//atom:entry", result);

            return entries;
        } catch (XPathException e) {
            logger.debug("No feed found by at url [" + url + "]");
        }
        return null;
    }


    private Map<String, String> getNamespaces() {
        Map<String, String> namespaces = new HashMap<String, String>();
        namespaces.put("atom", "http://www.w3.org/2005/Atom");
        return namespaces;
    }

}
