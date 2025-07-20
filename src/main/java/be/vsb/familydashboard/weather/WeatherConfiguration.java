
package be.vsb.familydashboard.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WeatherConfiguration {
    @Bean
    public RestClient restClient(@Value("${weatherapi.url}") String url) {
        return RestClient.builder()
                .baseUrl(url)
                .build();
    }

    @Bean
    public WeatherRepository weatherRepository(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(WeatherRepository.class);
    }
}