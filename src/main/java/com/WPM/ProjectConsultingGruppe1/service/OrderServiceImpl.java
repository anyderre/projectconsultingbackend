package com.WPM.ProjectConsultingGruppe1.service;

/**
 * Created by Dany on 04/04/2023.
 */

import com.WPM.ProjectConsultingGruppe1.model.Order;
import com.WPM.ProjectConsultingGruppe1.repository.OrderRepository;
import com.WPM.ProjectConsultingGruppe1.service.IServices.ISavingService;
import com.WPM.ProjectConsultingGruppe1.service.IServices.IOrderServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Transactional
@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderServices, ISavingService<Order, OrderRepository> {
    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(Long orderId) {
        log.info("Fetching order {}", orderId);
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getOrders(String username) {
        log.info("Fetching orders {} for username ", username);
        return orderRepository.findAllByUsername(username);
    }

    @Override
    public HashMap<String, Object> saveOrder(Order order) {
        log.info("Saving new order {} to the database", order.getId());
        return save(order, orderRepository);
    }

 @Override
    public HashMap<String, Object> save(Order order, OrderRepository orderRepository){
        HashMap<String, Object> result = new HashMap<>();
        try {
            orderRepository.save(order);
            result.put("saved", true);
        } catch (Exception ex)  {
            result.put("errorMessage", ex.getMessage());
            return result;
        }
        return result;
    }

}
