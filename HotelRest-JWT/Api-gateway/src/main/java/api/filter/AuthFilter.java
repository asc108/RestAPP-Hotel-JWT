package api.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;

import lombok.RequiredArgsConstructor;

@Component

public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private JwtUtil jwtUtil; 
	
	public AuthFilter() {
		super(Config.class);
	}
	@Override
	public GatewayFilter apply(Config config) {
		
		return ((exchange,chain) -> {
			if(validator.isSecured.test(exchange.getRequest())) {
				//da li header sadrzi token
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Nedostaje header");
				}
				String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				
				if(authHeaders != null && authHeaders.startsWith("Bearer ")) {
					authHeaders.substring(7);
				}
				try {
					jwtUtil.validateToken(authHeaders);
					
				}catch (Exception e) {
					throw new RuntimeException();
				}
			}
			return chain.filter(exchange);
		});
		
	}
	
	public static class Config {
		
	}

}
