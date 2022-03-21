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
	public Ahorcado() {
		this.palabra = elegirPalabra();
		this.palabraConGuiones = convertirPalabraAGuiones(palabra); 
		this.intentos = 6; 
		this.puntaje = 0;
	}
	
	public char[] obtenerPalabraAAdivinar(){
		return this.palabraConGuiones;
	}
	
	public void adivinarLetra(char letra) {
		if(adivinoLetra(letra)) {
			cambiarEstadoPalabra(letra);
			sumarPuntaje();
		} else {
			quitarIntentos();
		}
	}
	
	private String elegirPalabra() {
		Random random = new Random();
		int elem = random.nextInt(this.palabras.length);
		return this.palabra = this.palabras[elem];
	}

	private char[] convertirPalabraAGuiones(String palabra) {
		this.palabraConGuiones = new char[palabra.length()];
	
		for (int i = 0; i < palabra.length(); i++) {
			this.palabraConGuiones[i] = '-';
		}
		return this.palabraConGuiones;
	}
	
	private void cambiarEstadoPalabra(char letra) {
		for(int indice = 0; indice < palabraConGuiones.length;indice++) {
			if(letra == palabraConGuiones[indice]) {
				palabraConGuiones[indice] = letra;
			}
		}
	}
	
	private boolean adivinoLetra(char letra) {
		return palabra.contains(""+letra);
	}
	
	private int sumarPuntaje() {
		return this.puntaje++;
	}
	
	private int quitarIntentos() {
		return this.intentos--;
	}
	
}