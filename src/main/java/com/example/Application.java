package com.example;

import com.gearsofleo.platform.aux.country.feign.client.CountryQueryClient;
import com.gearsofleo.platform.aux.country.feign.client.config.CountryClientConfig;
import com.gearsofleo.platform.integration.bo.client.config.boot.BoIntegrationFeignClientAutoConfig;
import com.gearsofleo.rhino.core.config.CoreValidationConfig;
import com.gearsofleo.rhino.discovery.client.consul.config.DiscoveryConsulClientConfig;
import com.gearsofleo.rhino.feign.ClientFactory;
import com.gearsofleo.rhino.feign.ClientSpecification;
import com.gearsofleo.rhino.rest.jersey.client.config.RestJerseyClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.example"}, exclude = {BoIntegrationFeignClientAutoConfig.class, CountryClientConfig.class})
@Import({RestJerseyClientConfig.class, CoreValidationConfig.class, DiscoveryConsulClientConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CountryQueryClient boCountryQueryClient(ClientFactory clientFactory) {
        return (CountryQueryClient)this.createClient(clientFactory, CountryQueryClient.class);
    }

    public <T> T createClient(ClientFactory clientFactory, Class<T> client) {
        return clientFactory.create(ClientSpecification.newBuilder(client, "country", "platform.integration").pathPrefix("country/api/country").build());
    }
}