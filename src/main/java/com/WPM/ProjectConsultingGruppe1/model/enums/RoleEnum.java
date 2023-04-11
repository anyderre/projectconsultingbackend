package com.WPM.ProjectConsultingGruppe1.model.enums;
/**
 * Created by Dany on 04/04/2023.
 */

public enum RoleEnum {
    ROLE_ADMIN(1),
    ROLE_USER(2);

    public int getId() {
        return id;
    }

    int id;

    RoleEnum(int id) {
        this.id = id;
    }
}
