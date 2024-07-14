package dev.sharma.akash.tokenutility.libs.util.token;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;

public class JwtTokenUtility implements TokenUtility {

	private final SecretKey secretKey;
	private final Long expiration;

	public JwtTokenUtility(String secret, Long expiration) {
		this.secretKey = generateSecretKey(secret);
		this.expiration = expiration;
	}

	private SecretKey generateSecretKey(String secret) {
		if (secret == null || secret.isBlank()) {
			throw new RuntimeException("Invalid Token Secret");
		}
		return new SecretKeySpec(Decoders.BASE64.decode(secret), "HmacSHA256");
	}

	private Claims extractAllClaims(String token) {
		return (Claims) Jwts.parser().verifyWith(secretKey).build().parse(token).getPayload();
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

	@Override
	public String generateToken(String subject, Map<String, Object> claims) {
		return Jwts.builder().subject(subject).claims(claims).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiration)).signWith(secretKey).compact();
	}

	@Override
	public String getSubject(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public boolean isValidToken(String token) {
		return !isTokenExpired(token);
	}

}
