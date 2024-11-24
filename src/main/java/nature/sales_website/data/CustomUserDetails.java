package nature.sales_website.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import nature.sales_website.entity.Role;
import nature.sales_website.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Create the default Role user.
//        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        Set<Role> roleSet = user.getRoleSet();
        return roleSet.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
