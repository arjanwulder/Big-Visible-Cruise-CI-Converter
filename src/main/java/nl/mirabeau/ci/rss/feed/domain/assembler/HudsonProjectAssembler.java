package nl.mirabeau.ci.rss.feed.domain.assembler;

import nl.mirabeau.ci.rss.feed.common.NodeUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.w3c.dom.Node;

import java.util.Date;

public class HudsonProjectAssembler extends AbstractProjectAssembler {

    private final Logger logger = Logger.getLogger(getClass());

    @Override
    public void setLastBuildStatus() {
        try {
            Node node = NodeUtils.getChildNodeByName(entry, "title");
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String nodeValue = node.getFirstChild().getNodeValue();

                String lastBuildStatus = "Success";
                if (nodeValue.contains("broken")) {
                    lastBuildStatus = "Failure";
                }
                project.setLastBuildStatus(lastBuildStatus);
            }
        } catch (Exception e) {
            logger.warn("Could not retrieve property lastBuildStatus", e);
        }
    }

    @Override
    public void setName() {
        try {
            Node node = NodeUtils.getChildNodeByName(entry, "title");
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String nodeValue = node.getFirstChild().getNodeValue();

                String[] delimitedNodeValue = StringUtils.delimitedListToStringArray(nodeValue, "#");
                project.setName(delimitedNodeValue[0].trim());
            }
        } catch (Exception e) {
            logger.warn("Could not retrieve property name", e);
        }
    }

    @Override
    public void setActivity() {
        project.setActivity(ACTIVITY);
    }

    @Override
    public void setLastBuildTime() {
        try {
            Node node = NodeUtils.getChildNodeByName(entry, "updated");
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String nodeValue = node.getFirstChild().getNodeValue();

                Date lastBuildTime = sdfBuildTime.parse(nodeValue);
                project.setLastBuildTime(lastBuildTime);
            }
        } catch (Exception e) {
            logger.warn("Could not retrieve property lastBuildTime", e);
        }
    }

    @Override
    public void setNextBuildTime() {
        try {
            Node node = NodeUtils.getChildNodeByName(entry, "published");
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String nodeValue = node.getFirstChild().getNodeValue();

                Date nextBuildTime = sdfBuildTime.parse(nodeValue);
                project.setNextBuildTime(nextBuildTime);
            }
        } catch (Exception e) {
            logger.warn("Could not retrieve property nextBuildTime", e);
        }
    }

}
