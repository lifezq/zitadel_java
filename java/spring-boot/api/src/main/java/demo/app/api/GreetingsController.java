package demo.app.api;

import lombok.var;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
class GreetingsController {

    @GetMapping("/greet/me")
    Object greetme(Authentication auth) {
        var tokenAttributes = ((BearerTokenAuthentication) auth).getTokenAttributes();
        var message = "调用API资源成功" + tokenAttributes.get(StandardClaimNames.PREFERRED_USERNAME) + " " + Instant.now();
        Map map = new HashMap();
        map.put("message",message);
        return map;
    }

    @RolesAllowed("ROLE_p1-u2-role")
    @GetMapping("/greet/me/u2")
    Object greetmeU2(Authentication auth) {
        var tokenAttributes = ((BearerTokenAuthentication) auth).getTokenAttributes();
        var message = "调用成功" + tokenAttributes.get(StandardClaimNames.PREFERRED_USERNAME) + " token:" + ((BearerTokenAuthentication) auth).getToken().getTokenValue() + " " + Instant.now();
        Map map = new HashMap();
        map.put("message",message);
        return map;
    }

    @RolesAllowed("ROLE_p1-u3-role")
    @GetMapping("/greet/me/u3")
    Object greetmeU3(Authentication auth) {
        var tokenAttributes = ((BearerTokenAuthentication) auth).getTokenAttributes();
        var message = "调用成功" + tokenAttributes.get(StandardClaimNames.PREFERRED_USERNAME) + " " + Instant.now();
        Map map = new HashMap();
        map.put("message",message);
        return map;
    }

    @RolesAllowed("ROLE_p1-u2-role")
    @GetMapping(value = "/greetings/me/token")
    Object getToken(Authentication auth){
        var tokenAttributes = ((BearerTokenAuthentication) auth).getTokenAttributes();
        var message = "调用成功" + tokenAttributes.get(StandardClaimNames.PREFERRED_USERNAME) + " " + Instant.now();
        Map map = new HashMap();
        map.put("message",message);
        return map;
    }
}
