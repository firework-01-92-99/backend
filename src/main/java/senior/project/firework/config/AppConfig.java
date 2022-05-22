package senior.project.firework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Value("#{'${firework.origin.method}'.split(',')}")
    private String[] methodList;
    @Value("#{'${firework.origin.host}'.split(',')}")
    String[] hostList;
    @Value("#{'${firework.origin.headers}'.split(',')}")
    String[] headersList;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(hostList).allowedMethods(methodList).allowedHeaders(headersList);
    }
}
