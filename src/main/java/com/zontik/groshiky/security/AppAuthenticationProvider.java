package com.zontik.groshiky.security;

import com.zontik.groshiky.dao.IUserDao;
import com.zontik.groshiky.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppAuthenticationProvider implements AuthenticationProvider {

    private final IUserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppAuthenticationProvider(IUserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        User user = userDao.findByLogin(auth.getName());
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User not found: %s", auth.getName()));
        }
        String password = auth.getCredentials().toString();

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Incorrect username or password");
        }
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(
                it -> new SimpleGrantedAuthority("ROLE_" + it.getName())).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
