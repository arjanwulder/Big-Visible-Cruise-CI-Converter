package nl.mirabeau.ci.rss.feed.domain.assembler;

import org.w3c.dom.Node;

import java.util.List;

public interface Assembler<T> {

    T assemble(List<Node> rssEntries);
}
