package com.okconde.bestepstyle.core.config.jwt;


import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.entity.NhanVien;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Trong Phu on 5/20/2024
 *
 * @author Trong Phu
 */
@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.secretKey}")
    private String secretKey;

    public String gennerateToken(NhanVien nhanVien) {
        Map<String, Object> claims = new HashMap<>();
        //this.generateSecretKey();
        claims.put("username", nhanVien.getUsername());
        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(nhanVien.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        } catch (Exception e) {
            throw new InvalidParameterException("Cannot crate jwt token, error: " + e.getMessage());
        }
    }

    public String gennerateToken(KhachHang khachHang) {
            Map<String, Object> claims = new HashMap<>();
            //this.generateSecretKey();
            claims.put("username", khachHang.getUsername());
            try {
                String token = Jwts.builder()
                        .setClaims(claims)
                        .setSubject(khachHang.getUsername())
                        .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                        .compact();
                return token;
            } catch (Exception e) {
                throw new InvalidParameterException("Cannot crate jwt token, error: " + e.getMessage());
            }
        }

    private String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        String secretKey = Encoders.BASE64.encode(bytes);
        return secretKey;
    }

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);//2QWiVONM0iszTJhxKrVD/cGxSvUplcROqXMLyVDlPE0=
        return Keys.hmacShaKeyFor(bytes);

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = this.extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    /**
     * Check hạn cho token
     */
    public boolean isTokeExpired(String token) {
        Date expirationDate = this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    /**
     * Trích xuất username từ token
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Kiểm tra token còn hạn hay không
     * Kiểm tra username truyền vào có trùng với username
     * => true: token hợp lệ
     */
    public boolean validateToke(String token, UserDetails userDetails) {
        String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()))
                && !isTokeExpired(token);
    }
}
