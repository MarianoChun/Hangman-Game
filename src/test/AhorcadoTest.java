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
	public void adivinarLetraYObtenerPalabraConGuionesTest() {
		a.adivinarLetra('a');
		String palabraEsperada = "-a-a";

		assertEquals(palabraEsperada, a.obtenerPalabraAAdivinar());
	}

	@Test
	public void sumarPuntajeTest() {
		a.adivinarLetra('a');
		assertEquals(1, a.getPuntaje());
	}

	@Test
	public void intentoCorrectoTest() {
		a.adivinarLetra('a');
		assertEquals(6, a.getIntentos());
	}

	@Test
	public void restarIntentoPositivoTest() {
		a.adivinarLetra('b');
		assertEquals(5, a.getIntentos());
	}
	@Test
	public void restarIntentoIgualACeroTest() {
		int i = 8;
		while(i >= 0) {
			a.adivinarLetra('b');
			i--;
		}
			
		assertEquals(0, a.getIntentos());
	}
	@Test
	public void agregarLetrasAdivinadas() {
		a.adivinarLetra('a');
		a.adivinarLetra('s');
		int tamañoLetrasAdivinadas = a.getLetrasAdivinadas().size();
		
		assertEquals(2, tamañoLetrasAdivinadas);
	}
	@Test
	public void sumarPuntajeDeLetraAdivinada() {
		a.adivinarLetra('a');
		a.adivinarLetra('a');
		assertEquals(1,a.getPuntaje());
	}
	@Test
	public void cambiarPalabraPuntajeTest() {
		a.adivinarLetra('c');
		a.adivinarLetra('s');
		
		a.cambiarPalabra();
		
		assertEquals(0, a.getPuntaje());
	}
	@Test
	public void cambiarPalabraIntentosTest() {
		a.adivinarLetra('t');
		a.adivinarLetra('z');
		
		a.cambiarPalabra();
		
		assertEquals(6, a.getIntentos());		
	}
	@Test
	public void cambiarPalabraTest() {
		Ahorcado ahorcado = new Ahorcado("perro");
		ahorcado.adivinarLetra('p');
		ahorcado.adivinarLetra('e');
		ahorcado.adivinarLetra('r');

		ahorcado.cambiarPalabra();
		
		ahorcado.adivinarLetra('p');
		ahorcado.adivinarLetra('e');
		ahorcado.adivinarLetra('r');
		String palabraNueva = ahorcado.obtenerPalabraAAdivinar();
		
		assertNotEquals("perr-", palabraNueva);		
	}
}
