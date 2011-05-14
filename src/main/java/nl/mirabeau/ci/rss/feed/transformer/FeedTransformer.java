package nl.mirabeau.ci.rss.feed.transformer;

import nl.mirabeau.ci.rss.feed.domain.Projects;

public interface FeedTransformer {

    Projects transform(String url);

}
