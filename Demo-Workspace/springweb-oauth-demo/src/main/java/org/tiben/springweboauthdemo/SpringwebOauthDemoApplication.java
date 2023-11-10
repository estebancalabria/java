package org.tiben.springweboauthdemo;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringwebOauthDemoApplication {
	
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
    	if (principal != null) {
    		return Collections.singletonMap("name", principal.getAttribute("name"));
    	}
    	return Collections.singletonMap("name", "anonymous");
    }
    
    @GetMapping("/secret")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/")
    public String index() {
        return "This Is Home";
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringwebOauthDemoApplication.class, args);
	}

}
