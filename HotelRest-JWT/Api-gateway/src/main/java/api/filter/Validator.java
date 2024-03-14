package api.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
// validator ruta
@Component
public class Validator {
	
	public static final List<String> apiEndpoints = List.of(
			"/user/register",
			"/user/token",
			"/eureka");
	
	 public Predicate<ServerHttpRequest> isSecured = request -> apiEndpoints.stream().noneMatch(uri ->request.getURI().getPath().contains(uri));
			 
	
	

}
