package org.example.fs.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fs.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails , Serializable {

    private static final long serialVersionUID = 1L;

    //用户信息
    private User user;

    //用户权限
    private Set<String> permission;

    //用户对应的缓存信息的key
    private String uuid;


    public LoginUser(User user, Set<String> permission) {
        this.user = user;
        this.permission = permission;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
