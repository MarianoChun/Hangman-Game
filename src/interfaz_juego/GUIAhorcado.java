package interfaz_juego;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ahorcado.Ahorcado;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class GUIAhorcado {

	private JFrame frmJuegoAhorcado;
	private JTextField textLetraIngresada;
	private Ahorcado a = new Ahorcado("hola");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAhorcado window = new GUIAhorcado();
					window.frmJuegoAhorcado.setVisible(true);
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
		// ventana
		frmJuegoAhorcado = new JFrame();
		frmJuegoAhorcado.setForeground(Color.WHITE);
		frmJuegoAhorcado.getContentPane().setForeground(Color.WHITE);
		frmJuegoAhorcado.setResizable(false);
		frmJuegoAhorcado.setTitle("Juego Ahorcado");
		frmJuegoAhorcado.setBackground(Color.PINK);
		frmJuegoAhorcado.setBounds(100, 100, 589, 375);
		frmJuegoAhorcado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJuegoAhorcado.getContentPane().setLayout(null);

		// texto
		JLabel lblPalabra = new JLabel("Palabra a adivinar");
		lblPalabra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPalabra.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalabra.setBounds(187, 117, 172, 34);
		frmJuegoAhorcado.getContentPane().add(lblPalabra);

		JLabel lblPuntaje = new JLabel("Puntaje: " + a.getPuntaje());
		lblPuntaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuntaje.setBounds(10, 11, 93, 23);
		frmJuegoAhorcado.getContentPane().add(lblPuntaje);

		JLabel lblIntentos = new JLabel("Intentos: " + a.getIntentos());
		lblIntentos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntentos.setBounds(479, 11, 84, 23);
		frmJuegoAhorcado.getContentPane().add(lblIntentos);

		// palabra a adivinar
		JLabel lblPalabraConGuiones = new JLabel("");
		lblPalabraConGuiones.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPalabraConGuiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalabraConGuiones.setBounds(162, 162, 218, 48);
		frmJuegoAhorcado.getContentPane().add(lblPalabraConGuiones);

		// comenzar juego
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPalabraConGuiones.setText(a.obtenerPalabraAAdivinar().toString());
				textLetraIngresada.setEnabled(true);
			}
		});

		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStart.setBounds(228, 13, 89, 23);
		frmJuegoAhorcado.getContentPane().add(btnStart);

		// verificar letra ingresada
		JButton btnVerificarLetra = new JButton("Verificar letra");
		btnVerificarLetra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char letraUsuario;
				if (textLetraIngresada.getText().length() > 0) {
					letraUsuario = textLetraIngresada.getText().charAt(0);
				} else {
					letraUsuario = ' ';
				}
				a.adivinarLetra(letraUsuario);
				lblPalabraConGuiones.setText(a.obtenerPalabraAAdivinar().toString());
				lblPuntaje.setText("Puntaje: " + a.getPuntaje());
				lblIntentos.setText("Intentos: " + a.getIntentos());
			}
		});
		btnVerificarLetra.setBounds(204, 285, 143, 23);
		frmJuegoAhorcado.getContentPane().add(btnVerificarLetra);

		// letra ingresada por usuario
		textLetraIngresada = new JTextField();
		textLetraIngresada.setEnabled(false);
		textLetraIngresada.setBounds(204, 237, 143, 20);
		frmJuegoAhorcado.getContentPane().add(textLetraIngresada);
		textLetraIngresada.setColumns(10);
	}
}
