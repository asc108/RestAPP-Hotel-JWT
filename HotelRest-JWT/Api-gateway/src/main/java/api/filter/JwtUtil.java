package api.filter;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

	public void validateToken(final String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode("4UHXdRAresMMDayl2VAd/3bxsJyrIXXLRexWhYyxl5wNDflh0bVSqu1njr9YQrtr");
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
