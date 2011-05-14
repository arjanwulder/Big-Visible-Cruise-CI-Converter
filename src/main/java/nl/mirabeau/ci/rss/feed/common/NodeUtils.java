package nl.mirabeau.ci.rss.feed.common;

import nl.mirabeau.ci.rss.feed.exception.XmlAttributeNotFoundException;
import nl.mirabeau.ci.rss.feed.exception.XmlNodeNotFoundException;
import org.springframework.util.StringUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class NodeUtils {

    private NodeUtils() {
    }

    public static Node getChildNodeByName(Node node, String nodeName) throws XmlNodeNotFoundException {
        if (node == null) {
            throw new IllegalArgumentException("node may not be null");
        }
        if (!StringUtils.hasText(nodeName)) {
            throw new IllegalArgumentException("nodeName may not be empty or null");
        }

        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                if (nodeName.equalsIgnoreCase(childNode.getNodeName())) {
                    return childNode;
                }
            }
        }
        throw new XmlNodeNotFoundException("Node with name [" + nodeName + "] was not a child of node [" + node.getNodeName()
                + "]");
    }

    public static Node getAttributeByName(Node node, String attributeName) throws XmlAttributeNotFoundException {
        if (node == null) {
            throw new IllegalArgumentException("node may not be null");
        }
        if (!StringUtils.hasText(attributeName)) {
            throw new IllegalArgumentException("attributeName may not be empty or null");
        }

        if (node.hasAttributes()) {
            NamedNodeMap attributes = node.getAttributes();
            return attributes.getNamedItem(attributeName);
        }

        throw new XmlAttributeNotFoundException("Could not find attribute with name [" + attributeName + "] for node ["
                + node.getNodeName() + "]");
    }
}
