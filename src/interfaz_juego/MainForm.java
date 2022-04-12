package interfaz_juego;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
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
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

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
		JLabel lblLetrasYaIngresadas = crearLbslLetrasYaIngresadas();
		
		crearLblPalabraJuego();
		JLabel lblPuntaje = crearLblPuntajeJuego();
		JLabel lblIntentos = crearLblIntentosJuego();

		// Palabra a adivinar
		JLabel lblPalabraConGuiones = crearLblPalabraConGuionesJuego();

		// Verificar letra ingresada
		crearBtnVerificarLetraJuego(lblPuntaje, lblIntentos, lblPalabraConGuiones, lblLetrasYaIngresadas);

		// Reiniciar juego
		crearBtnReiniciarJuego(lblPuntaje, lblIntentos, lblPalabraConGuiones, lblLetrasYaIngresadas);

		// Letra ingresada por usuario
		crearTxtFieldLetraIngresadaJuego(lblPuntaje, lblIntentos, lblPalabraConGuiones, lblLetrasYaIngresadas);
		
		// Actualizar texto
		actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada, lblLetrasYaIngresadas);
		
		crearBtnVolverAMenu();
	}

	private void crearBtnVolverAMenu() {
		JButton btnVolverMenu = new JButton(textos.get("volverAMenu"));
		btnVolverMenu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolverMenu.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				String msj = "Perderas tu puntaje";
				btnVolverMenu.setToolTipText(msj);
			}
		});
		btnVolverMenu.setBackground(new Color(255, 255, 204));
		btnVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmJuegoAhorcado.setVisible(false);
				menu = new MenuForm();
			}
		});
		btnVolverMenu.setBounds(192, 11, 165, 23);
		frmJuegoAhorcado.getContentPane().add(btnVolverMenu);
	}

	private JLabel crearLbslLetrasYaIngresadas() {
		JLabel lblTextoLetrasYaIngresadas = new JLabel(textos.get("letrasYaIngresadas"));
		lblTextoLetrasYaIngresadas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTextoLetrasYaIngresadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextoLetrasYaIngresadas.setBounds(162, 75, 218, 23);
		frmJuegoAhorcado.getContentPane().add(lblTextoLetrasYaIngresadas);
		
		JLabel lblLetrasYaIngresadas = new JLabel("");
		lblLetrasYaIngresadas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLetrasYaIngresadas.setBounds(192, 110, 165, 23);
		frmJuegoAhorcado.getContentPane().add(lblLetrasYaIngresadas);
		return lblLetrasYaIngresadas;
	}

	private void crearTxtFieldLetraIngresadaJuego(JLabel lblPuntaje, JLabel lblIntentos, JLabel lblPalabraConGuiones,
			JLabel lblLetrasYaIngresadas) {
		textLetraIngresada = new JTextField();
		textLetraIngresada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String inputUsuario = textLetraIngresada.getText();
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ahorcado.jugar(inputUsuario);

					finalizarOContinuarJuego();

					actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada, lblLetrasYaIngresadas);
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
	private void crearBtnReiniciarJuego(JLabel lblPuntaje, JLabel lblIntentos, JLabel lblPalabraConGuiones,
			JLabel lblLetrasYaIngresadas) {
		JButton btnReiniciar = new JButton(textos.get("reiniciar"));
		btnReiniciar.setBackground(new Color(255, 255, 204));
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaReiniciarJuego();
				actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada, lblLetrasYaIngresadas);
			}
		});

		btnReiniciar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReiniciar.setBounds(192, 45, 165, 23);
		frmJuegoAhorcado.getContentPane().add(btnReiniciar);
	}

	private void crearBtnVerificarLetraJuego(JLabel lblPuntaje, JLabel lblIntentos, JLabel lblPalabraConGuiones,
			JLabel lblLetrasYaIngresadas) {
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

				actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones, textLetraIngresada, lblLetrasYaIngresadas);
			}

		});

		btnVerificarLetra.setBounds(204, 285, 143, 23);
		frmJuegoAhorcado.getContentPane().add(btnVerificarLetra);
	}

	private JLabel crearLblPalabraConGuionesJuego() {
		JLabel lblPalabraConGuiones = new JLabel("");
		lblPalabraConGuiones.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPalabraConGuiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalabraConGuiones.setBounds(162, 178, 218, 48);
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
		JLabel lblLetraUsuario = new JLabel(textos.get("palabraAAdivinar"));
		lblLetraUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLetraUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetraUsuario.setBounds(204, 144, 143, 23);
		frmJuegoAhorcado.getContentPane().add(lblLetraUsuario);
	}

	private void crearDiseñoJuego() {
		frmJuegoAhorcado = new JFrame();
		frmJuegoAhorcado.setIconImage(new ImageIcon(getClass().getResource("/imgs/ahorcado_icon.png")).getImage());
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
		frmJuegoAhorcado.setLocationRelativeTo(null);
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
		if (opcion == 1 || opcion == -1) {
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
			JTextField textLetraIngresada, JLabel lblLetrasYaIngresadas) {
		String textoPuntaje = textos.get("textoPuntaje");
		String textoIntentos = textos.get("textoIntentos");

		lblPalabraConGuiones.setText(ahorcado.obtenerPalabraSecreta().toString());
		lblPuntaje.setText(textoPuntaje + ": " + ahorcado.getPuntaje());
		lblIntentos.setText(textoIntentos + ": " + ahorcado.getIntentos());
		textLetraIngresada.setText("");
		lblLetrasYaIngresadas.setText(ahorcado.obtenerStringLetrasIngresadas());
	}

	private void buildIdiomaIngles() {
		textos.put("textoPuntaje", "Score");
		textos.put("textoIntentos", "Attempts");
		textos.put("reiniciar", "Restart");
		textos.put("palabraAAdivinar", "Word to guess");
		textos.put("verificarLetra", "Guess letter");
		textos.put("letrasYaIngresadas", "Letters already entered");
		textos.put("volverAMenu", "Return to main menu");
	}

	private void buildIdiomaEspañol() {
		textos.put("textoPuntaje", "Puntaje");
		textos.put("textoIntentos", "Intentos");
		textos.put("reiniciar", "Reiniciar");
		textos.put("palabraAAdivinar", "Palabra a adivinar");
		textos.put("verificarLetra", "Verificar letra");
		textos.put("letrasYaIngresadas", "Letras ya ingresadas");
		textos.put("volverAMenu", "Volver al menú");
	}
}
