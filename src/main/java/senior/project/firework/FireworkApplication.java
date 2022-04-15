package senior.project.firework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import senior.project.firework.services.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class FireworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(FireworkApplication.class, args);
	}

}
