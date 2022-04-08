package test;

import static org.junit.Assert.*;
import org.junit.*;

import logica.Ahorcado;

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

		assertEquals(palabraEsperada, a.obtenerPalabraSecreta());
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
		while (i >= 0) {
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
		assertEquals(1, a.getPuntaje());
	}

	@Test
	public void reiniciarJuegoPuntajeTest() {
		a.adivinarLetra('c');
		a.adivinarLetra('s');

		a.reiniciarJuego();

		assertEquals(0, a.getPuntaje());
	}

	@Test
	public void reiniciarJuegoIntentosTest() {
		a.adivinarLetra('t');
		a.adivinarLetra('z');

		a.reiniciarJuego();

		assertEquals(6, a.getIntentos());
	}

	@Test
	public void cambiarPalabraTest() {
		Ahorcado ahorcado = new Ahorcado("perro");
		ahorcado.adivinarLetra('p');
		ahorcado.adivinarLetra('e');
		ahorcado.adivinarLetra('r');

		ahorcado.cambiarPalabra();
		;

		ahorcado.adivinarLetra('p');
		ahorcado.adivinarLetra('e');
		ahorcado.adivinarLetra('r');
		String palabraNueva = ahorcado.obtenerPalabraSecreta();

		assertNotEquals("perr-", palabraNueva);
	}

	@Test
	public void siPerdioJuegoTest() {
		Ahorcado ahorcado = new Ahorcado("pepito");
		for (int i = 0; i < 6; i++) {
			ahorcado.adivinarLetra('z');
		}
		assertEquals(true, ahorcado.perdioJuego());
	}

	@Test
	public void noPerdioJuegoTest() {
		Ahorcado ahorcado = new Ahorcado("pepito");

		ahorcado.adivinarLetra('p');
		ahorcado.adivinarLetra('z');
		ahorcado.adivinarLetra('t');

		assertEquals(false, ahorcado.perdioJuego());
	}

	@Test
	public void ganoJuegoPuntaje20Test() {
		Ahorcado ahorcado = new Ahorcado("qwertyuiopasdfghjklñ");
		String palabra = "qwertyuiopasdfghjklñ";
		for (int i = 0; i < palabra.length(); i++) {
			ahorcado.adivinarLetra(palabra.charAt(i));
		}

		assertEquals(true, ahorcado.ganoJuego());
	}

	@Test
	public void ganoJuegoPuntaje0Test() {
		assertEquals(false, a.ganoJuego());
	}

	@Test
	public void ganoJuegoPuntaje5Test() {
		Ahorcado ahorcado = new Ahorcado("taller");
		ahorcado.adivinarLetra("t");
		ahorcado.adivinarLetra("a");
		ahorcado.adivinarLetra("l");
		ahorcado.adivinarLetra("e");
		ahorcado.adivinarLetra("r");

		assertEquals(false, ahorcado.ganoJuego());
	}

	@Test
	public void puntajeMayusculasTest() {
		Ahorcado ahorcado = new Ahorcado("holas");
		ahorcado.adivinarLetra("H");
		ahorcado.adivinarLetra("O");
		ahorcado.adivinarLetra("L");
		ahorcado.adivinarLetra("A");
		ahorcado.adivinarLetra("S");

		assertEquals(5, ahorcado.getPuntaje());
	}

	@Test
	public void dificultadNormalTest() {
		Ahorcado ahorcado = new Ahorcado();
		int INTENTOS_DEFAULT = 6;

		assertEquals(INTENTOS_DEFAULT, ahorcado.getIntentos());
	}

	@Test
	public void intentosFallarYAcertarTest() {
		Ahorcado ahorcado = new Ahorcado("Hello");

		ahorcado.adivinarLetra('a');
		ahorcado.adivinarLetra('l');

		assertEquals(5, ahorcado.getIntentos());
	}

	@Test
	public void reiniciarLetrasAdivinadasTest() {
		Ahorcado ahorcado = new Ahorcado("a");
		ahorcado.adivinarLetra('a');
		ahorcado.adivinarPalabra();
	
		
		// Despues de adivinar una palabra, deberia reiniciarse esta lista a una sin letras.
		int tamañoLetrasAdivinadas = ahorcado.getLetrasAdivinadas().size();
		
		assertEquals(0, tamañoLetrasAdivinadas);
	}
	
}
