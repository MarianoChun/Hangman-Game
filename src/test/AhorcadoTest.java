package test;

import static org.junit.Assert.*;
import org.junit.*;

import ahorcado.Ahorcado;

public class AhorcadoTest {
	Ahorcado a;
	String palabra;

	@Before
	public void setUp() {
		a = new Ahorcado("casa");
	}

	@Test
	public void adivinarLetraTest() {
		a.adivinarLetra('a');
		char[] arrayEsperado = { '-', 'a', '-', 'a' };

		assertArrayEquals(arrayEsperado, a.obtenerPalabraAAdivinar());
	}

	@Test
	public void puntajeTest() {
		a.adivinarLetra('a');
		assertEquals(1, a.getPuntaje());
	}

	@Test
	public void intentoTest() {
		a.adivinarLetra('a');
		assertEquals(6, a.getIntentos());
	}

	@Test
	public void restarIntentoTest() {
		a.adivinarLetra('b');
		assertEquals(5, a.getIntentos());
	}
}
