package org.gobvasco.cursomsa.clasedos.servicioA.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="servicio-b", url="http://localhost:8080/")
public interface ServiceBInvocation {
	
	@GetMapping("/serviceb")
	String getMensaje();

}
