package springbootoracledatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableSwagger2
public class SpringBootOracleDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOracleDatabaseApplication.class, args);

		try {
			InetAddress localHost = InetAddress.getLocalHost();
			System.out.println("Local IP Address: " + localHost.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
