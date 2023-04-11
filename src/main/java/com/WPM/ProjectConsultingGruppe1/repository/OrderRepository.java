package com.WPM.ProjectConsultingGruppe1.repository;
/**
 * Created by Dany on 04/04/2023.
 */

import com.WPM.ProjectConsultingGruppe1.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUsername (String username);
}
