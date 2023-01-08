package ru.elseff.learn_jwt.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.elseff.learn_jwt.persistence.User;

import java.util.Collection;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static UserDetailsImpl toUserDetails(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setGrantedAuthorities(user.getRoles());
        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
