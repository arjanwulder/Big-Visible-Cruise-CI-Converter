package nl.mirabeau.ci.rss.feed.transformer;

import org.w3c.dom.Node;

import java.util.List;

public interface FeedGetter {

    List<Node> getFeed(String url);

}