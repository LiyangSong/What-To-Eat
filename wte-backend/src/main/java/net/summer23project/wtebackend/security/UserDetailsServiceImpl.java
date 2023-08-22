package net.summer23project.wtebackend.security;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByNameOrEmail(nameOrEmail, nameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        Set<GrantedAuthority> authorities = user.getUserRoleMappings().stream()
                .map(userRoleMapping -> new SimpleGrantedAuthority(userRoleMapping.getRole().getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                nameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}
