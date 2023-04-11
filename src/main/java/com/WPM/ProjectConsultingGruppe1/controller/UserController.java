package com.WPM.ProjectConsultingGruppe1.controller;
/**
 * Created by Dany on 04/04/2023.
 */

import com.WPM.ProjectConsultingGruppe1.model.User;
import com.WPM.ProjectConsultingGruppe1.service.IServices.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public IUserServices userServices;

    @PostMapping("/create")
    public ResponseEntity<HashMap<String, Object>> CreateUser(@Valid @RequestBody User user) {
        HashMap<String, Object> result = userServices.saveUser(user);
        if(result.get("errorMessage") !=null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@PathVariable(value = "username", required = true) String username) {
        if (username.isEmpty()) {
            return ResponseEntity.badRequest().body("You should specify the id of the user");
        }
        User user = userServices.getUser(username);

        return ResponseEntity.ok().body(user);
    }
}
