package nl.mirabeau.ci.rss.feed.config;

import nl.mirabeau.ci.rss.feed.domain.Project;
import nl.mirabeau.ci.rss.feed.domain.Projects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

@Configuration
@ImportResource("classpath:properties-config.xml")
public class Beans {

    @Bean
    public BeanNameViewResolver beanNameViewResolver() {
        return new BeanNameViewResolver();
    }

    @Bean
    public MarshallingView feedXmlView() {
        Marshaller marshaller = getMarshaller();
        MarshallingView marshallingView = new MarshallingView(marshaller);
        return marshallingView;
    }

    private Marshaller getMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Projects.class, Project.class);
        return marshaller;
    }
}
