package ascentitllc.submittions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Ascentitllc1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ascentitllc1Application.class, args);
	}

}
