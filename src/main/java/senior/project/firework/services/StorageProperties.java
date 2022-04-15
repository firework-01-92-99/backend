package senior.project.firework.services;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "firework.storage")
public class StorageProperties {
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
