package nl.mirabeau.ci.rss.feed.controller;

import nl.mirabeau.ci.rss.feed.domain.Projects;
import nl.mirabeau.ci.rss.feed.service.TransformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RssFeedController {

    private static final String XML_VIEW_NAME = "feedXmlView";

    @Autowired(required = true)
    private TransformService transformService;

    @RequestMapping(value = "/vendor/{vendor}/view/{view}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable String vendor, @PathVariable String view) {
        Projects projects = transformService.transformFeed(vendor, view);
        return new ModelAndView(XML_VIEW_NAME, "object", projects);
    }

    @RequestMapping(value = "/vendor/{vendor}/view/{view}/param/{param}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable String vendor, @PathVariable String view, @PathVariable String param) {
        Projects projects = transformService.transformFeed(vendor, view, param);
        return new ModelAndView(XML_VIEW_NAME, "object", projects);
    }
}
