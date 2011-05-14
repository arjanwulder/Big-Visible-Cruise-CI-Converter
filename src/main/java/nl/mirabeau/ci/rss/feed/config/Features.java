package nl.mirabeau.ci.rss.feed.config;

import org.springframework.context.annotation.ComponentScanSpec;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Feature;
import org.springframework.context.annotation.FeatureConfiguration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.servlet.config.MvcAnnotationDriven;

@FeatureConfiguration
public class Features {

    @Feature
    public ComponentScanSpec componentScan() {
        return new ComponentScanSpec("nl.mirabeau.ci.rss.feed").excludeFilters(
                new AnnotationTypeFilter(Configuration.class),
                new AnnotationTypeFilter(FeatureConfiguration.class));
    }

    @Feature
    public MvcAnnotationDriven annotationDriven(ConversionService conversionService) {
        return new MvcAnnotationDriven();
    }

}
