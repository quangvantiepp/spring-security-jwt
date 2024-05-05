package nature.sales_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SalesWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesWebsiteApplication.class, args);
	}

}
