package kr.pe.jonghak.demo.oauth2.authorization.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

@SpringBootApplication
public class DemoOauth2AuthorizationServerApplication implements CommandLineRunner {
    @Autowired
    private RegisteredClientRepository registeredClientRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoOauth2AuthorizationServerApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        if (registeredClientRepository.findById("client-for-request-to-resource-server") != null) {
            registeredClientRepository.save(RegisteredClient
                .withId("client-for-request-to-resource-server")
                .clientId("demo-client")
                .clientSecret("{noop}secret")
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("resource:read")
                .scope("secret:read")
                .build());
        }
    }

}
