package demo1.web;


import lombok.var;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
class UiController {

    @GetMapping("/")
    public String showIndex(Model model, Authentication auth) {
        var roleNames = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        model.addAttribute("roleNames", roleNames);
        return "index";
    }

    @GetMapping("/public")
    public String publicPage(Model model, Authentication auth) {
        if (auth != null) {
            model.addAttribute("uname", auth.getName());
        }
        return "public";
    }
}
