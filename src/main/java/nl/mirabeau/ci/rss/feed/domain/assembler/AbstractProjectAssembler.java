package nl.mirabeau.ci.rss.feed.domain.assembler;

import nl.mirabeau.ci.rss.feed.domain.Project;
import org.w3c.dom.Node;

import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract class AbstractProjectAssembler {

    protected final SimpleDateFormat sdfBuildTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS'Z'", new Locale("nl", "NL"));

    protected static final String ACTIVITY = "Sleeping";

    protected Project project;
    protected Node entry;

    public Project assemble(Node entry) {
        this.entry = entry;
        this.project = new Project();

        setLastBuildStatus();
        setName();
        setActivity();
        setLastBuildTime();
        setNextBuildTime();

        return this.project;
    }

    public abstract void setName();

    public abstract void setLastBuildStatus();

    public abstract void setActivity();

    public abstract void setLastBuildTime();

    public abstract void setNextBuildTime();

}
