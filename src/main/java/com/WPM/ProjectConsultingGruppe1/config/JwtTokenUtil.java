package com.WPM.ProjectConsultingGruppe1.config;

/**
 * Created by Dany on 04/04/2023.
 */
import com.WPM.ProjectConsultingGruppe1.model.User;
import com.WPM.ProjectConsultingGruppe1.service.UserServiceImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {
    @Autowired
    UserServiceImpl userService;

    @Value("${projectconsulting.jwtSecret}")
    private String projectConsultingJwtSecretKey;

    @Value("${projectconsulting.jwtExpiration}")
    private int projectConsultingJwtExpiration;

    public String generateJwtToken(UserDetails userDetails) {

        JwtBuilder jwt =  Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + projectConsultingJwtExpiration * 1000L))
            .signWith(SignatureAlgorithm.HS512, projectConsultingJwtSecretKey)
            .claim("roles", userDetails.getAuthorities())
            ;

        User user = userService.getUser(userDetails.getUsername());
        if (user != null) {
            jwt.claim("user", user);
        }
        return jwt.compact();
    }


    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(projectConsultingJwtSecretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(projectConsultingJwtSecretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
