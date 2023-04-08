package demo1.config;

import demo1.support.TokenAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
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
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + serviceToken)
//                .interceptors(new AccessTokenInterceptor(tokenAccessor)) //
                .build();
    }
}
