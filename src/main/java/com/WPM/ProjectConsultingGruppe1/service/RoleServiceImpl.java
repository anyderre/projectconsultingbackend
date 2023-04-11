package com.WPM.ProjectConsultingGruppe1.service;

/**
 * Created by Dany on 04/04/2023.
 */

import com.WPM.ProjectConsultingGruppe1.model.Role;
import com.WPM.ProjectConsultingGruppe1.model.User;
import com.WPM.ProjectConsultingGruppe1.repository.RoleRepository;
import com.WPM.ProjectConsultingGruppe1.repository.UserRepository;
import com.WPM.ProjectConsultingGruppe1.service.IServices.IRoleServices;
import com.WPM.ProjectConsultingGruppe1.service.IServices.ISavingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Transactional
@Service
@AllArgsConstructor
public class RoleServiceImpl implements IRoleServices, ISavingService<Role, RoleRepository> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public HashMap<String, Object> saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return save(role, roleRepository);
    }

    @Override
    public List<Role> getRoles() {
        log.info("Fetching all roles");
        return roleRepository.findAll();
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role {} to user {}", roleName, userName);
        User user = userRepository.findByUsername(userName);
        Role role = roleRepository.findByName(roleName);

        if (user != null && role != null) {
            user.getRoles().add(role);
        }
        userRepository.save(user);
    }

    @Override
    public HashMap<String, Object> save(Role role, RoleRepository roleRepository){
        HashMap<String, Object> result = new HashMap<>();
        try {
            roleRepository.save(role);
            result.put("saved", true);
        } catch (Exception ex)  {
            result.put("errorMessage", ex.getMessage());
            return result;
        }
        return result;
    }

}
