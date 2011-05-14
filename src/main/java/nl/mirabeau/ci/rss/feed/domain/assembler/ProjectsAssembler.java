package nl.mirabeau.ci.rss.feed.domain.assembler;

import nl.mirabeau.ci.rss.feed.common.Validate;
import nl.mirabeau.ci.rss.feed.domain.Project;
import nl.mirabeau.ci.rss.feed.domain.Projects;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Node;

import java.util.*;

public class ProjectsAssembler {

    private final AbstractProjectAssembler projectAssembler;

    public ProjectsAssembler(AbstractProjectAssembler projectAssembler) {
        Validate.notNull(projectAssembler);
        this.projectAssembler = projectAssembler;
    }

    public Projects assemble(List<Node> rssEntries) {
        Projects projectsTo = new Projects();

        if (CollectionUtils.isEmpty(rssEntries)) {
            return projectsTo;
        }

        List<Project> projectList = new ArrayList<Project>();
        for (Node entry : rssEntries) {
            projectList.add(projectAssembler.assemble(entry));
        }

        Map<String, Project> projectMap = filter(projectList);
        List<Project> sortedProjectList = sort(projectMap);

        projectsTo.setProjectList(sortedProjectList);
        return projectsTo;
    }


    private Map<String, Project> filter(List<Project> projectList) {
        Map<String, Project> projectMap = new HashMap<String, Project>();
        for (Project project : projectList) {
            if (projectMap.containsKey(project.getName())) {
                Project projectInMap = projectMap.get(project.getName());
                if (isLastBuidTimeMoreRecent(project, projectInMap)) {
                    replaceMapEntry(projectMap, project);
                }
            } else {
                projectMap.put(project.getName(), project);
            }
        }
        return projectMap;
    }

    private void replaceMapEntry(Map<String, Project> projectMap, Project project) {
        projectMap.remove(project.getName());
        projectMap.put(project.getName(), project);
    }

    private boolean isLastBuidTimeMoreRecent(Project project, Project projectInMap) {
        return project.getLastBuildTime().getTime() > projectInMap.getLastBuildTime().getTime();
    }

    private List<Project> sort(Map<String, Project> projectMap) {
        Object[] key = projectMap.keySet().toArray();
        Arrays.sort(key);

        List<Project> projectList = new ArrayList<Project>();
        for (int i = 0; i < key.length; i++) {
            projectList.add(projectMap.get(key[i]));
        }
        return projectList;
    }

}
