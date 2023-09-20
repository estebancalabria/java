package org.allianz.hellospringboot;

import org.allianz.hellospringboot.controllers.ClienteController;
import org.allianz.hellospringboot.models.Cliente;
import org.allianz.hellospringboot.services.IClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTests {

	@Autowired
	private ClienteController controller;

	@Autowired
	private MockMvc client;

	@MockBean
	private IClienteService service;

	@Test
	public void testControllerLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testListarTodosUnauthorized() throws Exception {

		this.client.perform(get("/api/cliente/")).andDo(print()).andExpect(status().isUnauthorized());
	}

	@Test
	public void testListarTodosAuthorized() throws Exception {

		List<Cliente> mockList = new ArrayList<Cliente>() {
			private static final long serialVersionUID = 1L;
			{
				add(new Cliente() {
					{
						setId(1);
						setNombre("TEST");
						setApellido("TEST");
						setTelefono("555-555555");
						setEmail("test@test");
					}
				});
			}
		};
		when(service.getAll()).thenReturn(mockList);

		this
		    .client
		    .perform(get("/api/cliente/").header("token", "TOKEN"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json(
						"[{\"id\":1,\"nombre\":\"TEST\",\"apellido\":\"TEST\",\"telefono\":\"555-555555\",\"email\":\"test@test\"}]"));
	}
}
