package com.WPM.ProjectConsultingGruppe1.repository;
/**
 * Created by Dany on 04/04/2023.
 */

import com.WPM.ProjectConsultingGruppe1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
