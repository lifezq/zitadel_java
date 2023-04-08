package demo.config;

import demo.support.AccessTokenInterceptor;
import demo.support.TokenAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
@RequiredArgsConstructor
class WebClientConfig {
    private final TokenAccessor tokenAccessor;

    @Value("${zitadel.service.token}")
    private String serviceToken;

    @Bean
    @Qualifier("zitadel")
    RestTemplate restTemplate() {
        return new RestTemplateBuilder() //
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + serviceToken)
                .interceptors(new AccessTokenInterceptor(tokenAccessor)) //
                .build();
    }

    @Bean
    @Qualifier("defaultRestTemplate")
    RestTemplate defaultRestTemplate() {
        return new RestTemplateBuilder().build();
    }
}
