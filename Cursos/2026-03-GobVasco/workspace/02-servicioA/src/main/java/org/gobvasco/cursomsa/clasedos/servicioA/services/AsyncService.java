package org.gobvasco.cursomsa.clasedos.servicioA.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	private String estado = "Proceso Largo no Inciado";
	
	@Async
	public void iniciarProcesoLargo() {
		this.estado = "Proceso Largo Inciado";
		try {
			Thread.sleep(10000);
			this.estado = "Proceso Largo Finalizado";
		} catch (InterruptedException e) {
		   //...
		}
	}
	
	public String getEstadoProceso() {
		return this.estado;
	}
	
}
