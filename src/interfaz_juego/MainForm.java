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
import logica.Ahorcado;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainForm {

	private JFrame frmJuegoAhorcado;
	private JTextField textLetraIngresada;
	private Ahorcado ahorcado;
	private static MenuForm menu;
	private Map<String, String> textos = new HashMap<String, String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu = new MenuForm();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Juego
		crearDiseñoJuego();

		// Inicializo ahorcado
		ahorcado = new Ahorcado();

		configurarDificultad();

		// Cambiar idioma
		cambiarIdioma();

		// Labels
		crearLblPalabraJuego();
		JLabel lblPuntaje = crearLblPuntajeJuego();
		JLabel lblIntentos = crearLblIntentosJuego();

		// Palabra a adivinar
		JLabel lblPalabraConGuiones = crearLblPalabraConGuionesJuego();

		// Verificar letra ingresada
		crearBtnVerificarLetraJuego(lblPuntaje, lblIntentos, lblPalabraConGuiones);

		// Reiniciar juego
		crearBtnReiniciarJuego(lblPuntaje, lblIntentos, lblPalabraConGuiones);

		// Letra ingresada por usuario
		crearTxtFieldLetraIngresadaJuego(lblPuntaje, lblIntentos, lblPalabraConGuiones);

		// Actualizar texto
		actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada);
		
		JLabel lblTextoLetrasYaIngresadas = new JLabel("Letras ya ingresadas");
		lblTextoLetrasYaIngresadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextoLetrasYaIngresadas.setBounds(204, 92, 129, 14);
		frmJuegoAhorcado.getContentPane().add(lblTextoLetrasYaIngresadas);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(204, 117, 143, 23);
		frmJuegoAhorcado.getContentPane().add(lblNewLabel_1);
	}

	private void crearTxtFieldLetraIngresadaJuego(JLabel lblPuntaje, JLabel lblIntentos, JLabel lblPalabraConGuiones) {
		textLetraIngresada = new JTextField();
		textLetraIngresada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String inputUsuario = textLetraIngresada.getText();
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ahorcado.jugar(inputUsuario);

					finalizarOContinuarJuego();

					actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada);
				}
			}
		});
		textLetraIngresada.setHorizontalAlignment(SwingConstants.CENTER);
		textLetraIngresada.setBounds(204, 237, 143, 20);
		frmJuegoAhorcado.getContentPane().add(textLetraIngresada);
		textLetraIngresada.setColumns(10);

		restringirCaracteresTextField(textLetraIngresada, 1);
	}

	private void restringirCaracteresTextField(JTextField field, int limite) {
		// Restrinjo cantidad de caracteres que puede ingresar el usuario
		RestrictedTextField restricted = new RestrictedTextField(field);
		restricted.setLimit(limite);
		restricted.setOnlyText(true);
	}
	
	private void ventanaReiniciarJuego() {
		int opcion = JOptionPane.showConfirmDialog(frmJuegoAhorcado, "¿Estás seguro?, perderas todo el puntaje acumulado.", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);

		if (opcion == 0) {
			ahorcado.reiniciarJuego();
		}
		
	}
	private void crearBtnReiniciarJuego(JLabel lblPuntaje, JLabel lblIntentos, JLabel lblPalabraConGuiones) {
		JButton btnReiniciar = new JButton(textos.get("reiniciar"));
		btnReiniciar.setBackground(new Color(255, 255, 204));
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaReiniciarJuego();
				actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada);
			}
		});

		btnReiniciar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReiniciar.setBounds(204, 53, 143, 23);
		frmJuegoAhorcado.getContentPane().add(btnReiniciar);
	}

	private void crearBtnVerificarLetraJuego(JLabel lblPuntaje, JLabel lblIntentos, JLabel lblPalabraConGuiones) {
		JButton btnVerificarLetra = new JButton(textos.get("verificarLetra"));
		btnVerificarLetra.setBackground(new Color(245, 222, 179));
		btnVerificarLetra.setForeground(new Color(0, 0, 0));
		btnVerificarLetra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerificarLetra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String inputUsuario = textLetraIngresada.getText();
				ahorcado.jugar(inputUsuario);

				finalizarOContinuarJuego();

				actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada);
			}

		});

		btnVerificarLetra.setBounds(204, 285, 143, 23);
		frmJuegoAhorcado.getContentPane().add(btnVerificarLetra);
	}

	private JLabel crearLblPalabraConGuionesJuego() {
		JLabel lblPalabraConGuiones = new JLabel("");
		lblPalabraConGuiones.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPalabraConGuiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalabraConGuiones.setBounds(162, 162, 218, 48);
		frmJuegoAhorcado.getContentPane().add(lblPalabraConGuiones);
		return lblPalabraConGuiones;
	}

	private JLabel crearLblIntentosJuego() {
		JLabel lblIntentos = new JLabel(textos.get("textoIntentos") + ": " + ahorcado.getIntentos());
		lblIntentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIntentos.setBounds(479, 11, 84, 23);
		frmJuegoAhorcado.getContentPane().add(lblIntentos);
		return lblIntentos;
	}

	private JLabel crearLblPuntajeJuego() {
		JLabel lblPuntaje = new JLabel(textos.get("textoPuntaje") + ": " + ahorcado.getPuntaje());
		lblPuntaje.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPuntaje.setBounds(10, 11, 93, 23);
		frmJuegoAhorcado.getContentPane().add(lblPuntaje);
		return lblPuntaje;
	}

	private void crearLblPalabraJuego() {
		JLabel lblLetrasIngresadas = new JLabel(textos.get("palabraAAdivinar"));
		lblLetrasIngresadas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLetrasIngresadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetrasIngresadas.setBounds(204, 117, 143, 23);
		frmJuegoAhorcado.getContentPane().add(lblLetrasIngresadas);
	}

	private void crearDiseñoJuego() {
		frmJuegoAhorcado = new JFrame();
		frmJuegoAhorcado.getContentPane().setBackground(new Color(204, 204, 255));
		frmJuegoAhorcado.setForeground(Color.WHITE);
		frmJuegoAhorcado.getContentPane().setForeground(Color.WHITE);
		frmJuegoAhorcado.setResizable(false);
		frmJuegoAhorcado.setTitle("Juego Ahorcado");
		frmJuegoAhorcado.setBackground(Color.PINK);
		frmJuegoAhorcado.setBounds(100, 100, 589, 375);
		frmJuegoAhorcado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJuegoAhorcado.getContentPane().setLayout(null);
		frmJuegoAhorcado.setVisible(true);
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
			ahorcado.setIdiomaIngles();
			buildIdiomaIngles();
		} else {
			buildIdiomaEspañol();
		}
	}

	private void configurarDificultad() {
		String dificultad = menu.getDificultad();
		ahorcado.cambiarDificultad(dificultad);
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
