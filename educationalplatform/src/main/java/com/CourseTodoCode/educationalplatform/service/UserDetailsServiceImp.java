package com.CourseTodoCode.educationalplatform.service;

import com.CourseTodoCode.educationalplatform.model.UserEntity;
import com.CourseTodoCode.educationalplatform.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserEntityByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        user.getRoleList().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));

        user.getRoleList().stream()
                        .flatMap(role -> role.getPermissionsList().stream())
                        .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNotExpired(),
                user.isCredentialNotExpired(),
                user.isAccountNotLocked(),
                authorityList
        );
    }
}
