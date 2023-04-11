package com.WPM.ProjectConsultingGruppe1.service.IServices;

/**
 * Created by Dany on 04/04/2023.
 *
 */
import com.WPM.ProjectConsultingGruppe1.model.User;

import java.util.HashMap;

public interface IUserServices {
    HashMap<String, Object> saveUser(User user);
    User getUser(String userName);
}
