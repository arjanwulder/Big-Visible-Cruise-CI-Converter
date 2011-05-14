package nl.mirabeau.ci.rss.feed.service;

import nl.mirabeau.ci.rss.feed.domain.Projects;

public interface TransformService {

    Projects transformFeed(String vendor, String view);

    Projects transformFeed(String vendor, String view, String parameters);
}
