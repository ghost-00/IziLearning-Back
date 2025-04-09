package com.jzi.izilearning.service;

import com.jzi.izilearning.entity.RoleEntity;
import com.jzi.izilearning.entity.UserEntity;
import com.jzi.izilearning.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IziUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;
  public IziUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new UsernameNotFoundException(email + " not found." ));

    Set<RoleEntity> roles = user.getRoles();

    Set<GrantedAuthority> authorities = roles
            .stream()
            .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

    return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            authorities
    );
  }
}
