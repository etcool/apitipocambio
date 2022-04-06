package com.bcp.apitipocambio.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.apitipocambio.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
		
		if (username.equals("userbcp") && pwd.equals("JqdGkiOiJzb2Z0dGV")) {
			String token = getJWTToken(username);
			User user = new User();
			user.setUsername(username);
			user.setToken(token);
			return ResponseEntity.ok().body(user);
		} else {
			Map<String, String> mapBody = new HashMap<String, String>();
			mapBody.put("code", "1");
			mapBody.put("message", "Error al autenticar usuario");
			return ResponseEntity.ok().body(mapBody);
		}
		
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}
