package ru.base.web.user.security;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ru.base.AuthorizedUser;
import ru.base.service.UserDetailsServiceImpl;
import ru.base.service.UserServiceImpl;
import ru.base.util.exception.JwtAuthenticationException;


@Component
public class JwtTokenProvider {
    
    private final UserDetailsServiceImpl userService;
    
    @Value("${jwt.secret}")    
    private String secretKey;
    @Value("${jwt.expiration}")
    private long validityInMilliseconds;
    @Value("${jwt.header}")
    private String authorizationHeader;

    @Autowired
    public JwtTokenProvider(@Qualifier("userDetailServiceImpl") UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds * 10000); 
        return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();

    }

    public boolean validateToken (String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch(JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }
    
    public Authentication getAuthentication(String token) {
        UserDetails user = this.userService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(user,"",user.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(authorizationHeader);
    }

}