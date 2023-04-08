package demo.support.zitadel;

import lombok.var;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

import java.util.Collection;
import java.util.HashSet;

public class ZitadelGrantedAuthoritiesMapper implements GrantedAuthoritiesMapper {

    public static final String ZITADEL_ROLES_CLAIM = "urn:zitadel:iam:org:project:roles";

    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {

        System.out.println("00000000000000000000000000000000000000000000000");
        var mappedAuthorities = new HashSet<GrantedAuthority>();

        authorities.forEach(authority -> {

            if (authority instanceof SimpleGrantedAuthority) {
                mappedAuthorities.add(authority);
            }

            if (authority instanceof OidcUserAuthority) {
                addRolesFromUserInfo(mappedAuthorities, (OidcUserAuthority) authority);
            }

        });

        System.out.println("--------------------------------" + mappedAuthorities);
        return mappedAuthorities;
    }

    private void addRolesFromUserInfo(HashSet<GrantedAuthority> mappedAuthorities, OidcUserAuthority oidcUserAuthority) {
        System.out.println("00000000000000000000000000007777777777777770000000000000000000");
        var userInfo = oidcUserAuthority.getUserInfo();

        var roleInfo = userInfo.getClaimAsMap(ZITADEL_ROLES_CLAIM);
        if (roleInfo == null || roleInfo.isEmpty()) {
            return;
        }

        roleInfo.keySet().forEach(zitadelRoleName -> {
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + zitadelRoleName));
        });

        System.out.println("------------------------============--------" + mappedAuthorities);
    }

}
