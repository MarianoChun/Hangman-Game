package ahorcado;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Ahorcado {
	private String[] palabras = { "ola", "mar", "casa", "auto", "perro", "gato", "oso", "botella", "eclipse", "barco", "bote" };
	private String palabra;
	private List<String> letrasAdivinadas;
	private char[] palabraSecreta;
	private int puntaje;
	private int intentos;
	private final int DEFAULT_INTENTOS = 6;
	
	public Ahorcado() {
		this.palabra = elegirPalabra();
		this.palabraSecreta = convertirPalabraAGuiones(this.palabra);
		this.letrasAdivinadas = new LinkedList<String>();
		this.intentos = DEFAULT_INTENTOS;
		this.puntaje = 0;
	}
	/* Para test! */
	public Ahorcado(String palabra) {
		this.palabra = palabra;
		this.palabraSecreta = convertirPalabraAGuiones(palabra);
		this.letrasAdivinadas = new LinkedList<String>();
		this.intentos = DEFAULT_INTENTOS;
		this.puntaje = 0;
	}

	public String obtenerPalabraSecreta() {
		StringBuilder palabra = new StringBuilder();
		for (int i = 0; i < this.palabraSecreta.length; i++) {
			palabra.append(palabraSecreta[i]);
		}
		return palabra.toString();
	}

	public void adivinarLetra(char letra) {
		letra = Character.toLowerCase(letra);
		if (adivinoLetra(letra)) {
			cambiarEstadoPalabra(letra);		
			sumarPuntaje(letra);	
			agregarLetraAdivinada(letra);
			if(adivinoPalabra())
				cambiarPalabra();
		} else {
			quitarIntentos();
		}
	}

	public void adivinarLetra(String str) {
		if(str.length() > 0){
			char letra = str.charAt(0);
			adivinarLetra(letra);
		}
			
	}
	
	public void reiniciarJuego() {
		cambiarPalabra();	
		restablecerPuntaje();
	}
	
	public void cambiarPalabra() {
		String viejaPalabra = this.palabra;
		String nuevaPalabra = elegirPalabra();
		
		while(nuevaPalabra == viejaPalabra) 
			nuevaPalabra = elegirPalabra();
		
		restablecerIntentos();
		
		this.palabra = nuevaPalabra;
		this.palabraSecreta = convertirPalabraAGuiones(nuevaPalabra);
	}
	
	public boolean perdioJuego() {
		return this.intentos == 0;
	}
	
	public boolean ganoJuego() {
		if(this.puntaje == 0) {
			return false;
		}
		return this.puntaje % 20 == 0;
	}
	
	public int getPuntaje() {
		return puntaje;
	}

	public int getIntentos() {
		return intentos;
	}

	public List<String> getLetrasAdivinadas() {
		return letrasAdivinadas;
	}
	
	private void restablecerIntentos() {
		this.intentos = DEFAULT_INTENTOS;
	}
	private void restablecerPuntaje() {
		this.puntaje = 0;
	}
	private String elegirPalabra() {
		Random random = new Random();
		int elem = random.nextInt(this.palabras.length);
		return this.palabras[elem];
	}

	private char[] convertirPalabraAGuiones(String palabra) {
		this.palabraSecreta = new char[palabra.length()];

		for (int i = 0; i < palabra.length(); i++) {
			this.palabraSecreta[i] = '-';
		}
		return this.palabraSecreta;
	}

	private void cambiarEstadoPalabra(char letra) {
		for (int indice = 0; indice < this.palabraSecreta.length; indice++) {
			if (letra == this.palabra.charAt(indice)) {
				this.palabraSecreta[indice] = letra;
			}
		}
	}

	private boolean adivinoLetra(char letra) {
		return this.palabra.contains("" + letra);
	}

	private void agregarLetraAdivinada(char letra) {
		if(!esLetraAdivinada(letra)) {
			letrasAdivinadas.add(""+letra);
		}
	}
	private boolean esLetraAdivinada(char letra) {
		return letrasAdivinadas.contains(""+letra);
	}
	private void sumarPuntaje(char letra) {
		if(!esLetraAdivinada(letra)) {
			this.puntaje++;
		}
	}

	private void quitarIntentos() {
		if(this.intentos > 0) {
			this.intentos--;
		}
		
	}
	
	private boolean adivinoPalabra() {
		return this.obtenerPalabraSecreta().equals(this.palabra);
	}

}