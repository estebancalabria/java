package org.indra.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JavaTimeTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testLocalTime() {
		LocalTime fechaArg = LocalTime.now(ZoneId.of("America/Argentina/Salta")).truncatedTo(ChronoUnit.SECONDS);;
		LocalTime fechaEsp = LocalTime.now(ZoneId.of("Europe/Madrid")).truncatedTo(ChronoUnit.SECONDS);
		
		assertEquals(fechaArg.plusHours(5), fechaEsp);
	}

}
