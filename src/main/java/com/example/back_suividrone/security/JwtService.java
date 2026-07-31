package com.example.back_suividrone.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.util.Date;



@Service
public class JwtService {


    private final String secretKey;

    private final long expirationTime;



    public JwtService(

            @Value("${jwt.secret}")
            String secretKey,

            @Value("${jwt.expiration}")
            long expirationTime

    ){

        this.secretKey = secretKey;

        this.expirationTime = expirationTime;

    }



    private SecretKey getSigningKey(){

        return Keys.hmacShaKeyFor(
                secretKey.getBytes()
        );

    }



    // Création du token

    public String generateToken(
            String email,
            String role
    ){


        return Jwts.builder()

                .subject(email)

                .claim(
                        "role",
                        role
                )

                .issuedAt(
                        new Date()
                )

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + expirationTime
                        )
                )

                .signWith(
                        getSigningKey()
                )

                .compact();

    }





    // récupérer email

    public String extractUsername(
            String token
    ){

        return Jwts.parser()

                .verifyWith(
                        getSigningKey()
                )

                .build()

                .parseSignedClaims(token)

                .getPayload()

                .getSubject();

    }





    // récupérer rôle

    public String extractRole(
            String token
    ){

        return Jwts.parser()

                .verifyWith(
                        getSigningKey()
                )

                .build()

                .parseSignedClaims(token)

                .getPayload()

                .get(
                        "role",
                        String.class
                );

    }





    // vérifier validité token

    public boolean isTokenValid(
            String token,
            String email
    ){


        String username =
                extractUsername(token);


        return username.equals(email)
                &&
                !isTokenExpired(token);

    }





    private boolean isTokenExpired(
            String token
    ){


        Date expiration =
                Jwts.parser()

                        .verifyWith(
                                getSigningKey()
                        )

                        .build()

                        .parseSignedClaims(token)

                        .getPayload()

                        .getExpiration();


        return expiration.before(
                new Date()
        );

    }

}