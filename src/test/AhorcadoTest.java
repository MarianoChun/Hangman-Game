package test;

import static org.junit.Assert.*;
import org.junit.*;

import ahorcado.Ahorcado;

public class AhorcadoTest {
	Ahorcado a;
	String palabra;
	
	@Before
	public void setUp() {
		a = new Ahorcado(5);
		palabra = a.elegirPalabra();
	}
	
	@Test
	public void adivinoLetraTest() {
		
	}
}
