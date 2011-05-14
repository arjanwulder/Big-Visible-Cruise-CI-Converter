package nl.mirabeau.ci.rss.feed.transformer;

import nl.mirabeau.ci.rss.feed.common.Validate;
import nl.mirabeau.ci.rss.feed.domain.Projects;
import nl.mirabeau.ci.rss.feed.domain.assembler.HudsonProjectAssembler;
import nl.mirabeau.ci.rss.feed.domain.assembler.ProjectsAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.w3c.dom.Node;

import java.util.List;

@Component("hudsonFeedTransformer")
public class HudsonFeedTransformer implements FeedTransformer {

    @Autowired(required = true)
    private FeedGetter feedGetter;

    @Override
    public Projects transform(String url) {
        Validate.hasText(url);

        List<Node> rssEntries = feedGetter.getFeed(url);

        ProjectsAssembler assembler = new ProjectsAssembler(new HudsonProjectAssembler());
        return assembler.assemble(rssEntries);
    }

    public void setFeedGetter(FeedGetter feedGetter) {
        this.feedGetter = feedGetter;
    }

}
