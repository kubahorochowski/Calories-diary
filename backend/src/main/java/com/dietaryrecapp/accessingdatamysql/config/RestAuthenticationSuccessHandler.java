package com.dietaryrecapp.accessingdatamysql.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.dietaryrecapp.accessingdatamysql.users.User;
import com.dietaryrecapp.accessingdatamysql.users.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final long expirationTime;
    private final String secret;
    private final UserService userService;

    public RestAuthenticationSuccessHandler(@Value("${jwt.expirationTime}") long expirationTime, @Value("${jwt.secret}") String secret, UserService userService) {
        this.expirationTime = expirationTime;
        this.secret = secret;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User user = userService.getUserByUsername(principal.getUsername());
        try {
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("id", user.getUser_id())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
        response.addHeader("Authorization","Bearer " + token);
        String responseBody = "{\"username\": \"" + user.getUsername() + "\", " +
                "\"id\": \"" + user.getUser_id() + "\", " +
                "\"email\": \"" + user.getEmail() + "\", " +
                "\"password\": \"" + user.getPassword() + "\", " +
                "\"birthdate\": \"" + user.getBirthdate() + "\", " +
                "\"weight\": \"" + user.getWeight() + "\", " +
                "\"height\": \"" + user.getHeight() + "\", " +
                "\"sex\": \"" + user.getSex() + "\", " +
                "\"token\": \"" + "Bearer " + token + "\"" +
            "}";
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.getWriter().write(responseBody);
        response.getWriter().flush();
        } catch (JWTCreationException | IOException e){
            throw new JWTCreationException("JWTCreationException",e);
        }
    }

//    public String generateJwtToken(Authentication authentication) {
//
//        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//
//        return Jwts.builder()
//                .setSubject((userPrincipal.getUsername()))
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }


    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}");
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}");
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}");
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}");
        }

        return false;
    }

}
