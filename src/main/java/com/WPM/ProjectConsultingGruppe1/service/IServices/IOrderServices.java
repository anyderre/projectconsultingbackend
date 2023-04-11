package com.WPM.ProjectConsultingGruppe1.service.IServices;

/**
 * Created by Dany on 04/04/2023.
 *
 */
import com.WPM.ProjectConsultingGruppe1.model.Order;

import java.util.HashMap;
import java.util.List;

public interface IOrderServices {
    HashMap<String, Object> saveOrder(Order order);
    Order getOrder(Long orderId);
    List<Order> getOrders(String orders);
}
