package nl.mirabeau.ci.rss.feed.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.io.IOException;

public class ApplicationInitializer extends PropertiesLoaderSupport
        implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    @Override
    public void initialize(ConfigurableWebApplicationContext applicationContext) {
        try {
            this.setLocations(applicationContext.getResources("classpath:*.properties"));
            applicationContext.getEnvironment().getPropertySources().addLast(
                    new PropertiesPropertySource("app_properties", this.mergeProperties()));
        } catch (IOException e) {
            logger.error("IO error during apllication properties initialization", e);
            throw new RuntimeException(e);
        }

        // perform any other initialization of the context ...
    }
}
