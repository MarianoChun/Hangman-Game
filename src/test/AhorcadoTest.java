package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		a.jugar('a');
		String palabraEsperada = "-a-a";

		assertEquals(palabraEsperada, a.obtenerPalabraSecreta());
	}

	@Test
	public void sumarPuntajeTest() {
		a.jugar('a');
		assertEquals(1, a.getPuntaje());
	}

	@Test
	public void intentoCorrectoTest() {
		a.jugar('a');
		assertEquals(6, a.getIntentos());
	}

	@Test
	public void restarIntentoPositivoTest() {
		a.jugar('b');
		assertEquals(5, a.getIntentos());
	}

	@Test
	public void restarIntentoIgualACeroTest() {
		int i = 8;
		while (i >= 0) {
			a.jugar('b');
			i--;
		}

		assertEquals(0, a.getIntentos());
	}

	@Test
	public void agregarLetrasAdivinadas() {
		a.jugar('a');
		a.jugar('s');
		int tamañoLetrasAdivinadas = a.getLetrasAdivinadas().size();

		assertEquals(2, tamañoLetrasAdivinadas);
	}

	@Test
	public void sumarPuntajeDeLetraAdivinada() {
		a.jugar('a');
		a.jugar('a');
		assertEquals(1, a.getPuntaje());
	}

	@Test
	public void reiniciarJuegoPuntajeTest() {
		a.jugar('c');
		a.jugar('s');

		a.reiniciarJuego();

		assertEquals(0, a.getPuntaje());
	}

	@Test
	public void reiniciarJuegoIntentosTest() {
		a.jugar('t');
		a.jugar('z');

		a.reiniciarJuego();

		assertEquals(6, a.getIntentos());
	}

	@Test
	public void cambiarPalabraTest() {
		Ahorcado ahorcado = new Ahorcado("perro");
		ahorcado.jugar('p');
		ahorcado.jugar('e');
		ahorcado.jugar('r');

		ahorcado.cambiarPalabra();
		;

		ahorcado.jugar('p');
		ahorcado.jugar('e');
		ahorcado.jugar('r');
		String palabraNueva = ahorcado.obtenerPalabraSecreta();

		assertNotEquals("perr-", palabraNueva);
	}

	@Test
	public void siPerdioJuegoTest() {
		Ahorcado ahorcado = new Ahorcado("pepito");
		for (int i = 0; i < 6; i++) {
			ahorcado.jugar('z');
		}
		assertEquals(true, ahorcado.perdioJuego());
	}

	@Test
	public void noPerdioJuegoTest() {
		Ahorcado ahorcado = new Ahorcado("pepito");

		ahorcado.jugar('p');
		ahorcado.jugar('z');
		ahorcado.jugar('t');

		assertEquals(false, ahorcado.perdioJuego());
	}

	@Test
	public void ganoJuegoPuntaje20Test() {
		Ahorcado ahorcado = new Ahorcado("qwertyuiopasdfghjklñ");
		String palabra = "qwertyuiopasdfghjklñ";
		for (int i = 0; i < palabra.length(); i++) {
			ahorcado.jugar(palabra.charAt(i));
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
		ahorcado.jugar("t");
		ahorcado.jugar("a");
		ahorcado.jugar("l");
		ahorcado.jugar("e");
		ahorcado.jugar("r");

		assertEquals(false, ahorcado.ganoJuego());
	}

	@Test
	public void puntajeMayusculasTest() {
		Ahorcado ahorcado = new Ahorcado("holas");
		ahorcado.jugar("H");
		ahorcado.jugar("O");
		ahorcado.jugar("L");
		ahorcado.jugar("A");
		ahorcado.jugar("S");

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

		ahorcado.jugar('a');
		ahorcado.jugar('l');

		assertEquals(5, ahorcado.getIntentos());
	}

	@Test
	public void reiniciarLetrasAdivinadasTest() {
		Ahorcado ahorcado = new Ahorcado("a");
		ahorcado.jugar('a');
	
		
		// Despues de adivinar una palabra, deberia reiniciarse esta lista a una sin letras.
		int tamañoLetrasAdivinadas = ahorcado.getLetrasAdivinadas().size();
		
		assertEquals(0, tamañoLetrasAdivinadas);
	}
	@Test
	public void agregarLetraIngresada() {
		Ahorcado ahorcado = new Ahorcado("bellota");
		ahorcado.jugar('b');
		ahorcado.jugar('e');
		
		ArrayList<String> esperado = new ArrayList<String>();
		esperado.add("b");
		esperado.add("e");
		
		assertEquals(esperado, ahorcado.getLetrasIngresadas());
	}
	
	@Test
	public void reiniciarLetrasIngresadasTest() {
		Ahorcado ahorcado = new Ahorcado("auto");

		ahorcado.jugar('a');
		ahorcado.jugar('u');
		ahorcado.jugar('t');
		ahorcado.jugar('o');
		
		
		assertEquals(0, ahorcado.getLetrasIngresadas().size());
	}

	@Test
	public void reiniciarLetrasIngresadasCambiarPalabraTest() {
		Ahorcado ahorcado = new Ahorcado("auto");

		ahorcado.jugar('a');
		ahorcado.jugar('u');
		ahorcado.jugar('t');
		
		ahorcado.cambiarPalabra();
		
		
		assertEquals(0, ahorcado.getLetrasIngresadas().size());
	}
}
