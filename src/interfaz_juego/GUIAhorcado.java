package interfaz_juego;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Atxy2k.CustomTextField.RestrictedTextField;
import ahorcado.Ahorcado;

import java.awt.Font;

public class GUIAhorcado {

	private JFrame frmJuegoAhorcado;
	private JFrame frmMenu;
	private JTextField textLetraIngresada;
	private Ahorcado ahorcado;
	private JOptionPane panelPerdio = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAhorcado window = new GUIAhorcado();
					window.frmMenu.setVisible(true);
					// Comentado xq quiero abrir el juego desde el menu
					//window.frmJuegoAhorcado.setVisible(true);
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
		// Menu

		frmMenu = new JFrame();
		frmMenu.setForeground(Color.WHITE);
		frmMenu.getContentPane().setForeground(Color.WHITE);
		frmMenu.setResizable(false);
		frmMenu.setTitle("Menu");
		frmMenu.setBackground(Color.PINK);
		frmMenu.setBounds(100, 100, 589, 375);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		frmMenu.setVisible(true);

		JLabel lblMenuPrincipal = new JLabel("Menu Principal");
		lblMenuPrincipal.setFont(new Font("Script MT Bold", Font.PLAIN, 17));
		lblMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuPrincipal.setBounds(185, 49, 133, 28);
		frmMenu.getContentPane().add(lblMenuPrincipal);

		JButton btnIniciarJuego = new JButton("Iniciar juego");
		btnIniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmJuegoAhorcado.setVisible(true);
				frmMenu.setVisible(false);
			}
		});
		btnIniciarJuego.setBounds(185, 88, 117, 23);
		frmMenu.getContentPane().add(btnIniciarJuego);

		JComboBox<String> dificultadComboBox = new JComboBox<String>();
		dificultadComboBox.setBounds(185, 152, 117, 22);
		dificultadComboBox.addItem("Facil");
		dificultadComboBox.addItem("Normal");
		dificultadComboBox.addItem("Dificil");
		frmMenu.getContentPane().add(dificultadComboBox);

		JLabel lblNewLabel_1 = new JLabel("Dificultad");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Script MT Bold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(194, 125, 92, 14);
		frmMenu.getContentPane().add(lblNewLabel_1);

		JComboBox<String> idiomaComboBox = new JComboBox<String>();
		idiomaComboBox.setBounds(185, 209, 117, 22);
		idiomaComboBox.addItem("Español");
		idiomaComboBox.addItem("Inglés");

		frmMenu.getContentPane().add(idiomaComboBox);

		JLabel idiomaLabel = new JLabel("Idioma");
		idiomaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idiomaLabel.setFont(new Font("Script MT Bold", Font.PLAIN, 14));
		idiomaLabel.setBounds(195, 184, 92, 14);
		frmMenu.getContentPane().add(idiomaLabel);
				
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
				
		// inicializo ahorcado
		ahorcado = new Ahorcado();
		
		
		
		// textos
		JLabel lblPalabra = new JLabel("Palabra a adivinar");
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
		JButton btnVerificarLetra = new JButton("Verificar letra");
		btnVerificarLetra.setBackground(new Color(245, 222, 179));
		btnVerificarLetra.setForeground(new Color(0, 0, 0));
		btnVerificarLetra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerificarLetra.setEnabled(false);
		btnVerificarLetra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputUsuario = textLetraIngresada.getText();
				ahorcado.adivinarLetra(inputUsuario);
				
				if(ahorcado.perdioJuego()) {
					panelPerdio.showConfirmDialog(frmJuegoAhorcado, "¡Perdiste!, ¿Desea seguir jugando?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
					if(panelPerdio.YES_NO_OPTION == 0)
						ahorcado.reiniciarJuego();
					else
						frmJuegoAhorcado.setVisible(false);
						frmJuegoAhorcado.dispose();
				}
				
				actualizarTexto(lblPuntaje, lblIntentos, lblPalabraConGuiones);
			}

			
		});
		
		btnVerificarLetra.setBounds(204, 285, 143, 23);
		frmJuegoAhorcado.getContentPane().add(btnVerificarLetra);

		
		
		// cambiar de palabra
		JButton btnCambiarPalabra = new JButton("Reiniciar");
		btnCambiarPalabra.setEnabled(false);
		btnCambiarPalabra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ahorcado.reiniciarJuego();
				lblPalabraConGuiones.setText(ahorcado.obtenerPalabraSecreta().toString());
				lblPuntaje.setText("Puntaje: "+ahorcado.getPuntaje());
				lblIntentos.setText("Intentos: "+ahorcado.getIntentos());
			}
		});
		
		btnCambiarPalabra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCambiarPalabra.setBounds(204, 53, 143, 23);
		frmJuegoAhorcado.getContentPane().add(btnCambiarPalabra);

		// comenzar juego
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPalabraConGuiones.setText(ahorcado.obtenerPalabraSecreta().toString());
				textLetraIngresada.setEnabled(true);
				btnVerificarLetra.setEnabled(true);
				btnCambiarPalabra.setEnabled(true);
			}
		});

		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStart.setBounds(204, 13, 143, 23);
		frmJuegoAhorcado.getContentPane().add(btnStart);

		// letra ingresada por usuario
		textLetraIngresada = new JTextField();
		textLetraIngresada.setHorizontalAlignment(SwingConstants.CENTER);
		textLetraIngresada.setEnabled(false);
		textLetraIngresada.setBounds(204, 237, 143, 20);
		frmJuegoAhorcado.getContentPane().add(textLetraIngresada);
		textLetraIngresada.setColumns(10);
		
		// restrinjo cantidad de caracteres que puede ingresar el usuario
		RestrictedTextField restricted = new RestrictedTextField(textLetraIngresada);
		restricted.setLimit(1);
		restricted.setOnlyText(true);
	
		
	}
	
	private void actualizarTexto(JLabel lblPuntaje, JLabel lblIntentos, JLabel lblPalabraConGuiones) {
		lblPalabraConGuiones.setText(ahorcado.obtenerPalabraSecreta().toString());
		lblPuntaje.setText("Puntaje: " + ahorcado.getPuntaje());
		lblIntentos.setText("Intentos: " + ahorcado.getIntentos());
	}
}
