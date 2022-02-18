package com.jarida.server.jaridaserver.spring_security_2.security;

import com.jarida.server.jaridaserver.spring_security_2.entity.RoleTwos;
import com.jarida.server.jaridaserver.spring_security_2.entity.UserTwos;
import com.jarida.server.jaridaserver.spring_security_2.repository.UserRepositoryTwos;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServiceTwos implements UserDetailsService {

    private UserRepositoryTwos userRepositoryTwos;

    public CustomUserDetailsServiceTwos(UserRepositoryTwos userRepositoryTwos) {
        this.userRepositoryTwos = userRepositoryTwos;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserTwos userTwos = userRepositoryTwos.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
        return new org.springframework.security.core.userdetails.User(userTwos.getEmail(), userTwos.getPassword(), mapRolesToAuthorities(userTwos.getRoleTwos()));
    }




    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<RoleTwos> roleTwos) {
        return roleTwos.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
