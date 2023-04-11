package com.WPM.ProjectConsultingGruppe1.service.IServices;
/**
 * Created by Dany on 04/04/2023.
 */
import com.WPM.ProjectConsultingGruppe1.model.Role;

import java.util.HashMap;
import java.util.List;

public interface IRoleServices {
    HashMap<String, Object> saveRole (Role role);
    List<Role> getRoles();
    void addRoleToUser(String userName, String roleName);

}
