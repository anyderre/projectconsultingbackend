package com.WPM.ProjectConsultingGruppe1.controller;
/**
 * Created by Dany on 04/04/2023.
 */

import com.WPM.ProjectConsultingGruppe1.model.Order;
import com.WPM.ProjectConsultingGruppe1.model.User;
import com.WPM.ProjectConsultingGruppe1.service.IServices.IOrderServices;
import com.WPM.ProjectConsultingGruppe1.service.IServices.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    public IOrderServices orderServices;

    @PostMapping("/create")
    public ResponseEntity<HashMap<String, Object>> CreateUser(@Valid @RequestBody Order order) {
        HashMap<String, Object> result = orderServices.saveOrder(order);

        if(result.get("errorMessage") !=null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOrder(@PathVariable(value = "orderId", required = true) Long orderId) {
        if (orderId == null) {
            return ResponseEntity.badRequest().body("You should specify the id of the order");
        }
        Order order = orderServices.getOrder(orderId);

        return ResponseEntity.ok().body(order);
    }

    @GetMapping(value = "/list/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOrders(@PathVariable(value = "username", required = true) String username) {
        if (username == null) {
            return ResponseEntity.badRequest().body("You should specify the username of the user");
        }

        List<Order> orders = orderServices.getOrders(username);
        return ResponseEntity.ok().body(orders);
    }
}
