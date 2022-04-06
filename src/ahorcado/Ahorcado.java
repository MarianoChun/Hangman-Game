package ahorcado;

import java.util.ArrayList;
import java.util.Random;

public class Ahorcado {
	private String[] palabrasEspañol = { "ola", "mar", "casa", "auto", "perro", "gato", "oso", "botella", "eclipse", "barco",
			"bote" };
	private String[] palabrasIngles = { "abruptly", "absurd", "abyss", "avenue", "lucky", "length", "matrix", "night",
			"pixel", "programming", "waltz" };
	private String palabra;
	private char[] palabraSecreta;
	private ArrayList<String> letrasAdivinadas;
	private int puntaje;
	private int intentos;
	private String dificultad;

	public Ahorcado() {
		this.palabra = elegirPalabra();
		this.palabraSecreta = convertirPalabraAGuiones(this.palabra);
		this.letrasAdivinadas = new ArrayList<String>();
		this.intentos = 6;
		this.puntaje = 0;
		this.dificultad = "Normal";
	}

	/* Para test! */
	public Ahorcado(String palabra) {
		this.palabra = palabra;
		this.palabraSecreta = convertirPalabraAGuiones(palabra);
		this.letrasAdivinadas = new ArrayList<String>();
		this.intentos = 6;
		this.puntaje = 0;
		this.dificultad = "Normal";
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
			if (adivinoPalabra())
				cambiarPalabra();
			restablecerIntentos();
		} else {
			quitarIntentos();
		}
	}

	public void adivinarLetra(String str) {
		if (str.length() > 0) {
			char letra = str.charAt(0);
			adivinarLetra(letra);
		}
	}

	public void reiniciarJuego() {
		cambiarPalabra();
		restablecerPuntaje();
		restablecerIntentos();
	}

	public void cambiarPalabra() {
		String viejaPalabra = this.palabra;
		String nuevaPalabra = elegirPalabra();

		while (nuevaPalabra == viejaPalabra)
			nuevaPalabra = elegirPalabra();

		this.palabra = nuevaPalabra;
		this.palabraSecreta = convertirPalabraAGuiones(nuevaPalabra);
	}

	public boolean perdioJuego() {
		return this.intentos == 0;
	}

	public boolean ganoJuego() {
		if (this.puntaje == 0) {
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

	public ArrayList<String> getLetrasAdivinadas() {
		return letrasAdivinadas;
	}
	
	private void restablecerPuntaje() {
		this.puntaje = 0;
	}
	
	public void setDificultadFácil() {
		this.dificultad = "Fácil";
		this.intentos = 8;
	}

	public void setDificultadDifícil() {
		this.dificultad = "Difícil";
		this.intentos = 4;
	}

	public void setIdiomaIngles() {
		this.palabrasEspañol = this.palabrasIngles;
		cambiarPalabra();
	}
	
	private String elegirPalabra() {
		Random random = new Random();
		int elem = random.nextInt(this.palabrasEspañol.length);
		return this.palabrasEspañol[elem];
	}
	
	private void restablecerIntentos() {
		if (this.dificultad.equals("Fácil")) {
			this.intentos = 8;
		}
		if (this.dificultad.equals("Difícil")) {
			this.intentos = 4;
		}
		if (this.dificultad.equals("Normal")) {
			this.intentos = 6;
		}
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
		if (!esLetraAdivinada(letra)) {
			this.letrasAdivinadas.add("" + letra);
		}
	}

	private boolean esLetraAdivinada(char letra) {
		return this.letrasAdivinadas.contains("" + letra);
	}
	
	private boolean adivinoPalabra() {
		return this.obtenerPalabraSecreta().equals(this.palabra);
	}

	private void sumarPuntaje(char letra) {
		if (!esLetraAdivinada(letra)) {
			this.puntaje++;
		}
	}

	private void quitarIntentos() {
		if (this.intentos > 0) {
			this.intentos--;
		}
	}

}