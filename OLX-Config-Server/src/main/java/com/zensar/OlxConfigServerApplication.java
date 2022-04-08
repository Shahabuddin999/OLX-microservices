package com.zensar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class OlxConfigServerApplication {

	public static void main(String[] args) {
		
		/* to run get response from API we need to hit the api like this
		 * Config server always get the updated value from github, because it always hit the git, in case if anything is changed in uploaded .yml file on git, microservice hits config server only once
		 * so microservice will not get updated value, to get updated value we need to hit /refresh actutor on those(port) microservice in which we want updated value
		 * like : http://localhost:8080/actuator/refresh   method should be of this /refresh actuator METHOD : POST
		 *
		 * There is a class inside OLX-Login microservice CheckForConfigServerUpdatedValue.java have look.
		 * in Config server changes is being reflected but in microservice is not reflecting without calling /refresh actuator.
		 * 
		 * you can check by hitting this api defined inside CheckForConfigServerUpdatedValue.java : http://localhost:8080/read-property
		 * */
		 
		// http://localhost:port(8888)/.yml_file_name as as application_name/default   i.e.
		// http://localhost:8888/advertise-service/default
		// http://localhost:8888/master-service/default
		// http://localhost:8888/login-service/default
		
		SpringApplication.run(OlxConfigServerApplication.class, args);
	}

}
