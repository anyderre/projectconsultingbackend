package com.WPM.ProjectConsultingGruppe1.controller;

import com.WPM.ProjectConsultingGruppe1.config.JwtTokenUtil;
import com.WPM.ProjectConsultingGruppe1.model.auth.JwtRequest;
import com.WPM.ProjectConsultingGruppe1.model.auth.JwtResponse;
import com.WPM.ProjectConsultingGruppe1.model.auth.MessageResponse;
import com.WPM.ProjectConsultingGruppe1.repository.RoleRepository;
import com.WPM.ProjectConsultingGruppe1.repository.UserRepository;
import com.WPM.ProjectConsultingGruppe1.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody JwtRequest request) {

        String authentication = authenticate(request.getUsername(), request.getPassword());
        if (!authentication.equals("")){
            return new ResponseEntity<>(MessageResponse.of(authentication), HttpStatus.BAD_REQUEST);
        }
        final UserDetails userDetails = userService
                .loadUserByUsername(request.getUsername());

        String jwt = jwtTokenUtil.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    private String authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
    //            throw new Exception("USER_DISABLED", e);
            return "USER_DISABLED";
        } catch (BadCredentialsException e) {
            return "INVALID_CREDENTIALS";
    //            throw new Exception("INVALID_CREDENTIALS", e);
        }
        return "";
    }
}
