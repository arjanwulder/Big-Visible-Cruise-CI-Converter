package nl.mirabeau.ci.rss.feed.transformer;

import nl.mirabeau.ci.rss.feed.common.Validate;
import nl.mirabeau.ci.rss.feed.domain.Projects;
import nl.mirabeau.ci.rss.feed.domain.assembler.ProjectsAssembler;
import nl.mirabeau.ci.rss.feed.domain.assembler.TeamCityProjectAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import java.util.List;

@Component("teamCityFeedTransformer")
public class TeamCityFeedTransformer implements FeedTransformer {

    @Autowired(required = true)
    private FeedGetter feedGetter;

    @Override
    public Projects transform(String url) {
        Validate.hasText(url);

        List<Node> rssEntries = feedGetter.getFeed(url);

        ProjectsAssembler assembler = new ProjectsAssembler(new TeamCityProjectAssembler());
        return assembler.assemble(rssEntries);
    }

    public void setFeedGetter(FeedGetter feedGetter) {
        this.feedGetter = feedGetter;
    }

}
