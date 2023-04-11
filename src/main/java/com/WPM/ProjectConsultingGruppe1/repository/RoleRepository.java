package com.WPM.ProjectConsultingGruppe1.repository;
/**
 * Created by Dany on 04/04/2023.
 */
import com.WPM.ProjectConsultingGruppe1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String username);
}
