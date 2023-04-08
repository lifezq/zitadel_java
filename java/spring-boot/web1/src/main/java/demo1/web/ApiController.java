package demo1.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class ApiController {

    @Qualifier("zitadel")
    private final RestTemplate restTemplate;

    @GetMapping("/greetings/me")
    Object greetme(Authentication auth) {

        try {
            log.debug("Calling backend API: begin");
            log.info("web.token:" + ((DefaultOidcUser) auth.getPrincipal()).getIdToken().getTokenValue());
            var payload = restTemplate.getForObject("http://localhost:18090/api/greet/me", Map.class);
            log.debug("Calling backend API: end");
            return payload;
        } catch (Exception e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("message", "调用API资源成功");
            return map;
        }
    }

    @GetMapping("/greetings/me/u2")
    Object greetmeU2(Authentication auth) {

        try {
            log.debug("Calling backend API: begin");
            log.info("web.token:" + ((DefaultOidcUser) auth.getPrincipal()).getIdToken().getTokenValue());
            var payload = restTemplate.getForObject("http://localhost:18090/api/greet/me/u2", Map.class);
            log.debug("Calling backend API: end");
            return payload;
        } catch (Exception e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("message", "调用成功");
            return map;
        }
    }

    @GetMapping("/greetings/me/u3")
    Object greetmeU3(Authentication auth) {

        try {
            log.debug("Calling backend API: begin");
            log.info("web.token:" + ((DefaultOidcUser) auth.getPrincipal()).getIdToken().getTokenValue());
            var payload = restTemplate.getForObject("http://localhost:18090/api/greet/me/u3", Map.class);
            log.debug("Calling backend API: end");
            return payload;
        } catch (Exception e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("message", "调用成功");
            return map;
        }
    }
}
