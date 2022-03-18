package ahorcado;

import java.util.Random;

public class Ahorcado {
	private String[] palabras = {"hola","casa","auto","perro"};
	private String palabra;
	private String palabraConGuiones;
	private int puntaje;
	private int intentos;
	
	public Ahorcado(int intentos) {
		this.palabra = "";
		this.palabraConGuiones = "";
		this.intentos = intentos; 
		this.puntaje = 0;
	}
	
	public String elegirPalabra() {
		Random random = new Random();
		int elem = random.nextInt(this.palabras.length);
		return this.palabra = this.palabras[elem];
	}

	public String convertirPalabraAGuiones(String palabra) {
		for (int i = 0; i < palabra.length(); i++) {
			this.palabraConGuiones += "-";
		}
		return this.palabraConGuiones;
	}
	
	public String adivinarLetra(char letra) {
		return "";
	}
	
	public boolean adivinoLetra(CharSequence c) {
		return true;
	}
}
