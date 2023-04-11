package com.WPM.ProjectConsultingGruppe1.service;

/**
 * Created by Dany on 04/04/2023.
 */

import com.WPM.ProjectConsultingGruppe1.model.enums.RoleEnum;
import com.WPM.ProjectConsultingGruppe1.model.User;
import com.WPM.ProjectConsultingGruppe1.repository.UserRepository;
import com.WPM.ProjectConsultingGruppe1.service.IServices.IRoleServices;
import com.WPM.ProjectConsultingGruppe1.service.IServices.ISavingService;
import com.WPM.ProjectConsultingGruppe1.service.IServices.IUserServices;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


@Slf4j
@Transactional
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserServices, UserDetailsService, ISavingService<User, UserRepository> {
    private final IRoleServices roleServices;
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(String userName) {
        log.info("Fetching user {}", userName);
        return userRepository.findByUsername(userName);
    }

    @Override
    public HashMap<String, Object> saveUser(User user) {
        log.info("Saving new user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return save(user, userRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.isEmpty()) {
            log.error("Username must be provided");
            throw new UsernameNotFoundException("Username must be provided");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.debug("User not found with username: {}", username);
            throw new UsernameNotFoundException(
                    String.format("User not found with username: %s",
                            username));
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName()))
        );

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public HashMap<String, Object> save(User user, UserRepository userRepository){
        HashMap<String, Object> result = new HashMap<>();
        try {
            userRepository.save(user);
            roleServices.addRoleToUser(user.getUsername(), RoleEnum.ROLE_USER.name());
            result.put("saved", true);
        } catch (Exception ex)  {
            result.put("errorMessage", ex.getMessage());
            return result;
        }
        return result;
    }

}
