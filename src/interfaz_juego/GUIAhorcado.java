package interfaz_juego;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Atxy2k.CustomTextField.RestrictedTextField;
import ahorcado.Ahorcado;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUIAhorcado extends JFrame {

	private JFrame frmJuegoAhorcado;
	private JTextField textLetraIngresada;
	private Ahorcado ahorcado;
	private JOptionPane panelGano;
	private JOptionPane panelPerdio;
	private String idioma;
	private static Menu menu;

	private Map<String, String> textos = new HashMap<String, String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu = new Menu();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIAhorcado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Juego
		frmJuegoAhorcado = new JFrame();
		frmJuegoAhorcado.setForeground(Color.WHITE);
		frmJuegoAhorcado.getContentPane().setForeground(Color.WHITE);
		frmJuegoAhorcado.setResizable(false);
		frmJuegoAhorcado.setTitle("Juego Ahorcado");
		frmJuegoAhorcado.setBackground(Color.PINK);
		frmJuegoAhorcado.setBounds(100, 100, 589, 375);
		frmJuegoAhorcado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJuegoAhorcado.getContentPane().setLayout(null);
		frmJuegoAhorcado.setVisible(true);

		// inicializo ahorcado
		ahorcado = new Ahorcado();
		configurarDificultad();
		// Cambiar idioma
		cambiarIdioma();

		// textos
		JLabel lblPalabra = new JLabel(textos.get("palabraAAdivinar"));
		lblPalabra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPalabra.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalabra.setBounds(187, 117, 172, 34);
		frmJuegoAhorcado.getContentPane().add(lblPalabra);

		JLabel lblPuntaje = new JLabel("Puntaje: " + ahorcado.getPuntaje());
		lblPuntaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuntaje.setBounds(10, 11, 93, 23);
		frmJuegoAhorcado.getContentPane().add(lblPuntaje);

		JLabel lblIntentos = new JLabel("Intentos: " + ahorcado.getIntentos());
		lblIntentos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntentos.setBounds(479, 11, 84, 23);
		frmJuegoAhorcado.getContentPane().add(lblIntentos);

		// palabra a adivinar
		JLabel lblPalabraConGuiones = new JLabel("");
		lblPalabraConGuiones.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPalabraConGuiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalabraConGuiones.setBounds(162, 162, 218, 48);
		frmJuegoAhorcado.getContentPane().add(lblPalabraConGuiones);

		// verificar letra ingresada
		JButton btnVerificarLetra = new JButton(textos.get("verificarLetra"));
		btnVerificarLetra.setBackground(new Color(245, 222, 179));
		btnVerificarLetra.setForeground(new Color(0, 0, 0));
		btnVerificarLetra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerificarLetra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String inputUsuario = textLetraIngresada.getText();
				ahorcado.adivinarLetra(inputUsuario);

				finalizarOContinuarJuego();

				actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada);
			}

		});

		btnVerificarLetra.setBounds(204, 285, 143, 23);
		frmJuegoAhorcado.getContentPane().add(btnVerificarLetra);

		// cambiar de palabra
		JButton btnReiniciar = new JButton(textos.get("reiniciar"));
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ahorcado.reiniciarJuego();
				actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada);
			}
		});

		btnReiniciar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReiniciar.setBounds(204, 53, 143, 23);
		frmJuegoAhorcado.getContentPane().add(btnReiniciar);

		// letra ingresada por usuario
		textLetraIngresada = new JTextField();
		textLetraIngresada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String inputUsuario = textLetraIngresada.getText();
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ahorcado.adivinarLetra(inputUsuario);

					finalizarOContinuarJuego();

					actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada);
				}
			}
		});
		textLetraIngresada.setHorizontalAlignment(SwingConstants.CENTER);
		textLetraIngresada.setBounds(204, 237, 143, 20);
		frmJuegoAhorcado.getContentPane().add(textLetraIngresada);
		textLetraIngresada.setColumns(10);

		// restrinjo cantidad de caracteres que puede ingresar el usuario
		RestrictedTextField restricted = new RestrictedTextField(textLetraIngresada);
		restricted.setLimit(1);
		restricted.setOnlyText(true);

		actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada);
	}

	private void finalizarOContinuarJuego() {

		if (ahorcado.perdioJuego()) {
			perderJuego();
		}

		if (ahorcado.ganoJuego()) {
			ganarJuego();
		}
	}

	private void ganarJuego() {
		int opcion = JOptionPane.showConfirmDialog(frmJuegoAhorcado, "¡Ganaste!, ¿Desea seguir jugando?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);

		if (opcion == 0) {
			ahorcado.cambiarPalabra();
		}
		if (opcion == 1) {
			System.exit(0);
		}
	}

	private void perderJuego() {
		int opcion = JOptionPane.showConfirmDialog(frmJuegoAhorcado, "¡Game Over!, ¿Desea seguir jugando?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		if (opcion == 0) {
			ahorcado.reiniciarJuego();
		}
		if (opcion == 1) {
			System.exit(0);
		}
	}

	private void cambiarIdioma() {
		String idioma = menu.getIdioma();
		if (idioma.equals("English")) {
			this.idioma = "English";
			ahorcado.setIdiomaIngles();
			buildIdiomaIngles();
		} else {
			this.idioma = "Español";
			buildIdiomaEspañol();
		}
	}

	private void configurarDificultad() {
		String dificultad = menu.getDificultad();
		if (dificultad.equals("Fácil")) {
			ahorcado.setDificultadFácil();
		}
		if (dificultad.equals("Difícil")) {
			ahorcado.setDificultadDifícil();
		}
	}

	// actualizar texto
	private void actualizarTexto(JLabel lblPuntaje, JLabel lblIntentos, JLabel lblPalabraConGuiones,
			JTextField textLetraIngresada) {
		String textoPuntaje = textos.get("textoPuntaje");
		String textoIntentos = textos.get("textoIntentos");

		lblPalabraConGuiones.setText(ahorcado.obtenerPalabraSecreta().toString());
		lblPuntaje.setText(textoPuntaje + ": " + ahorcado.getPuntaje());
		lblIntentos.setText(textoIntentos + ": " + ahorcado.getIntentos());
		textLetraIngresada.setText("");
	}

	private void buildIdiomaIngles() {
		textos.put("textoPuntaje", "Score");
		textos.put("textoIntentos", "Attempts");
		textos.put("reiniciar", "Restart");
		textos.put("palabraAAdivinar", "Word to guess");
		textos.put("verificarLetra", "Guess letter");
	}

	private void buildIdiomaEspañol() {
		textos.put("textoPuntaje", "Puntaje");
		textos.put("textoIntentos", "Intentos");
		textos.put("reiniciar", "Reiniciar");
		textos.put("palabraAAdivinar", "Palabra a adivinar");
		textos.put("verificarLetra", "Verificar letra");
	}
}
