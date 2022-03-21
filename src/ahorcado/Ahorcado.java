package ahorcado;

import java.util.Random;

public class Ahorcado {
	private String[] palabras = {"hola","casa","auto","perro"};
	private String palabra;
	private char[] palabraConGuiones;
	private int puntaje;
	private int intentos;
	
	// Podriamos colocar un valor por default para intentos y elegir la palabra directamente desde el constructor
	// De esta forma, quedaria el constructor sin parametros 
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
	
	public void cambiarEstadoPalabra(char letra) {
		for(int indice = 0; indice < palabraConGuiones.length;indice++) {
			if(letra == palabraConGuiones[indice]) {
				palabraConGuiones[indice] = letra;
			}
		}
	}
	
	public boolean adivinoLetra(CharSequence c) {
		return true;
	}
	private void restarIntento() {
		intentos--;
	}
}
