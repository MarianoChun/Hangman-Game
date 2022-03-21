package ahorcado;

import java.util.Random;

public class Ahorcado {
	private String[] palabras = {"hola","casa","auto","perro"};
	private String palabra;
	private char[] palabraConGuiones;
	private int puntaje;
	private int intentos;
	
	public Ahorcado(int intentos) {
		this.palabra = "";
		this.intentos = intentos; 
		this.puntaje = 0;
	}
	
	public String elegirPalabra() {
		Random random = new Random();
		int elem = random.nextInt(this.palabras.length);
		return this.palabra = this.palabras[elem];
	}

	public char[] convertirPalabraAGuiones(String palabra) {
		this.palabraConGuiones = new char[palabra.length()];
	
		for (int i = 0; i < palabra.length(); i++) {
			this.palabraConGuiones[i] = '-';
		}
		return this.palabraConGuiones;
	}
	
	public String adivinarLetra(char letra) {
		return "";
	}
	
	public boolean adivinoLetra(CharSequence c) {
		return true;
	}
	
	public int sumarPuntaje() {
		return this.puntaje++;
	}
	
	public int quitarIntentos() {
		return this.intentos--;
	}
}
