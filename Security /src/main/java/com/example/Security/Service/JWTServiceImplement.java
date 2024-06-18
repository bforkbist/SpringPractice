package com.example.Security.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImplement implements JWTService{

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60* 1000 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.ES256).compact();
    }

    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }


    private <T> T extractClaim(String token, Function<Claims,T> claimResolvers){
        final Claims claims = extractClaims(token);
        return claimResolvers.apply(claims);
    }
    private Key getSignInKey() {
        byte[] key = Decoders.BASE64.decode("");
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
